package ee.project.trader.handlers;
import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.client.Types;
import com.ib.controller.ApiController;
import ee.project.trader.Ticker;
import ee.project.trader.TraderService;
import ee.project.trader.dto.ConnectionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionHandler implements ApiController.IConnectionHandler {

    @Autowired
    private TraderService traderService;

    private boolean isConnected;
    private String account;
    ApiController m_controller = new ApiController( this);

    public void placeOrModifyOrder(Contract contract, Order o, OrderHandler orderHandler) {
        m_controller.placeOrModifyOrder(contract, o, orderHandler);
    }

    public void run(String hostIp, int port, int clientId, String connectionOpts) {
        m_controller.connect( hostIp, port, clientId, connectionOpts);
    }

    public void disconnectTws(){
        m_controller.disconnect();
    }

    public void addTicker(Contract contract){
        System.out.println("addTicker käivitus....");

       // m_controller.reqMktDataType(1); //Select market Data type 1=Live, 2=Frozen, 3=Delayed, 4=Delayed and frozen
       // m_controller.reqTopMktData(contract, "221", false, false, new TopMktDataHandler());
        m_controller.reqRealTimeBars(contract, Types.WhatToShow.TRADES, false, new RaivoRealTimeHandler(contract, traderService));
    }

    @Override
    public void connected() {
        isConnected = true;
        System.out.println("Connected");
        traderService.getTickerList().forEach(ticker -> {

            Contract contract = new Contract();
            contract.symbol(ticker.getSymbol());
            contract.secType(ticker.getSecType());
            contract.exchange(ticker.getExchange());
            contract.currency(ticker.getCurrency());

            addTicker(contract);
        });
        // korjan tickereid, sma-d käima
        //tsekkan, kas on OrderDB-s kirje orderi kohta
        //if yes, käivitan
    }

    @Override
    public void disconnected() {
        isConnected = false;
        account = null;
        System.out.println("Disconnected");
    }

    @Override
    public void accountList(List<String> list) {
        account = list.get(0);
        System.out.printf("Accounts: %s\n", list);
    }

    @Override
    public void error(Exception e) {
        System.out.println("Exception: " + e);
    }

    @Override
    public void message(int id, int errorCode, String errorMsg) {
        System.out.printf("Message: id:%s, Code:%s, %s.\n", id, errorCode, errorMsg);    }

    @Override
    public void show(String string) {
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getAccount() {
        return account;
    }
}
