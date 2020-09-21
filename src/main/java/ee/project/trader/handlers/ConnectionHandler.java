package ee.project.trader.handlers;
import com.ib.client.Contract;
import com.ib.client.Types;
import com.ib.controller.ApiController;
import ee.project.trader.Ticker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionHandler implements ApiController.IConnectionHandler {

    private boolean isConnected;
    ApiController m_controller = new ApiController( this);

    public static void placeOrModifyOrder(Ticker initializeContract, NewOrder o, OrderHandler orderHandler) {
    }

    public void run(String hostIp, int port, int clientId, String connectionOpts) {
        m_controller.connect( hostIp, port, clientId, connectionOpts);
    }

    public void addTicker(Contract contract){
        System.out.println("addTicker käivitus....");

       // m_controller.reqMktDataType(1); //Select market Data type 1=Live, 2=Frozen, 3=Delayed, 4=Delayed and frozen
       // m_controller.reqTopMktData(contract, "221", false, false, new TopMktDataHandler());
        m_controller.reqRealTimeBars(contract, Types.WhatToShow.TRADES, false, new RaivoRealTimeHandler(contract));
    }

    @Override
    public void connected() {
        isConnected = true;
        System.out.println("Connected");
    }

    @Override
    public void disconnected() {
        isConnected = false;
        System.out.print("Disconnected");

    }

    @Override
    public void accountList(List<String> list) {
        System.out.print("Accounts: " + list);

    }

    @Override
    public void error(Exception e) {
        System.out.print("Exception: " + e);

    }

    @Override
    public void message(int id, int errorCode, String errorMsg) {
        System.out.printf("Message: id:%s; Code:%s; %s", id, errorCode, errorMsg);
    }

    @Override
    public void show(String string) {
    }

    public boolean isConnected() {
        return isConnected;
    }
}
