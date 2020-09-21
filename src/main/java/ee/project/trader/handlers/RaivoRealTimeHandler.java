package ee.project.trader.handlers;

import com.ib.client.Contract;
import com.ib.controller.ApiController;
import com.ib.controller.Bar;

public class RaivoRealTimeHandler implements ApiController.IRealTimeBarHandler {

    private Contract contract;

    public RaivoRealTimeHandler(Contract contract) {
        this.contract = contract;
    }

    @Override
    public void realtimeBar(Bar bar) {
        System.out.println("realTimeBar käivitus");

        System.out.println("TIME: " + bar.time());
        System.out.println("HIGH: " + bar.high());
        System.out.println("TIME FORMATTED" + bar.formattedTime());
        System.out.println(contract.symbol());
        System.out.println(bar);
    }
}
