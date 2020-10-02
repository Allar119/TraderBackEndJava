let navBar = document.getElementById("nav");

let pagesList = document.createElement("ul");
pagesList.className = "pages"

addTxt("TRAD€R", "logo", "logo")
addTxt("" , "status_d", "status")
addPages("ORDERS", "index.html");
addPages("TICKERS", "tickers.html");
addPages("STRATEGY", "strategy.html");
addPages("SETTINGS", "settings.html", );

function addPages (name, url) {
    let listItem = document.createElement("li")

    let link = document.createElement("a");    
    link.innerHTML = name;
    link.href = url;
    
    listItem.append(link);
    pagesList.append(listItem);
}

function addTxt(txt, className, id){
    let link = document.createElement("p");    
    link.innerHTML = txt;
    link.className = className;
    link.id = id;
    return link;
}

function displayConnectionStatus(jsonData){
    status = document.getElementById("status");
    account = document.getElementById("account");


    
}


async function checkConnectionStatus(){ 
    
    s = document.getElementById("status");
    a = document.getElementById("account");

    let response = await fetch('/getconnectionstatus');
    
    if (response.status != 200) {
        console.log(response.statusText);        
        
        s.innerHTML = "SERVER ERROR";
        a.innerHTML = "";

        //Recconect in 5 second
        await new Promise(resolve => setTimeout(resolve, 5000));

    } else {
        let message = await response.json();
        console.log(message);

        if (message.connected === true){
            s.innerHTML = "TWS: CONNECTED";
            s.style.color = "greenyellow"
            a.innerHTML = message.account;
            await new Promise(resolve => setTimeout(resolve, 10000));
        } else {
            s.innerHTML = "TWS: DISCONNECTED";
            s.style.color = "red"
            a.innerHTML ="";
            await new Promise(resolve => setTimeout(resolve, 1000));
        }
    }
    await checkConnectionStatus();
}


function createNavBar(){        
    navBar.append(addTxt("TRAD€R", "logo", "logo"));
    navBar.append(addTxt("", "status", "status"));
    navBar.append(addTxt("", "account", "account"));
    navBar.append(pagesList);
    checkConnectionStatus();
}

createNavBar();
