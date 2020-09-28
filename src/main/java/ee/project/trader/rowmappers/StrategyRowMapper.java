package ee.project.trader.rowmappers;

import ee.project.trader.dto.StrategyDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrategyRowMapper implements RowMapper<StrategyDetails> {
    @Override
    public StrategyDetails mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
