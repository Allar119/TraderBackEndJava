package ee.project.trader;

public class Order {
    String symbol;
    String orderType;
    int quantity;
    double limitPrice;
    double stopLossPrice;

    public Order(String symbol, String orderType, int quantity, double limitPrice, double stopLossPrice, double profitTakerPrice) {
        this.symbol = symbol;
        this.orderType = orderType;
        this.quantity = quantity;
        this.limitPrice = limitPrice;
        this.stopLossPrice = stopLossPrice;
        this.profitTakerPrice = profitTakerPrice;
    }

    double profitTakerPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(double limitPrice) {
        this.limitPrice = limitPrice;
    }

    public double getStopLossPrice() {
        return stopLossPrice;
    }

    public void setStopLossPrice(double stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public double getProfitTakerPrice() {
        return profitTakerPrice;
    }

    public void setProfitTakerPrice(double profitTakerPrice) {
        this.profitTakerPrice = profitTakerPrice;
    }




}
