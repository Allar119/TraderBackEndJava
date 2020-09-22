package ee.project.trader;

public class Order {
    int algoId;
    String symbol;
    String orderType;
    int quantity;
    double limitPrice;
    double stopLossPrice;
    String status;

    public int getId() {
        return algoId;
    }

    public void setId(int id) {
        this.algoId = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Order(int id, String symbol, String orderType, int quantity, double limitPrice, double stopLossPrice, String status, double profitTakerPrice) {
        this.algoId = id;
        this.symbol = symbol;
        this.orderType = orderType;
        this.quantity = quantity;
        this.limitPrice = limitPrice;
        this.stopLossPrice = stopLossPrice;
        this.status = status;
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
