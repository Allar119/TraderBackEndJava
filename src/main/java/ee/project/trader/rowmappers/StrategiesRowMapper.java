package ee.project.trader.rowmappers;

import ee.project.trader.dto.StrategyType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrategiesRowMapper implements RowMapper<StrategyType> {

    @Override
    public StrategyType mapRow(ResultSet rs, int rowNum) throws SQLException {

        StrategyType st = new StrategyType();
        st.setStrategyName(rs.getString("strategy_name"));
        st.setStrategyId(rs.getInt("strategy_id"));
        return st;
    }
}
