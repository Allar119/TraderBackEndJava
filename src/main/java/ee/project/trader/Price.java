package ee.project.trader;

import java.sql.Timestamp;

public class Price {

    String symbol;
    long timestamp;
    Double priceOpen;
    Double priceClose;
    Double priceMax;
    Double priceMin;


    public Price(String symbol, long timestamp, Double priceOpen, Double priceClose, Double priceMax, Double priceMin) {
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        this.priceMax = priceMax;
        this.priceMin = priceMin;
    }
}
