package ee.project.trader;

import com.ib.client.Contract;
import com.ib.controller.Bar;
import ee.project.trader.dto.PlaceOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;
    String actionZero;
    String actionOne;

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

    public List<Ticker> getTickerList() {
        return traderRepository.getTickerList();
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
        double trend = traderRepository.getSMA(sma1);
        double quick = traderRepository.getSMA(sma5);
        double slow = traderRepository.getSMA(sma13);

        // Strateegiate actionid:
        String price_trend;
        String price_quick;
        String price_slow;
        String trend_quick;
        String trend_slow;
        String quick_slow;


        if (price > trend) {
            price_trend = "BUY";
        } else {
            price_trend = "SELL";
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

        if (trend > quick) {
            trend_quick = "BUY";
        } else {
            trend_quick = "SELL";
        }

        if (trend > slow) {
            trend_slow = "BUY";
        } else {
            trend_slow = "SELL";
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
                contract.symbol(), price, trend,
                quick, slow, price_trend, price_quick, price_slow, trend_quick, trend_slow, quick_slow);
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

    public void placeOrder(PlaceOrderDto order) {
        traderRepository.placeOrder(order);
    }
}
