package ee.project.trader.handlers;
import com.ib.client.Contract;
import com.ib.controller.ApiController;
import ee.project.trader.objects.Connect;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionHandler implements ApiController.IConnectionHandler {

    //static API INSTANCE = new API();
    ApiController m_controller = new ApiController( this);

    //public static void main(String[] args) {
    //    INSTANCE.run();
    //}

    public void run(String hostIp, int port, int clientId, String connectionOpts) {
        m_controller.connect( hostIp, port, clientId, connectionOpts);
        //m_controller.connect( "127.0.0.1", 7400, 0, "");
    }

    @Override
    public void connected() {
        System.out.println("Connected käivitus");

        Contract contract = new Contract();
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.exchange("SMART");
        contract.currency("USD");

        m_controller.reqMktDataType(3); //Select market Data type 1=Live, 2=Frozen, 3=Delayed, 4=Delayed and frozen
        m_controller.reqTopMktData(contract, "221", false, false, new TopMktDataHandler());
        //m_controller.reqRealTimeBars(contract, Types.WhatToShow.TRADES, false, new RaivoRealTimeHandler());
        //System.out.println("Connected peale realTimeBar väljakutsumist");
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
