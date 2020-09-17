package ee.project.trader;

import com.ib.client.Contract;

public class Ticker {

/*
      symbol("AAPL"), secType("STK"), exchange("SMART"), currency("USD")
 */

    String symbol;
    String secType;
    String exchange;
    String currency;

    public Ticker(String symbol, String secType, String exchange, String currency) {
        this.symbol = symbol;
        this.secType = secType;
        this.exchange = exchange;
        this.currency = currency;
    }

}

