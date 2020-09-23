package ee.project.trader.dto;

public class PlaceOrderDto {

    private int algoId;             //Strategy
    private String symbol;          //Ticker symbol
    private String orderType;       //Limit/Market
    private String orderAction;     //Buy, Sell, Short
    private int quantity;           //Amount of stocks
    private double limitPrice;      //Limit price
    private double stopLoss;        //Stop loss price
    private double profitTaker;     //Profit taker price
    private String valid;           //Duration of the order

    public int getAlgoId() {
        return algoId;
    }

    public void setAlgoId(int algoId) {
        this.algoId = algoId;
    }

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

    public String getOrderAction() {
        return orderAction;
    }

    public void setOrderAction(String orderAction) {
        this.orderAction = orderAction;
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

    public double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public double getProfitTaker() {
        return profitTaker;
    }

    public void setProfitTaker(double profitTaker) {
        this.profitTaker = profitTaker;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
