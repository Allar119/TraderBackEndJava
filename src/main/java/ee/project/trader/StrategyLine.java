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
    double marketPrice;
    double trend;
    double quick;
    double slow;
    String price_trend;
    String price_quick;
    String price_slow;
    String trend_quick;
    String trend_slow;
    String quick_slow;

    public StrategyLine(long time, String symbol, double marketPrice, double trend, double quick, double slow, String price_trend, String price_quick, String price_slow, String trend_quick, String trend_slow, String quick_slow) {
        this.time = time;
        this.symbol = symbol;
        this.marketPrice = marketPrice;
        this.trend = trend;
        this.quick = quick;
        this.slow = slow;
        this.price_trend = price_trend;
        this.price_quick = price_quick;
        this.price_slow = price_slow;
        this.trend_quick = trend_quick;
        this.trend_slow = trend_slow;
        this.quick_slow = quick_slow;
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

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getTrend() {
        return trend;
    }

    public void setTrend(double trend) {
        this.trend = trend;
    }

    public double getQuick() {
        return quick;
    }

    public void setQuick(double quick) {
        this.quick = quick;
    }

    public double getSlow() {
        return slow;
    }

    public void setSlow(double slow) {
        this.slow = slow;
    }

    public String getPrice_trend() {
        return price_trend;
    }

    public void setPrice_trend(String price_trend) {
        this.price_trend = price_trend;
    }

    public String getPrice_quick() {
        return price_quick;
    }

    public void setPrice_quick(String price_quick) {
        this.price_quick = price_quick;
    }

    public String getPrice_slow() {
        return price_slow;
    }

    public void setPrice_slow(String price_slow) {
        this.price_slow = price_slow;
    }

    public String getTrend_quick() {
        return trend_quick;
    }

    public void setTrend_quick(String trend_quick) {
        this.trend_quick = trend_quick;
    }

    public String getTrend_slow() {
        return trend_slow;
    }

    public void setTrend_slow(String trend_slow) {
        this.trend_slow = trend_slow;
    }

    public String getQuick_slow() {
        return quick_slow;
    }

    public void setQuick_slow(String quick_slow) {
        this.quick_slow = quick_slow;
    }
}
