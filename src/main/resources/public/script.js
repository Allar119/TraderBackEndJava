let navBar = document.getElementById("nav");

let logo = document.createElement("label");
logo.innerHTML = "TRAD€R";
logo.className ="logo";

let status = document.createElement("a");


let items =[];
addLinkToNav("ORDERS", "index.html");
addLinkToNav("TICKERS", "tickers.html");
addLinkToNav("STRATEGY", "strategy.html");
addLinkToNav("SETTING", "settings.html");

function addLinkToNav (name, url) {
    let link = document.createElement("a");
    link.innerHTML = name;
    link.href = url;
    link.className = "links";
    items.push(link);
}

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
            if (jsonData.connected === false){
                status.innerHTML = "TWS: DISCONNECTED"
                status.className = "status_d"
            }
            else{
                status.innerHTML = "TWS: CONNECTED"
                status.className = "status_c"
            }            
            //account: null
            //connected: false

        })
        .catch(function(err) {
            console.log(err);
        })
}

function displayConnectionStatus(){

}

function createNavBar(){
    let list = document.createElement("ul");
    for(let i = 0; i < items.length; i++){
        let listItem = document.createElement("li");
        listItem.append(items[i]);
        list.append(listItem);
    }
    navBar.append(logo);
    navBar.append(status);
    navBar.append(list);
    checkConnectionStatus();
}

createNavBar();
