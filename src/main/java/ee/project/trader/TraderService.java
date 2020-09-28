package ee.project.trader;

import com.ib.client.Contract;
import com.ib.controller.Bar;
import ee.project.trader.dto.*;
import ee.project.trader.handlers.ConnectionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;
    @Autowired
    private ConnectionHandler connectionHandler;

    String actionZero;
    String actionOne;

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

        // Kirjutame strateegiad andmebaasi:
        StrategyLine strategyLine = new StrategyLine(bar.time(),
                contract.symbol(), price, rapid,
                quick, slow, price_rapid, price_quick, price_slow, rapid_quick, rapid_slow, quick_slow);
        traderRepository.insertStrategyLine(strategyLine);
        traderRepository.insertStrategyLineToTicker(strategyLine);


        // Nüüdseks on meil kõikide aktsiate kohta olemas hinnainfo, ja valitud SMA-de alusel
        // genereeritud strateegiate actionid

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

    public void addOrder(SubmitOrder order) {
        traderRepository.insertOrder(order);

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

}
