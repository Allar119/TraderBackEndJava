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
        System.out.println("realTimeBar käivitus");

        System.out.println("TIME: " + bar.time());
        System.out.println("HIGH: " + bar.high());
        System.out.println("TIME FORMATTED" + bar.formattedTime());
        System.out.println(contract.symbol());
        System.out.println(bar);
        Price barPrice = new Price(contract.symbol(), bar.formattedTime(), bar.open(), bar.close(), bar.high(), bar.low());
        traderRepository.addPrice(barPrice);
        //SIIT HAKKAB DB, SMA jpm.

        // baasi tabelid valmis


    }

}
