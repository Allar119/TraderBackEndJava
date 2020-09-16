package com.ib;

import com.ib.controller.ApiController;
import com.ib.controller.Bar;

public class RaivoRealTimeHandler implements ApiController.IRealTimeBarHandler {
    @Override
    public void realtimeBar(Bar bar) {
        System.out.println(bar);
    }
}
