let navBar = document.getElementById("nav");

let rightList = document.createElement("ul");
addToRightList("ORDERS", "index.html");
addToRightList("TICKERS", "tickers.html");
addToRightList("STRATEGY", "strategy.html");
addToRightList("SETTING", "settings.html", );

function addToRightList (name, url) {
    let listItem = document.createElement("li")
    let link = document.createElement("a");
    
    link.innerHTML = name;
    link.href = url;
    link.className = "links";
    
    listItem.append(link);
    rightList.append(listItem);
}

let leftList = document.createElement("ul");
leftList.className = "leftList"
addToLeftList("TRAD€R", "logo")
addToLeftList("xx" , "status_d", "status")

function addToLeftList(txt, className, id){
    let listItem = document.createElement("li")
    let link = document.createElement("p");
    
    link.innerHTML = txt;
    link.className = className;
    link.id = id;

    listItem.append(link);
    leftList.append(listItem);
}


function checkConnectionStatus(){
    s = document.getElementById("status");
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
                s.innerHTML = "TWS: DISCONNECTED"
                s.className = "status_d"
            }
            else if (jsonData.connected === true){
                s.innerHTML = "TWS: CONNECTED"
                s.className = "status_c"
            }            
        })
        .catch(function(err) {
            console.log(err);
            s.innerHTML = "SERVER ERROR"
            s.className = "status_d"
        })
}

function displayConnectionStatus(){
}

function createNavBar(){

    navBar.append(leftList);
    navBar.append(rightList);
    checkConnectionStatus();
}

createNavBar();
