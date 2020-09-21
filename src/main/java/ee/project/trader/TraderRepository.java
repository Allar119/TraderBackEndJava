package ee.project.trader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

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
      //  paramMap.put("account_number", accountNumber);
        //paramMap.put("bank_customer_id", id);
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
}





