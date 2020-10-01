package ee.project.trader;

import ee.project.trader.dto.*;
import ee.project.trader.rowmappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public double getSMA(SMA sma) {
        int smaFrame = sma.timeFrame * 12;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("timeFrame", smaFrame);
        paramMap.put("symbol", sma.symbol);

        String sql = "select avg(price_open) over (rows between :timeFrame preceding and 0 following) from price_history where symbol = :symbol ORDER BY ID DESC LIMIT 1;";

        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);

        // select avg(price_open) over (rows between 60 preceding and 0 following) from price_history where symbol = 'SOXL' ORDER BY ID DESC LIMIT 1
        // return jdbcTemplate.query(sql, paramMap);

    }

    public String getAction(String symbol, String strategy) {
        //  select price_quick from strategy where symbol='TQQQ' ORDER BY ID DESC LIMIT 1;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("symbol", symbol);
        paramMap.put("strategy", strategy);
        String sql = "SELECT * FROM strategy WHERE symbol = :symbol ORDER BY ID DESC LIMIT 1;";

        String action = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        System.out.println(action);
        return action;
    }


    public int insertOrder(SubmitOrder order) {

        String sql_parent = "INSERT INTO order_table (algo_id, parent_order, symbol, order_type, quantity, price, status, valid, order_action) values (" +
                ":algoId, " +
                ":parent_order, " +
                ":symbol, " +
                ":orderType, " +
                ":quantity, " +
                ":price, " +
                ":status, " +
                ":valid, " +
                ":orderAction)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("algoId", order.getAlgoId());
        paramMap.put("parent_order", null);
        paramMap.put("symbol", order.getSymbol());
        paramMap.put("orderType", order.getOrderType());
        paramMap.put("quantity", order.getQuantity());
        paramMap.put("price", order.getLimitPrice());
        paramMap.put("status", "submitted");
        paramMap.put("valid", order.getValid());
        paramMap.put("orderAction", order.getOrderAction());

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql_parent, new MapSqlParameterSource(paramMap), holder, new String[]{"id"});
        return Objects.requireNonNull(holder.getKey()).intValue();

    }


    public int insertProfitTaker(SubmitOrder order, int parentOrderId) {


        String sql_profitTaker = "INSERT INTO order_table (algo_id, parent_order, symbol, order_type, quantity, price, status, valid, order_action) values (" +
                ":algoId, " +
                ":parent_order, " +
                ":symbol, " +
                ":orderType, " +
                ":quantity, " +
                ":price, " +
                ":status, " +
                ":valid, " +
                ":orderAction)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("algoId", order.getAlgoId());
        paramMap.put("parent_order", parentOrderId);
        paramMap.put("symbol", order.getSymbol());
        paramMap.put("orderType", "LMT");
        paramMap.put("quantity", order.getQuantity());
        paramMap.put("price", order.getProfitTaker());
        paramMap.put("status", "submitted");
        paramMap.put("valid", order.getValid());
        paramMap.put("orderAction", order.getOrderAction());

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql_profitTaker, new MapSqlParameterSource(paramMap), holder, new String[]{"id"});
        return Objects.requireNonNull(holder.getKey()).intValue();

    }


    public int insertStopLoss(SubmitOrder order, int parentOrderId) {

        String sql_stopLoss = "INSERT INTO order_table (algo_id, parent_order, symbol, order_type, quantity, price, status, valid, order_action) values (" +
                ":algoId, " +
                ":parent_order, " +
                ":symbol, " +
                ":orderType, " +
                ":quantity, " +
                ":price, " +
                ":status, " +
                ":valid, " +
                ":orderAction)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("algoId", order.getAlgoId());
        paramMap.put("parent_order", parentOrderId);
        paramMap.put("symbol", order.getSymbol());
        paramMap.put("orderType", "STP");
        paramMap.put("quantity", order.getQuantity());
        paramMap.put("price", order.getStopLoss());
        paramMap.put("status", "submitted");
        paramMap.put("valid", order.getValid());
        paramMap.put("orderAction", order.getOrderAction());

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql_stopLoss, new MapSqlParameterSource(paramMap), holder, new String[]{"id"});
        return Objects.requireNonNull(holder.getKey()).intValue();

    }


    /* OLD INSERT ORDER
    public void insertOrder (SubmitOrder order) {
        String sql = "INSERT INTO order_table (algo_id, symbol, order_type, quantity, limit_price, stop_loss_price, profit_taker_price, status, valid, order_action) values (" +
                ":algoId, " +
                ":symbol, " +
                ":orderType, " +
                ":quantity, " +
                ":limitPrice, " +
                ":stopLoss, " +
                ":profitTaker, " +
                ":status, " +
                ":valid, " +
                ":orderAction)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("algoId", order.getAlgoId());
        paramMap.put("symbol", order.getSymbol());
        paramMap.put("orderType", order.getOrderType());
        paramMap.put("quantity", order.getQuantity());
        paramMap.put("limitPrice", order.getLimitPrice());
        paramMap.put("stopLoss", order.getStopLoss());
        paramMap.put("profitTaker", order.getProfitTaker());
        paramMap.put("status", "submitted");
        paramMap.put("valid", order.getValid());
        paramMap.put("orderAction", order.getOrderAction());

        jdbcTemplate.update(sql, paramMap);

    }
*/
    public void insertStrategyLineToTicker(StrategyLine strategyLine) {

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("symbol", strategyLine.symbol);
        paramMap.put("marketPrice", strategyLine.marketPrice);
        paramMap.put("rapid", strategyLine.rapidSMA);
        paramMap.put("quick", strategyLine.quickSMA);
        paramMap.put("slow", strategyLine.slowSMA);
        paramMap.put("price_rapid", strategyLine.price_rapidSMA);
        paramMap.put("price_quick", strategyLine.price_quickSMA);
        paramMap.put("price_slow", strategyLine.price_slowSMA);
        paramMap.put("rapid_quick", strategyLine.rapidSMA_quickSMA);
        paramMap.put("rapid_slow", strategyLine.rapidSMA_slowSMA);
        paramMap.put("quick_slow", strategyLine.quickSMA_slowSMA);


        String sql = "UPDATE ticker SET market_price = :marketPrice, rapid = :rapid, quick = :quick," +
                " slow = :slow, price_rapid = :price_rapid, price_quick = :price_quick," +
                " price_slow = :price_slow, rapid_quick = :rapid_quick, rapid_slow = :rapid_slow," +
                " quick_slow = :quick_slow where symbol = :symbol";
        jdbcTemplate.update(sql, paramMap);
    }

    public void changeOrderStatus(int id, String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("status", status);
        String sql = "UPDATE order_table SET status = :status where id = :id";
        jdbcTemplate.update(sql, paramMap);
        System.out.println("Order Status updated");
    }

    public void insertStrategyLine(StrategyLine strategyLine) {
/*
            <column name="time" type="BIGINT"/>
            <column name="symbol" type="TEXT"/>
            <column name="market_price" type="FLOAT"/>
            <column name="trend" type="FLOAT"/>
            <column name="quick" type="FLOAT"/>
            <column name="slow" type="FLOAT"/>
            <column name="price_trend" type="TEXT"/>
            <column name="price_quick" type="TEXT"/>
            <column name="price_slow" type="TEXT"/>
            <column name="trend_quick" type="TEXT"/>
            <column name="trend_slow" type="TEXT"/>
            <column name="quick_slow" type="TEXT"/>
 */

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("time", strategyLine.time);
        paramMap.put("symbol", strategyLine.symbol);
        paramMap.put("marketPrice", strategyLine.marketPrice);
        paramMap.put("rapid", strategyLine.rapidSMA);
        paramMap.put("quick", strategyLine.quickSMA);
        paramMap.put("slow", strategyLine.slowSMA);
        paramMap.put("price_rapid", strategyLine.price_rapidSMA);
        paramMap.put("price_quick", strategyLine.price_quickSMA);
        paramMap.put("price_slow", strategyLine.price_slowSMA);
        paramMap.put("rapid_quick", strategyLine.rapidSMA_quickSMA);
        paramMap.put("rapid_slow", strategyLine.rapidSMA_slowSMA);
        paramMap.put("quick_slow", strategyLine.quickSMA_slowSMA);

        String sql = "INSERT INTO strategy (time, symbol, market_price, rapid, quick, slow, price_rapid, price_quick, price_slow, rapid_quick, rapid_slow, quick_slow) values (" +
                ":time, " +
                ":symbol, " +
                ":marketPrice, " +
                ":rapid, " +
                ":quick, " +
                ":slow, " +
                ":price_rapid, " +
                ":price_quick, " +
                ":price_slow, " +
                ":rapid_quick, " +
                ":rapid_slow, " +
                ":quick_slow)";
        jdbcTemplate.update(sql, paramMap);
    }

    public void deleteTicker(int id) {
        String sql = "DELETE FROM ticker WHERE id = :id";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM order_table WHERE id = :id";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);
    }

    public List<Ticker> getTickerList() {
        String sql = "SELECT * FROM ticker ORDER BY id";
        return jdbcTemplate.query(sql, new HashMap<>(), new TickerRowMapper());
    }

    public List<OrderDetails> getOrdersList() {
        String sql = "SELECT * FROM order_table ORDER BY id";
        return jdbcTemplate.query(sql, new HashMap<>(), new OrderRowMapper());
    }

    public List<OrderDetails> getSubmittedOrdersList() {
        String sql = "SELECT * FROM order_table WHERE status = 'submitted' ORDER BY id";
        return jdbcTemplate.query(sql, new HashMap<>(), new OrderRowMapper());
    }

    public int getSubmittedOrdersFirstId() {
        String sql = "SELECT id FROM order_table WHERE status = 'submitted' ORDER BY ID ASC LIMIT 1";
        Integer id = jdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
        return id;
    }

    public List<TickerSymbol> getSymbolList() {
        String sql = "SELECT symbol FROM ticker ORDER BY symbol";
        return jdbcTemplate.query(sql, new HashMap<>(), new SymbolRowMapper());
    }

    public List<StrategyDetails> getStrategyDetails() {
        String sql = "SELECT * FROM ticker ORDER BY symbol";
        return jdbcTemplate.query(sql, new HashMap<>(), new StrategyRowMapper());
    }

    public Ticker getTickerBySymbol(String symbol) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("symbol", symbol);

        String sql = "SELECT * FROM ticker WHERE symbol = :symbol";
        return jdbcTemplate.queryForObject(sql, paramMap, new TickerRowMapper());
    }

    public OrderDetails getOrder(int id) {
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("id", id);

        String sql = "SELECT * FROM order_table WHERE id = :id";
        return jdbcTemplate.queryForObject(sql, paramMap, new OrderRowMapper());
    }

    public List<StrategyType> getStrategies() {
        String sql = "SELECT strategy_name, strategy_id FROM strategy_selection";
        return jdbcTemplate.query(sql, new HashMap<>(), new StrategiesRowMapper());
    }

    public StrategyDetails getTickerStrategy(String symbol) {
        String sql = "SELECT * FROM ticker WHERE symbol = :symbol";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("symbol", symbol);
        return jdbcTemplate.queryForObject(sql, paramMap, new StrategyRowMapper());
    }
}





