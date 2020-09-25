package ee.project.trader.rowmappers;

import ee.project.trader.dto.TickerSymbol;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SymbolRowMapper implements RowMapper<TickerSymbol> {
    @Override
    public TickerSymbol mapRow(ResultSet rs, int rowNum) throws SQLException {

        TickerSymbol symbol = new TickerSymbol();
        symbol.setSymbol(rs.getString("symbol"));
        return symbol;
    }
}
