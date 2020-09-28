package ee.project.trader.dto;

public class StrategyDetails {
    private String symbol;
    private double marketPrice;
    private String priceTrend;
    private String priceQuick;
    private String priceSlow;
    private String trendQuick;
    private String trendSlow;
    private String quickSlow;

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

    public String getPriceTrend() {
        return priceTrend;
    }

    public void setPriceTrend(String priceTrend) {
        this.priceTrend = priceTrend;
    }

    public String getPriceQuick() {
        return priceQuick;
    }

    public void setPriceQuick(String priceQuick) {
        this.priceQuick = priceQuick;
    }

    public String getPriceSlow() {
        return priceSlow;
    }

    public void setPriceSlow(String priceSlow) {
        this.priceSlow = priceSlow;
    }

    public String getTrendQuick() {
        return trendQuick;
    }

    public void setTrendQuick(String trendQuick) {
        this.trendQuick = trendQuick;
    }

    public String getTrendSlow() {
        return trendSlow;
    }

    public void setTrendSlow(String trendSlow) {
        this.trendSlow = trendSlow;
    }

    public String getQuickSlow() {
        return quickSlow;
    }

    public void setQuickSlow(String quickSlow) {
        this.quickSlow = quickSlow;
    }
}
