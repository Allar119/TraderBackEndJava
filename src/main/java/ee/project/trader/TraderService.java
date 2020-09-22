package ee.project.trader;

import com.ib.client.Contract;
import com.ib.controller.Bar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;

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


    public List<Ticker> getTickerList() {
        return traderRepository.getTickerList();
    }

    public void newBar(Contract contract, Bar bar) {
        /*
        System.out.println("realTimeBar käivitus");
        System.out.println("TIME: " + bar.time());
        System.out.println("HIGH: " + bar.high());
        System.out.println("TIME FORMATTED" + bar.formattedTime());
        System.out.println(contract.symbol());
        System.out.println(bar);

        SIIT HAKKAB DB, SMA jpm.
         */
        Price barPrice = new Price(contract.symbol(), bar.time(), bar.open(), bar.close(), bar.high(), bar.low());
        traderRepository.addPrice(barPrice);

        SMA sma5 = new SMA(contract.symbol(),60);
        SMA sma13 = new SMA(contract.symbol(),156);
        System.out.println("******************* " + contract.symbol() + " *********************");
        System.out.println(contract.symbol()+ " price " + bar.high());
        System.out.println(contract.symbol() +" SMA5  " + traderRepository.getSMA(sma5));
        System.out.println(contract.symbol() +" SMA13 " + traderRepository.getSMA(sma13));

        if (bar.high() > traderRepository.getSMA(sma5)) {
            System.out.println(contract.symbol()+ " Strategy Price/SMA5  BUY: " + contract.symbol());


        } else {
            System.out.println(contract.symbol()+ " Strategy Price/SMA5 SELL: " + contract.symbol());
        }

        if (traderRepository.getSMA(sma5) > traderRepository.getSMA(sma13)) {
            System.out.println(contract.symbol()+ " Strategy SMA5/SMA13  BUY: " + contract.symbol());


        } else {
            System.out.println(contract.symbol()+ " Strategy SMA5/SMA13 SELL: " + contract.symbol());
        }

        System.out.println("______________________________________________");
        System.out.println();
    //    SMA sma9 = new SMA(contract.symbol(),108);
      //  SMA sma13 = new SMA(contract.symbol(),156);
        //SMA sma20 = new SMA(contract.symbol(),240);
        //SMA sma26 = new SMA(contract.symbol(),312);





        // baasi tabelid valmis


    }

}
