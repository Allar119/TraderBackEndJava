package ee.project.trader;

import java.sql.Timestamp;

public class Price {

    String symbol;
    String timestamp;
    double priceOpen;
    double priceClose;
    double priceMax;
    double priceMin;

    public Price(String symbol, String timestamp, double priceOpen, double priceClose, double priceMax, double priceMin) {
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        this.priceMax = priceMax;
        this.priceMin = priceMin;
        //priceBarTimeFrame?
    }
}
