
var navBar = document.getElementById("nav");

var logo = document.createElement("label");
logo.innerHTML = "TRAD€R";
logo.className ="logo";

var homePage = document.createElement("a");
homePage.innerHTML = "HOME";
homePage.href = "index.html";

var tickerPage = document.createElement("a");
tickerPage.innerHTML = "TICKERS";
tickerPage.href = "tickers.html";

var settingsPage = document.createElement("a");
settingsPage.innerHTML = "SETTINGS";
settingsPage.href = "settings.html";

var stradegyPage = document.createElement("a");
stradegyPage.innerHTML = "STRADEGY"

var items = new Array(homePage, tickerPage, settingsPage);

function createNavBar(){
    var list = document.createElement("ul");
    for(i = 0; i < 3; i++){    
        var listItem = document.createElement("li");
        listItem.append(items[i]);
        list.append(listItem);
    }
    navBar.append(logo);
    navBar.append(list);
}

createNavBar();
