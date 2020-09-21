package ee.project.trader;


import com.ib.client.Contract;
import ee.project.trader.handlers.ConnectionHandler;
import ee.project.trader.dto.ConnectionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TraderController {


    @Autowired
    private TraderService traderService;

    @Autowired
    private ConnectionHandler connectionHandler;

    //Start connection

    @PostMapping("/connect")
    public boolean startConnection(@RequestBody ConnectionDetails connectionDetails) throws InterruptedException {
        connectionHandler.run(connectionDetails.getIp(), connectionDetails.getPort(), connectionDetails.getClientId(), "");
        Thread.sleep(1000); //wait 1s
        return connectionHandler.isConnected();
    }

    @PostMapping("/addticker")
    public void addTicker(@RequestBody Ticker ticker){

        Contract contract = new Contract();
        contract.symbol(ticker.getSymbol());
        contract.secType(ticker.getSecType());
        contract.exchange(ticker.getExchange());
        contract.currency(ticker.getCurrency());

        connectionHandler.addTicker(contract);
        traderService.addTicker(ticker);

    }

    @GetMapping("/getactivetickers")
    public List<Ticker> getactivetickersList() {
        return traderService.getTickerList();
        // teeme andmebaasi päringu, et saada TICKER-ist kätte kõik aktiivsed tickerid,
        // et need siis connectioni loomise järel automaataelt käivitada
    }



    @PostMapping("addorder/")
    public void addOrder() {
    /* teeme andmebaasis TICKER tabelis uue rea tickeri dataga, a-la AAPL, NVDA jne andes kaasa json-is
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.exchange("SMART");
        contract.currency("USD");
     */
        Ticker ticker = new Ticker();
        ticker.setSymbol("AAPL");
        ticker.setSecType("STK");
        ticker.setExchange("SMART");
        ticker.setCurrency("USD");
        traderService.addTicker(ticker);
    }


}
