package ee.project.trader.rowmappers;

import ee.project.trader.Ticker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TickerRowMapper implements RowMapper<Ticker> {

    @Override
    public Ticker mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticker ticker = new Ticker();
        ticker.setSymbol(rs.getString("symbol"));
        ticker.setSecType(rs.getString("sec_type"));
        ticker.setExchange(rs.getString("exchange"));
        ticker.setCurrency(rs.getString("currency"));
        return ticker;
    }
}
