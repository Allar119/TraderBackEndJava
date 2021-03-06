package ee.project.trader;


import com.ib.client.Contract;
import ee.project.trader.dto.*;
import ee.project.trader.handlers.ConnectionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TraderController {


    @Autowired
    private TraderService traderService;
    @Autowired
    private ConnectionHandler connectionHandler;

    @PostMapping("/connect")
    public ConnectionStatus connectToTws(@RequestBody ConnectionDetails connectionDetails) throws InterruptedException {
        return traderService.connectToTws(connectionDetails);
    }

    @PostMapping("/disconnect")
    public ConnectionStatus disconnectTws() throws InterruptedException {
        return traderService.disconnectTws();
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

    @DeleteMapping("/deleteticker/{id}")
    public void deleteTicker(@PathVariable("id") int id){
        traderService.deleteTicker(id);
    }

    @GetMapping("/getactivetickers")
    public List<Ticker> getactivetickersList() {
        return traderService.getTickerList();
        // teeme andmebaasi päringu, et saada TICKER-ist kätte kõik aktiivsed tickerid,
        // et need siis connectioni loomise järel automaataelt käivitada
    }

    @PostMapping("/submitorder")
    public void submitOrder(@RequestBody SubmitOrder order) {
        traderService.addOrder(order);
    /* teeme andmebaasis TICKER tabelis uue rea tickeri dataga, a-la AAPL, NVDA jne andes kaasa json-is
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.exchange("SMART");
        contract.currency("USD");

        Ticker ticker = new Ticker();
        ticker.setSymbol("AAPL");
        ticker.setSecType("STK");
        ticker.setExchange("SMART");
        ticker.setCurrency("USD");
        traderService.addTicker(ticker);

     */
    }

    @GetMapping("/getorderlist")
    public List<OrderDetails> getOrderList(){
        return traderService.getOrdersList();
    }

    @DeleteMapping("/deleteorder/{id}")
    public void deleteOrder(@PathVariable("id") int id){
        traderService.deleteOrder(id);
    }

    @GetMapping("/gettickersymbols")
    public List<DropDownOption> getSymbolList(){
        return traderService.getSymbolList();
    }

    @GetMapping("/getstrategyinfo")
    public List<StrategyDetails> getStrategyDetails(){
        return traderService.getStrategyDetails();
    }

    @GetMapping("/getstrategies")
    public List<DropDownOption> getStrategies(){
        return traderService.getStrategies();
    }

    @GetMapping("/getconnectionstatus")
    public ConnectionStatus checkConnectionStatusc() throws InterruptedException {
        return traderService.checkConnectionStatus();
    }

    @GetMapping("/gettickerstrategy/{symbol}")
    public StrategyDetails getTickerStrategy (@PathVariable String symbol) {
        return traderService.getTickerStrategy(symbol);
    }
}
