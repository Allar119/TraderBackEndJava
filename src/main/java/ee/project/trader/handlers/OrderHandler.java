package ee.project.trader.handlers;

import com.ib.client.*;
import com.ib.controller.ApiController;
import ee.project.trader.Ticker;
import ee.project.trader.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class OrderHandler implements ApiController.ILiveOrderHandler, ApiController.IOrderHandler  {

    private TraderService traderService;

    public OrderHandler(TraderService traderService) {
        this.traderService = traderService;
    }

    @Override
    public void orderState(OrderState orderState) {
        System.out.println("state:" + orderState.getStatus());
    }

    @Override
    public void orderStatus(OrderStatus status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
        System.out.println("status");
    }

    @Override
    public void handle(int errorCode, String errorMsg) {
        System.out.println("handle");
    }

    @Override
    public void openOrder(Contract contract, Order order, OrderState orderState) {
        System.out.println("Open order " + contract.symbol());
    }

    @Override
    public void openOrderEnd() {
        System.out.println("Open order end");
    }

    @Override
    public void orderStatus(int orderId, OrderStatus status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
        // TODO siin tuleb kasutada traderService-it ja seda ei saa Autowire-ita, seega see on initsialiseeritud constructoris, kuna antud klass pole spring fw kontekstis.
        System.out.println("ORDER STATUS *** | OrderID: " + orderId + " | parentID: " + parentId + " | status: " + status.name());
        traderService.changeStatus();
    }

    @Override
    public void handle(int orderId, int errorCode, String errorMsg) {
        System.out.println("Handle " + orderId + " " + errorCode + " " + errorMsg);
    }
}

