package ee.project.trader.handlers;

import com.ib.controller.ApiController;
import com.ib.controller.Bar;

public class RaivoRealTimeHandler implements ApiController.IRealTimeBarHandler {


    @Override
    public void realtimeBar(Bar bar) {
        System.out.println("realTimeBar käivitus");
        System.out.println(bar);
    }
}
