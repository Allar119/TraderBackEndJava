package ee.project.trader;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TraderRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addTicker(Ticker ticker) {
        System.out.println("TraderRepository addTicker: " + "Ticker");
        Map<String, Object> paramMap = new HashMap<>();
      //  paramMap.put("account_number", accountNumber);
        //paramMap.put("bank_customer_id", id);
        paramMap.put("balance", 0);
        String sql = "INSERT INTO bank_account (account_number, bank_customer_id, balance) values (" +
                ":account_number, " +
                ":bank_customer_id, " +
                ":balance)";
        jdbcTemplate.update(sql, paramMap);

    }

    public void addPrice(Price price) {

        System.out.println("TraderRepository addPrice");

    }


    public BigDecimal getBalance(String account) {
        System.out.println("AccountRepository getBalance account: " + account);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountNumber", account);
        String sql = "SELECT balance FROM bank_account " +
                "WHERE account_number = :accountNumber";
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);

    }

    public void updateBalance(String account, BigDecimal balance) {
        System.out.println("AccountRepository updateBalance account: " + account + " balance: " + balance);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("balance", balance);
        paramMap.put("accountNumber", account);
        String sql = "UPDATE bank_account SET balance = :balance WHERE " +
                "account_number = :accountNumber";
        jdbcTemplate.update(sql, paramMap);
    }

    public void createCustomer(String name, Integer personalId) {
        System.out.println("AccountRepository createCustomer name: " + name + " personalId: " + personalId);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("client_name", name);
        paramMap.put("client_id", personalId);
        String sql = "INSERT INTO bank_customer (name, personal_code) values (" +
                ":client_name, " +
                ":client_id)";
        jdbcTemplate.update(sql, paramMap);
    }

    public void createAccount(String accountNumber, Integer id) {
        System.out.println("AccountRepository createAccount account: " + accountNumber + " id: " + id);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_number", accountNumber);
        paramMap.put("bank_customer_id", id);
        paramMap.put("balance", 0);
        String sql = "INSERT INTO bank_account (account_number, bank_customer_id, balance) values (" +
                ":account_number, " +
                ":bank_customer_id, " +
                ":balance)";
        jdbcTemplate.update(sql, paramMap);
    }

    public Integer getIdBasedAccountNumber(String accountNumber) {
        System.out.println("AccountRepository getIdBasedAccountNumber accountNumber: " + accountNumber);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_number", accountNumber);
        String sql = "SELECT id FROM bank_account WHERE account_number= :account_number";
        System.out.println("Requested Id is: " + jdbcTemplate.queryForObject(sql, paramMap, Integer.class));
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void historyUpdate(String time, Integer accountId, String name, String account, String action) {
        System.out.println("AccountRepository historyUpdate ");
        System.out.print(" time: " + time);
        System.out.print(" accountId: " + accountId);
        System.out.print(" name: " + name);
        System.out.print(" account: " + account);
        System.out.print(" action: " + action);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("time", time);
        paramMap.put("accountId", accountId);
        paramMap.put("name", name);
        paramMap.put("account", account);
        paramMap.put("action", action);

        String sql = "INSERT INTO history (time, account_id, name, account_number, action) values (" +
                ":time, " +
                ":accountId, " +
                ":name, " +
                ":account, " +
                ":action)";
        jdbcTemplate.update(sql, paramMap);
    }





    /*
    public List<Account> getAllBalances() {
        // List<Account> üles tagastusse
        System.out.println("AccountRepository getAllBalances");
        String sql = "SELECT * FROM bank_account ";
        List<Account> accounts = jdbcTemplate.query(sql, new HashMap<>(), new AccountRowMapper());
        return accounts;
    }
*/

}





