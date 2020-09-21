package ee.project.trader;

import ee.project.trader.rowmappers.TickerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TraderRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addTicker(Ticker ticker) {
        System.out.println("TraderRepository addTicker: ");
        System.out.println(ticker.symbol);
        System.out.println(ticker.secType);
        System.out.println(ticker.exchange);
        System.out.println(ticker.currency);
        System.out.println("*****************************************************");
        System.out.println();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("symbol", ticker.symbol);
        paramMap.put("secType", ticker.secType);
        paramMap.put("exchange", ticker.exchange);
        paramMap.put("currency", ticker.currency);

        String sql = "INSERT INTO ticker (symbol, sec_type, exchange, currency) values (" +
                ":symbol, " +
                ":secType, " +
                ":exchange, " +
                ":currency)";
        jdbcTemplate.update(sql, paramMap);
    }

    public void addPrice(Price price) {

        System.out.println("TraderRepository addPrice:");
        System.out.println(price.symbol);
        System.out.println(price.priceMax);
        System.out.println("*****************************************************");
        System.out.println();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("timestamp", price.timestamp);
        paramMap.put("symbol", price.symbol);
        paramMap.put("priceOpen", price.priceOpen);
        paramMap.put("priceClose", price.priceClose);
        paramMap.put("priceMax", price.priceMax);
        paramMap.put("priceMin", price.priceMin);
        String sql = "INSERT INTO price_history (timestamp, symbol, price_open, price_close, price_max, price_min) values (" +
                ":timestamp, " +
                ":symbol, " +
                ":priceOpen, " +
                ":priceClose, " +
                ":priceMax, " +
                ":priceMin)";
        jdbcTemplate.update(sql, paramMap);
    }

    public List<Ticker> getTickerList() {
        String sql = "SELECT * FROM ticker ORDER BY symbol";
        return jdbcTemplate.query(sql, new HashMap<>(), new TickerRowMapper());
    }

    public double getSMA(SMA sma) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("timeFrame", sma.timeFrame);
        paramMap.put("symbol", sma.symbol);

        String sql = "SELECT avg(price_open) OVER (rows between (period) preceding and 0 following) from price_history " +
                "where symbol = (symbol) ORDER BY ID DESC LIMIT 1 values (" +
                ":timeFrame, " +
                ":symbol)";

        // select avg(price_open) over (rows between 60 preceding and 0 following) from price_history where symbol = 'SOXL' ORDER BY ID DESC LIMIT 1
       // return jdbcTemplate.query(sql, paramMap);
        return 0;
    }
}





