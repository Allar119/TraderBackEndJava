var navBar = document.getElementById("nav");


var logo = document.createElement("label");
logo.innerHTML = "TRAD€R";
logo.className ="logo";

var items =[];

var orderPage = document.createElement("a");
orderPage.innerHTML = "ORDERS";
orderPage.href = "index.html";
items.push(orderPage);

var tickerPage = document.createElement("a");
tickerPage.innerHTML = "TICKERS";
tickerPage.href = "tickers.html";
items.push(tickerPage);

var strategyPage = document.createElement("a");
strategyPage.innerHTML = "STRATEGY";
strategyPage.href = "strategy.html";
items.push(strategyPage);

var settingsPage = document.createElement("a");
settingsPage.innerHTML = "SETTINGS";
settingsPage.href = "settings.html";
items.push(settingsPage);

//var items = new Array(homePage, tickerPage, settingsPage);

function checkConnectionStatus(){
    fetch('/getconnectionstatus', {
        method: 'GET',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(function(response) {
            return response.json();
        })
        .then(function(jsonData) {
            console.log(jsonData);
        })
        .catch(function(err) {
            console.log(err);
        })
}

function createNavBar(){
    var list = document.createElement("ul");
    for(i = 0; i < items.length; i++){
        var listItem = document.createElement("li");
        listItem.append(items[i]);
        list.append(listItem);
    }
    navBar.append(logo);
    navBar.append(list);
}

createNavBar();
checkConnectionStatus();