package ee.project.trader.handlers;

import com.ib.client.OrderType;
import com.ib.client.Types;

public class NewOrder {

    private int orderId;
    private Types.Action action;
    private OrderType lmt;
    private int quantity;
    private double limitPrice;
    private boolean transmit;
    private int parentOrderId;
    private double auxPrice;

    public void orderId(int orderId) {
        this.orderId = orderId;
    }

    public void action(Types.Action action) {
        this.action = action;
    }

    public void orderType(OrderType lmt) {
        this.lmt = lmt;
    }

    public void totalQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void lmtPrice(double limitPrice) {
        this.limitPrice = limitPrice;
    }

    public void transmit(boolean transmit) {
        this.transmit = transmit;
    }

    public void parentId(int parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public void auxPrice(double auxPrice) {
        this.auxPrice = auxPrice;
    }

    public int orderId() {
        return orderId;
    }

    public Types.Action getAction() {
        return action;
    }

    public OrderType getLmt() {
        return lmt;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getLimitPrice() {
        return limitPrice;
    }

    public boolean isTransmit() {
        return transmit;
    }

    public int getParentOrderId() {
        return parentOrderId;
    }

    public double getAuxPrice() {
        return auxPrice;
    }
}
