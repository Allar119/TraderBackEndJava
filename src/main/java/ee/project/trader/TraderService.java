package ee.project.trader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;

    public void addTicker(Ticker ticker) {
        System.out.println("traderService addTicker:");
        System.out.println(ticker.symbol);
        System.out.println(ticker.secType);
        System.out.println(ticker.exchange);
        System.out.println(ticker.currency);
        System.out.println("=====================================================");
        System.out.println();
        traderRepository.addTicker(ticker);
    }

    public void addPrice(Price price) {

        traderRepository.addPrice(price);
    }

    public void getActiveTickersList() {
        traderRepository.getActiveTickersList();
    }


    /*

    public void transferMoney(String fromAccount,
                              String toAccount,
                              BigDecimal amount) {
        BigDecimal fromAccountBalance = traderRepository.getBalance(fromAccount);
        if (fromAccountBalance.compareTo(amount) >= 0) {
            BigDecimal toAccountBalance = traderRepository.getBalance(toAccount);
            fromAccountBalance = fromAccountBalance.subtract(amount);
            toAccountBalance = toAccountBalance.add(amount);
            traderRepository.updateBalance(fromAccount, fromAccountBalance);
            traderRepository.updateBalance(toAccount, toAccountBalance);
        }
    }

    public BigDecimal getAccountBalance(String accountNumber) {
        System.out.println("AccountService getAccountBalance " + accountNumber);
        BigDecimal accountBalance = traderRepository.getBalance(accountNumber);
       // history("", accountNumber, "balance request");
        return accountBalance;
    }

    public void makeDeposit(String accountNumber, BigDecimal deposit) {
        System.out.println("AccountService makeDeposit accountNumber: "+ accountNumber + " deposit: " + deposit);
        traderRepository.updateBalance(accountNumber, traderRepository.getBalance(accountNumber).add(deposit));
        //history("", accountNumber, "deposit made: "+ deposit);
    }

*/

}
