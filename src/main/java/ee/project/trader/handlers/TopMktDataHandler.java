package ee.project.trader.handlers;

import com.ib.client.TickAttrib;
import com.ib.client.TickType;
import com.ib.controller.ApiController;

public class TopMktDataHandler implements ApiController.ITopMktDataHandler {
    @Override
    public void tickPrice(TickType tickType, double price, TickAttrib attribs) {
        System.out.printf("Tick Price --> Tick Type: %s, Price: %s\n",tickType, price);
    }

    @Override
    public void tickSize(TickType tickType, int size) {
        System.out.printf ("Tick Size --> Tick Type: %s, Size: %s\n", tickType, size);
    }

    @Override
    public void tickString(TickType tickType, String value) {
        System.out.printf("Tick String --> Tick Type: %s, Value: %s\n", tickType, value);
    }

    @Override
    public void tickSnapshotEnd() {
    }

    @Override
    public void marketDataType(int marketDataType) {
        System.out.printf("Market Data Type: %s\n", marketDataType);
    }

    @Override
    public void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions) {
        System.out.printf("Tick Params --> TickerID: %s\n", tickerId);
    }
}
