package ee.project.trader;

public class StrategyLine {

/*
            <column name="time" type="BIGINT"/>
            <column name="symbol" type="TEXT"/>
            <column name="market_price" type="FLOAT"/>
            <column name="trend" type="FLOAT"/>
            <column name="quick" type="FLOAT"/>
            <column name="slow" type="FLOAT"/>
            <column name="price_trend" type="TEXT"/>
            <column name="price_quick" type="TEXT"/>
            <column name="price_slow" type="TEXT"/>
            <column name="trend_quick" type="TEXT"/>
            <column name="trend_slow" type="TEXT"/>
            <column name="quick_slow" type="TEXT"/>
 */

    long time;
    String symbol;
    Double marketPrice;
    Double rapidSMA;
    Double quickSMA;
    Double slowSMA;
    String price_rapidSMA;
    String price_quickSMA;
    String price_slowSMA;
    String rapidSMA_quickSMA;
    String rapidSMA_slowSMA;
    String quickSMA_slowSMA;

    public StrategyLine(long time, String symbol, Double marketPrice, Double rapidSMA, Double quickSMA, Double slowSMA, String price_rapidSMA, String price_quickSMA, String price_slowSMA, String rapidSMA_quickSMA, String rapidSMA_slowSMA, String quickSMA_slowSMA) {
        this.time = time;
        this.symbol = symbol;
        this.marketPrice = marketPrice;
        this.rapidSMA = rapidSMA;
        this.quickSMA = quickSMA;
        this.slowSMA = slowSMA;
        this.price_rapidSMA = price_rapidSMA;
        this.price_quickSMA = price_quickSMA;
        this.price_slowSMA = price_slowSMA;
        this.rapidSMA_quickSMA = rapidSMA_quickSMA;
        this.rapidSMA_slowSMA = rapidSMA_slowSMA;
        this.quickSMA_slowSMA = quickSMA_slowSMA;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getRapidSMA() {
        return rapidSMA;
    }

    public void setRapidSMA(Double rapidSMA) {
        this.rapidSMA = rapidSMA;
    }

    public Double getQuickSMA() {
        return quickSMA;
    }

    public void setQuickSMA(Double quickSMA) {
        this.quickSMA = quickSMA;
    }

    public Double getSlowSMA() {
        return slowSMA;
    }

    public void setSlowSMA(Double slowSMA) {
        this.slowSMA = slowSMA;
    }

    public String getPrice_rapidSMA() {
        return price_rapidSMA;
    }

    public void setPrice_rapidSMA(String price_rapidSMA) {
        this.price_rapidSMA = price_rapidSMA;
    }

    public String getPrice_quickSMA() {
        return price_quickSMA;
    }

    public void setPrice_quickSMA(String price_quickSMA) {
        this.price_quickSMA = price_quickSMA;
    }

    public String getPrice_slowSMA() {
        return price_slowSMA;
    }

    public void setPrice_slowSMA(String price_slowSMA) {
        this.price_slowSMA = price_slowSMA;
    }

    public String getRapidSMA_quickSMA() {
        return rapidSMA_quickSMA;
    }

    public void setRapidSMA_quickSMA(String rapidSMA_quickSMA) {
        this.rapidSMA_quickSMA = rapidSMA_quickSMA;
    }

    public String getRapidSMA_slowSMA() {
        return rapidSMA_slowSMA;
    }

    public void setRapidSMA_slowSMA(String rapidSMA_slowSMA) {
        this.rapidSMA_slowSMA = rapidSMA_slowSMA;
    }

    public String getQuickSMA_slowSMA() {
        return quickSMA_slowSMA;
    }

    public void setQuickSMA_slowSMA(String quickSMA_slowSMA) {
        this.quickSMA_slowSMA = quickSMA_slowSMA;
    }
}
