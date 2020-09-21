package ee.project.trader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
