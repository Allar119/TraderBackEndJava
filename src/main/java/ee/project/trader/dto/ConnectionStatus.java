package ee.project.trader.dto;

public class ConnectionStatus {
    private boolean isConnected;
    private String Account;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }
}
