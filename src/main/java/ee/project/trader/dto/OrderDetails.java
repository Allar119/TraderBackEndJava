package ee.project.trader.dto;

public class OrderDetails {

    private int id;                 //Database unique id
    private int algoId;             //Strategy
    private String symbol;          //Ticker symbol
    private String orderType;       //Limit/Market
    private int quantity;           //Amount of stocks
    private double limitPrice;      //Limit price
    private double stopLoss;        //Stop loss price
    private double profitTaker;     //Profit taker price
    private String status;          //Order status
    private String valid;           //Duration of the order
    private String orderAction;     //Buy, Sell, Short

    public void setAlgoId(int algoId) {
        this.algoId = algoId;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setOrderAction(String orderAction) {
        this.orderAction = orderAction;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLimitPrice(double limitPrice) {
        this.limitPrice = limitPrice;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public void setProfitTaker(double profitTaker) {
        this.profitTaker = profitTaker;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAlgoId() {
        return algoId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getOrderType() {
        return orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getLimitPrice() {
        return limitPrice;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public double getProfitTaker() {
        return profitTaker;
    }

    public String getStatus() {
        return status;
    }

    public String getValid() {
        return valid;
    }

    public String getOrderAction() {
        return orderAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
