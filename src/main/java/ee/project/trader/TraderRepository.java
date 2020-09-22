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

      /*  System.out.println("TraderRepository addPrice:");
        System.out.println(price.symbol);
        System.out.println(price.priceMax);
        System.out.println("*****************************************************");
        System.out.println();


       */
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

        String sql = "select avg(price_open) over (rows between :timeFrame preceding and 0 following) from price_history where symbol = :symbol ORDER BY ID DESC LIMIT 1;";

        double simoav =  jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return simoav;

        // select avg(price_open) over (rows between 60 preceding and 0 following) from price_history where symbol = 'SOXL' ORDER BY ID DESC LIMIT 1
       // return jdbcTemplate.query(sql, paramMap);

    }

    public void insertOrder (Order order) {
        // Order(String symbol, String orderType, int quantity, double limitPrice, double stopLossPrice, double profitTakerPrice)

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("algoId", order.algoId);
        paramMap.put("symbol", order.symbol);
        paramMap.put("orderType", order.orderType);
        paramMap.put("quantity", order.quantity);
        paramMap.put("limitPrice", order.limitPrice);
        paramMap.put("stopLossPrice", order.stopLossPrice);
        paramMap.put("profitTakerPrice", order.profitTakerPrice);
        paramMap.put("status", "pending");

        String sql = "INSERT INTO order (algo_id, symbol, order_type, quantity, limit_price, stop_loss_price, profit_taker_price, status) values (" +
                ":algoId, " +
                ":symbol, " +
                ":orderType, " +
                ":quantity, " +
                ":limitPrice, " +
                ":stopLossPrice, " +
                ":profitTakerPrice, " +
                ":status)";
        jdbcTemplate.update(sql, paramMap);



    }



}





