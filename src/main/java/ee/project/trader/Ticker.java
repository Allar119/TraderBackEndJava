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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }



}

