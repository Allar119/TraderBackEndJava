package ee.project.trader.objects;

public class ConnectionDetails {
    private String ip;
    private int port;
    private int clientId;
    private String connectionOpt;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getConnectionOpt() {
        return connectionOpt;
    }

    public void setConnectionOpt(String connectionOpt) {
        this.connectionOpt = connectionOpt;
    }
}
