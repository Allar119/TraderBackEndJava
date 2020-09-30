package ee.project.trader.handlers;

import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.client.OrderType;
import com.ib.client.Types;
import ee.project.trader.Ticker;
import ee.project.trader.TraderService;
import ee.project.trader.dto.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ConnectionHandler connectionHandler;
    @Autowired
    private TraderService traderService;

    public void addOrders(List<Integer> orderIdList) {
        int lastOrderId = orderIdList.get(orderIdList.size() - 1);
        for (int orderId : orderIdList) {

            OrderDetails order = traderService.getOrder(orderId);
            Ticker ticker = traderService.getTickerBySymbol(order.getSymbol());

            Order twsOrder = toTwsOrder(order, orderId == lastOrderId);
            Contract contract = toTwsContract(ticker);
            connectionHandler.placeOrModifyOrder(contract, twsOrder);
        }
    }

    private Order toTwsOrder(OrderDetails order, boolean isLast) {
        Order twsOrder = new Order();
        twsOrder.orderId(order.getId());
        twsOrder.parentId(order.getParentOrderId());
        twsOrder.action(order.getOrderAction());
        twsOrder.orderType(OrderType.get(order.getOrderType()));
        twsOrder.totalQuantity(order.getQuantity());
        twsOrder.transmit(false);
        if(order.getOrderType().equals("LMT")) {
            twsOrder.lmtPrice(order.getPrice());
        } else {
            twsOrder.auxPrice(order.getPrice());
        }
        if (isLast) {
            twsOrder.transmit(true);
        }
        return twsOrder;
    }

    private Contract toTwsContract(Ticker ticker) {
        Contract contract = new Contract();
        contract.symbol(ticker.getSymbol());
        contract.secType(ticker.getSecType());
        contract.currency(ticker.getCurrency());
        contract.exchange(ticker.getExchange());
        return contract;
    }
}
