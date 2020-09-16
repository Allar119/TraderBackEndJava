package com.ib;

import com.ib.controller.ApiController;
import com.ib.controller.Bar;
import lombok.extern.slf4j.Slf4j;

public class RaivoRealTimeHandler implements ApiController.IRealTimeBarHandler {
    @Override
    public void realtimeBar(Bar bar) {
        System.out.println(bar);
    }
}
