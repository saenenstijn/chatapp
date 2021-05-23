//verstuurde vrienden via http request
let xHRObjectFriends = new XMLHttpRequest();
let xHRObjectAdd = new XMLHttpRequest();


//vriendenlijst ophalen
function getFriends() {
    xHRObjectFriends.open("GET", "Controller?action=GetFriends");
    xHRObjectFriends.send();
    xHRObjectFriends.onreadystatechange = showData;
}

//vrienden tonen
function showData() {
    //altijd bovenste lijnen toevoegen
    if (xHRObjectFriends.readyState === 4) {
        if (xHRObjectFriends.status === 200) {
            //de json met alle friends ophalen
            let serverResponse = JSON.parse(xHRObjectFriends.responseText);

            //lege string
            let html = "";
            //over serverresponse lopen
            for (let i = 0; i < serverResponse.length; i++) {
                //dit aan html toevoegen
                html = html + "<tr><td id=" + serverResponse[i].userId + ">" + serverResponse[i].userId + "</td><td>" + serverResponse[i].status + "</td></tr>";
            }
            //zoeken in document naar iets met id friends en daar de html string aan toevoegen
            document.getElementById("friends").innerHTML = html;
            //polling
            setTimeout(getFriends, 2000);
        }
    }
    //polling
    setTimeout(getFriends, 2000);
}

//vrienden toevoegen
function addFriends() {
    //zoeken in document naar iets met id add en daar de value van ophalen en in var friends steken
    let friend = document.getElementById("add").value;

    //getparameter / attribuut ophalen
    let information = "vriend=" + encodeURI(friend);

    //zoeken in document naar iets met id add en de waarde op leeg zetten ("")
    document.getElementById("add").value = "";
    //java methode doen
    xHRObjectAdd.open("POST", "Controller?action=AddFriends");
    //altijd header meesturen
    xHRObjectAdd.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    //vriend attribuut meegeven in header
    xHRObjectAdd.send(information);
    //data tonen als ready
    xHRObjectAdd.onreadystatechange = showData;
}

//wachten tot met een click 'hoort' op iets me id friends om daarna chatvenster te openen = basiclly wanneer men clickt in de lijst van vrienden op een friend
document.getElementById("friends").addEventListener("click", function(e){
    if(e.target.nodeName == "TD" && e.target){
        showChat(e.target.id);
    }
});

