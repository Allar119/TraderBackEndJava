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
        order.setPrice(rs.getDouble("price"));
        order.setStatus(rs.getString("status"));
        order.setValid(rs.getString("valid"));
        order.setOrderAction(rs.getString("order_action"));

        return order;
    }
}
