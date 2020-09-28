package ee.project.trader.rowmappers;

import ee.project.trader.dto.StrategyDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrategyRowMapper implements RowMapper<StrategyDetails> {
    @Override
    public StrategyDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        StrategyDetails sd = new StrategyDetails();
        sd.setSymbol(rs.getString("symbol"));
        sd.setMarketPrice(rs.getDouble("market_price"));
        sd.setPriceRapid(rs.getString("price_rapid"));
        sd.setPriceQuick(rs.getString("price_quick"));
        sd.setPriceSlow(rs.getString("price_slow"));
        sd.setRapidQuick(rs.getString("rapid_quick"));
        sd.setRapidSlow(rs.getString("rapid_slow"));
        sd.setQuickSlow(rs.getString("quick_slow"));
        return sd;
    }
}
