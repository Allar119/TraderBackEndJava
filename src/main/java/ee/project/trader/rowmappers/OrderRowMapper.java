package ee.project.trader.rowmappers;

import ee.project.trader.dto.OrderDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderDetails> {

    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

        OrderDetails order = new OrderDetails();

        order.setId(rs.getInt("id"));
        order.setAlgoId(rs.getInt("algo_id"));
        order.setSymbol(rs.getString("symbol"));
        order.setOrderType(rs.getString("order_type"));
        order.setQuantity(rs.getInt("quantity"));
        order.setLimitPrice(rs.getDouble("limit_price"));
        order.setStopLoss(rs.getDouble("stop_loss_price"));
        order.setProfitTaker(rs.getDouble("profit_taker_price"));
        order.setStatus(rs.getString("status"));
        order.setValid(rs.getString("valid"));
        order.setOrderAction(rs.getString("order_action"));

        return order;
    }
}
