var navBar = document.getElementById("nav");


var logo = document.createElement("label");
logo.innerHTML = "TRAD€R";
logo.className ="logo";

var items =[];

var homePage = document.createElement("a");
homePage.innerHTML = "HOME";
homePage.href = "index.html";
items.push(homePage);

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
