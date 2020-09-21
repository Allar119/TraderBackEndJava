package ee.project.trader;


import com.ib.client.Contract;
import ee.project.trader.handlers.ConnectionHandler;
import ee.project.trader.objects.ConnectionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getactivetickers/")
    public void getactivetickersList() {
    /* teeme andmebaasi päringu, et saada TICKER-ist kätte kõik aktiivsed tickerid,
    et need siis connectioni loomise järel automaataelt käivitada
     */
        traderService.getActiveTickersList();
    }



    @PostMapping("addorder/")
    public void addOrder() {
    /* teeme andmebaasis TICKER tabelis uue rea tickeri dataga, a-la AAPL, NVDA jne andes kaasa json-is
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.exchange("SMART");
        contract.currency("USD");

     */
        Ticker ticker = new Ticker("AAPL", "STK", "SMART", "USD");
        traderService.addTicker(ticker);
    }

    public void addPrice() {
       Price price = new Price("AAPL", "2020-09-17 14:23:30", 110.30, 110.30, 110.30, 110.30);

        traderService.addPrice(price);
    }
}
