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

/*
        System.out.println("******************* " + contract.symbol() + " *********************");
        System.out.println(contract.symbol() + " price " + bar.high());
        System.out.println(contract.symbol() + " SMA5  " + traderRepository.getSMA(sma5));
        System.out.println(contract.symbol() + " SMA13 " + traderRepository.getSMA(sma13));

        if (doBuy(bar.high(), traderRepository.getSMA(sma5))) {
            System.out.println(contract.symbol() + " Strategy Price/SMA5  BUY: " + contract.symbol());

        } else {
            System.out.println(contract.symbol() + " Strategy Price/SMA5 SELL: " + contract.symbol());
        }


        if (traderRepository.getSMA(sma5) > traderRepository.getSMA(sma13)) {
            System.out.println(contract.symbol() + " Strategy SMA5/SMA13  BUY: " + contract.symbol());


        } else {
            System.out.println(contract.symbol() + " Strategy SMA5/SMA13 SELL: " + contract.symbol());
        }

        System.out.println("______________________________________________");
        System.out.println();

       */

        // Kirjutame SMA põhised strateegiad andmebaasi:
        StrategyLine strategyLine = new StrategyLine(bar.time(),
                contract.symbol(), price, rapid,
                quick, slow, price_rapid, price_quick, price_slow, rapid_quick, rapid_slow, quick_slow);
        traderRepository.insertStrategyLine(strategyLine);
        traderRepository.insertStrategyLineToTicker(strategyLine);


        // Nüüdseks on meil kõikide aktsiate kohta olemas hinnainfo, ja valitud SMA-de alusel
        // genereeritud strateegiate actionid


        // Tsekkame, kas mõni order on sisestatud ja saadame selle TWS-i

        if (!traderRepository.getSubmittedOrdersList().isEmpty()) {
        //    System.out.println("Order to be handled");
            traderRepository.changeOrderStatus(traderRepository.getSubmittedOrdersFirstId(), "T E H T U D");
        } else {
          //  System.out.println("Orderid teostatud");

        }

        /*
        if (traderRepository.getSubmittedOrdersFirstId() != 0) {
            System.out.println("Order to be handled");

            //System.out.println("Symbol: " + traderRepository.getSubmittedOrdersList().get(0).getSymbol() +" Qty: " + traderRepository.getSubmittedOrdersList().get(0).getQuantity() );
            //System.out.println("Status: " + traderRepository.getSubmittedOrdersList().get(0).getStatus());
            //System.out.println("Teostame ja muudame statust!");
            traderRepository.changeOrderStatus(traderRepository.getSubmittedOrdersFirstId(), "T E H T U D" );

        }

*/


// Kutsume konkreetse aktsia osas välja actioni valitud strateegiaga

        /*
        actionOne = traderRepository.getAction("SOXL", "trend_slow");
        if (actionOne.equals(actionZero)){
            System.out.println();
        } else {
            System.out.println("SOXL -> " + actionOne);
        }
        actionZero = actionOne;
*/
    }


// @Transactional : Et kui TWS annab tala, siis ei toimu orderi lisamist dB-sse Submitted
    @Transactional
    public void addOrder(SubmitOrder order) {
        List<Integer> orderIdList = new ArrayList<>();
        int parentOrderId = traderRepository.insertOrder(order);
        orderIdList.add(parentOrderId);
        if (order.getProfitTaker() != null) {
            orderIdList.add(traderRepository.insertProfitTaker(order, parentOrderId));
        }
        if (order.getStopLoss() != null) {
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

    public List<TickerSymbol> getSymbolList() {
        return traderRepository.getSymbolList();
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

    public void changeStatus() {
        // Muudame orderi statuse databaasis, kui täidetud
    }
}
