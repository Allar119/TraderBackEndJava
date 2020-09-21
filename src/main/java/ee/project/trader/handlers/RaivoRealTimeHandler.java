package ee.project.trader.handlers;

import com.ib.client.Contract;
import com.ib.controller.ApiController;
import com.ib.controller.Bar;
import ee.project.trader.TraderService;

public class RaivoRealTimeHandler implements ApiController.IRealTimeBarHandler {

    private Contract contract;
    private TraderService service;

    public RaivoRealTimeHandler(Contract contract, TraderService service) {
        this.contract = contract;
        this.service = service;
    }

    @Override
    public void realtimeBar(Bar bar) {
        service.newBar(contract, bar);
    }
}
