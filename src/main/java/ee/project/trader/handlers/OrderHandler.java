package ee.project.trader.handlers;

import com.ib.client.*;
import com.ib.controller.ApiController;
import ee.project.trader.Ticker;
import ee.project.trader.TraderService;

public class OrderHandler implements ApiController.ILiveOrderHandler, ApiController.IOrderHandler  {

    private final Ticker ticker;
    private final TraderService traderService;
    private final ConnectionHandler connectionHandler;

    public OrderHandler(Ticker ticker, TraderService traderService, ConnectionHandler connectionHandler) {
        this.ticker = ticker;
        this.traderService = traderService;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void orderState(OrderState orderState) {

    }

    @Override
    public void orderStatus(OrderStatus status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {

    }

    @Override
    public void handle(int errorCode, String errorMsg) {

    }

    @Override
    public void openOrder(Contract contract, Order order, OrderState orderState) {
        System.out.println("Open order " + contract.symbol());
    }

    @Override
    public void openOrderEnd() {

    }

    @Override
    public void orderStatus(int orderId, OrderStatus status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
        // TODO siin tuleb kasutada traderService-it ja seda ei saa Autowire-ita, seega see on initsialiseeritud constructoris, kuna antud klass pole spring fw kontekstis.
        System.out.println("Order status " + orderId + " " + parentId + " " + status.name());
    }

    @Override
    public void handle(int orderId, int errorCode, String errorMsg) {

    }
}
