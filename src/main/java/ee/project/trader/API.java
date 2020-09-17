package ee.project.trader;
import com.ib.client.Contract;
import com.ib.client.Types;
import com.ib.controller.ApiController;

import java.util.List;

public class API implements ApiController.IConnectionHandler {

    static API INSTANCE = new API();
    ApiController m_controller = new ApiController( this);

    public static void main(String[] args) {
        INSTANCE.run();
    }

    void run() {
        m_controller.connect( "127.0.0.1", 7400, 0, "");

    }


    @Override
    public void connected() {
        System.out.println("Connected käivitus");
        Contract contract = new Contract();
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.exchange("SMART");
        contract.currency("USD");

        m_controller.reqRealTimeBars(contract, Types.WhatToShow.TRADES, false, new RaivoRealTimeHandler());
        System.out.println("Connected peale realTimeBar väljakutsumist");
    }

    @Override
    public void disconnected() {

    }

    @Override
    public void accountList(List<String> list) {

    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void message(int id, int errorCode, String errorMsg) {

    }

    @Override
    public void show(String string) {

    }
}
