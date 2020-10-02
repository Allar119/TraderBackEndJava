package ee.project.trader.dto;

public class StrategyType {
    private String strategyName;
    private int strategyId;

    public String getStrategyNameWithId() {
        return strategyId + "-" + strategyName;
    }

    public void setStrategyNameWithId(String strategyNameWithId) {
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }
}