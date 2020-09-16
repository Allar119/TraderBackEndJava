package com.ib;
import com.ib.client.Contract;
import com.ib.client.Types;
import com.ib.controller.ApiConnection;
import com.ib.controller.ApiController;
import lombok.extern.slf4j.Slf4j;

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
        Contract contract = new Contract();
        contract.symbol("BMW");
        contract.secType("STK");
        contract.exchange("IBIS");
        contract.currency("EUR");

        m_controller.reqRealTimeBars(contract, Types.WhatToShow.TRADES, false, new RaivoRealTimeHandler());
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
