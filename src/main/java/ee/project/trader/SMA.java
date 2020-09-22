package ee.project.trader;

public class SMA {
    String symbol;
    int timeFrame;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }


    public SMA(String symbol, int timeFrame) {
        this.symbol = symbol;
        this.timeFrame = timeFrame;
    }
}
