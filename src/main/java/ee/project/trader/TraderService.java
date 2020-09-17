package ee.project.trader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;

    public void addTicker(Ticker ticker) {

        traderRepository.addTicker();
    }


    public void transferMoney(String fromAccount,
                              String toAccount,
                              BigDecimal amount) {
        BigDecimal fromAccountBalance = accountRepository.getBalance(fromAccount);
        if (fromAccountBalance.compareTo(amount) >= 0) {
            BigDecimal toAccountBalance = accountRepository.getBalance(toAccount);
            fromAccountBalance = fromAccountBalance.subtract(amount);
            toAccountBalance = toAccountBalance.add(amount);
            accountRepository.updateBalance(fromAccount, fromAccountBalance);
            accountRepository.updateBalance(toAccount, toAccountBalance);
        }
    }

    public BigDecimal getAccountBalance(String accountNumber) {
        System.out.println("AccountService getAccountBalance " + accountNumber);
        BigDecimal accountBalance = accountRepository.getBalance(accountNumber);
        history("", accountNumber, "balance request");
        return accountBalance;
    }

    public void makeDeposit(String accountNumber, BigDecimal deposit) {
        System.out.println("AccountService makeDeposit accountNumber: "+ accountNumber + " deposit: " + deposit);
        accountRepository.updateBalance(accountNumber, accountRepository.getBalance(accountNumber).add(deposit));
        history("", accountNumber, "deposit made: "+ deposit);
    }



}
