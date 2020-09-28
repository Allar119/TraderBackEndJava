package ee.project.trader.dto;

public class StrategyDetails {
    private String symbol;
    private Double marketPrice;
    private String priceRapid;
    private String priceQuick;
    private String priceSlow;
    private String rapidQuick;
    private String rapidSlow;
    private String quickSlow;

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

    public String getPriceRapid() {
        return priceRapid;
    }

    public void setPriceRapid(String priceRapid) {
        this.priceRapid = priceRapid;
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

    public String getRapidQuick() {
        return rapidQuick;
    }

    public void setRapidQuick(String rapidQuick) {
        this.rapidQuick = rapidQuick;
    }

    public String getRapidSlow() {
        return rapidSlow;
    }

    public void setRapidSlow(String rapidSlow) {
        this.rapidSlow = rapidSlow;
    }

    public String getQuickSlow() {
        return quickSlow;
    }

    public void setQuickSlow(String quickSlow) {
        this.quickSlow = quickSlow;
    }
}
