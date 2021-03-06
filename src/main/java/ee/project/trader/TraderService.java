package ee.project.trader;

import com.ib.client.Contract;
import com.ib.controller.Bar;
import ee.project.trader.dto.*;
import ee.project.trader.handlers.ConnectionHandler;
import ee.project.trader.handlers.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ConnectionHandler connectionHandler;

    public ConnectionStatus connectToTws(ConnectionDetails connect) throws InterruptedException {
        connectionHandler.run(connect.getIp(), connect.getPort(), connect.getClientId(), "");
        Thread.sleep(1000); //wait 1s the continue
        ConnectionStatus status = new ConnectionStatus();
        status.setConnected(connectionHandler.isConnected());
        status.setAccount(connectionHandler.getAccount());
        connectionHandler.init();
        return status;
    }

    public ConnectionStatus disconnectTws() throws InterruptedException {
        connectionHandler.disconnectTws();
        Thread.sleep(1000); //wait 1s the continue
        ConnectionStatus status = new ConnectionStatus();
        status.setConnected(connectionHandler.isConnected());
        status.setAccount(connectionHandler.getAccount());
        return status;
    }

    public void addTicker(Ticker ticker) {
        System.out.println("traderService addTicker:");
        System.out.println(ticker.symbol);
        System.out.println(ticker.secType);
        System.out.println(ticker.exchange);
        System.out.println(ticker.currency);
        System.out.println("=====================================================");
        System.out.println();
        traderRepository.addTicker(ticker);
    }

    public void addPrice(Price price) {
        traderRepository.addPrice(price);
    }

    public void deleteTicker(int id) {
        traderRepository.deleteTicker(id);
    }

    public boolean doBuy(double quick, double slow) {
        if (quick > slow) {
            return true;
        } else {
            return false;
        }
    }

    public void newBar(Contract contract, Bar bar) {
/*
        SIIT HAKKAB DB, SMA jpm.
         */

        // küsime iga aktsia nn "küünla" hinda 5 sekundi  tagant
        Price barPrice = new Price(contract.symbol(), bar.time(), bar.open(), bar.close(), bar.high(), bar.low());
        traderRepository.addPrice(barPrice);

        // degineerime jooksvate keskmiste arvutamiste alused:
        SMA sma1 = new SMA(contract.symbol(), 1);
        // SMA sma3 = new SMA(contract.symbol(), 3);
        SMA sma5 = new SMA(contract.symbol(), 5);
        // SMA sma9 = new SMA(contract.symbol(), 9);
        SMA sma13 = new SMA(contract.symbol(), 13);
        // SMA sma15 = new SMA(contract.symbol(), 15);
        // SMA sma20 = new SMA(contract.symbol(), 20);
        // SMA sma26 = new SMA(contract.symbol(), 26);

        // Defineerime neli sisendit erinevate strateegiate loomiseks

        double price = bar.high();
        double rapid = traderRepository.getSMA(sma1);
        double quick = traderRepository.getSMA(sma5);
        double slow = traderRepository.getSMA(sma13);

        // Strateegiate actionid:
        String price_rapid;
        String price_quick;
        String price_slow;
        String rapid_quick;
        String rapid_slow;
        String quick_slow;


        if (price > rapid) {
            price_rapid = "BUY";
        } else {
            price_rapid = "SELL";
        }

        if (price > quick) {
            price_quick = "BUY";
        } else {
            price_quick = "SELL";
        }

        if (price > slow) {
            price_slow = "BUY";
        } else {
            price_slow = "SELL";
        }

        if (rapid > quick) {
            rapid_quick = "BUY";
        } else {
            rapid_quick = "SELL";
        }

        if (rapid > slow) {
            rapid_slow = "BUY";
        } else {
            rapid_slow = "SELL";
        }

        if (quick > slow) {
            quick_slow = "BUY";
        } else {
            quick_slow = "SELL";
        }



        // Kirjutame SMA põhised strateegiad andmebaasi:
        StrategyLine strategyLine = new StrategyLine(bar.time(),
                contract.symbol(), price, rapid,
                quick, slow, price_rapid, price_quick, price_slow, rapid_quick, rapid_slow, quick_slow);
        traderRepository.insertStrategyLine(strategyLine);
        traderRepository.insertStrategyLineToTicker(strategyLine);

    }


    // @Transactional : Et kui TWS annab tala, siis ei toimu orderi lisamist dB-sse Submitted
    @Transactional
    public void addOrder(SubmitOrder order) {
        List<Integer> orderIdList = new ArrayList<>();
        int parentOrderId = traderRepository.insertOrder(order);
        orderIdList.add(parentOrderId);


        if (order.getProfitTaker() != null && order.getProfitTaker() > 0) {
            System.out.println("ProfitTaker");
            if (order.getOrderAction().equals("BUY")) {
              //  System.out.println("Parent orderAction: " + order.getOrderAction());
                order.setOrderAction("SELL");
             //   System.out.println("ProfitTaker orderAction: " + order.getOrderAction());
            } else if (order.getOrderType().equals("SELL")) {
             //   System.out.println("Parent orderAction: " + order.getOrderAction());
                order.setOrderAction("BUY");
               // System.out.println("ProfitTaker orderAction: " + order.getOrderAction());
            } else {
                System.out.println("ProfitTaker puudub");
            }

            orderIdList.add(traderRepository.insertProfitTaker(order, parentOrderId));

        }
        if (order.getStopLoss() != null) {
            System.out.println("StopLoss");
            if (order.getOrderType().equals("BUY")) {
           //     System.out.println("Parent orderType: " + order.getOrderType());
                order.setOrderType("SELL");
             //   System.out.println("StopLoss orderType: " + order.getOrderType());
            } else if (order.getOrderType().equals("SELL")) {
             //   System.out.println("Parent orderType: " + order.getOrderType());
                order.setOrderType("BUY");
             //   System.out.println("StopLoss orderType: " + order.getOrderType());
            } else {
                System.out.println("StopLoss puudub");
            }
            orderIdList.add(traderRepository.insertStopLoss(order, parentOrderId));
        }
        orderService.addOrders(orderIdList);
    }

    public List<Ticker> getTickerList() {
        return traderRepository.getTickerList();
    }

    public List<OrderDetails> getOrdersList() {
        return traderRepository.getOrdersList();
    }

    public void deleteOrder(int id) {
        traderRepository.deleteOrder(id);
    }

    public List<DropDownOption> getSymbolList() {
        List<DropDownOption> dropDownList = new ArrayList<>();

        for (TickerSymbol symbol : traderRepository.getSymbolList()){
            DropDownOption option = new DropDownOption();
            option.setOption(symbol.getSymbol());
            option.setValue(symbol.getSymbol());
            dropDownList.add(option);
        }
        return dropDownList;
    }

    public List<StrategyDetails> getStrategyDetails() {
        return traderRepository.getStrategyDetails();
    }

    public OrderDetails getOrder(int orderId) {
        return traderRepository.getOrder(orderId);
    }

    public Ticker getTickerBySymbol(String symbol) {
        return traderRepository.getTickerBySymbol(symbol);
    }

    public ConnectionStatus checkConnectionStatus() throws InterruptedException {
        Thread.sleep(1); //wait 1s the continue

        ConnectionStatus status = new ConnectionStatus();
        status.setConnected(connectionHandler.isConnected());
        status.setAccount(connectionHandler.getAccount());

        return status;
    }

    public List<DropDownOption> getStrategies() {

        List<DropDownOption> dropDownList = new ArrayList<>();

        for (StrategyType str : traderRepository.getStrategies()) {
            DropDownOption option = new DropDownOption();
            option.setOption(str.getStrategyNameWithId());
            option.setValue(String.valueOf((str.getStrategyId())));
            dropDownList.add(option);
        }
        return dropDownList;
    }

    public void changeStatus() {
        System.out.println("TraderService changeStatus()");
        // Muudame orderi statuse databaasis, kui täidetud
    }

    public StrategyDetails getTickerStrategy(String symbol) {
        return traderRepository.getTickerStrategy(symbol);
    }
}
