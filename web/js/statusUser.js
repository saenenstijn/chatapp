let xhr = new XMLHttpRequest();

//onload status en vrienden ophalen
window.onload = function () {
    getStatus();
    getFriends();
}

//status ophalen via java methode
function getStatus() {
    xhr.open("GET", "Controller?action=GetStatus");
    xhr.onreadystatechange = getData;
    xhr.send(null);
}


//status aanpassen via input veld
function changeStatus() {
    //waarde van input field ophalen
    let status = document.getElementById("change").value;
    //status ophalen
    let info = "status= " + encodeURI(status);
    //status leeg zetten
    document.getElementById("change").value = "";
    //java methode setstatus oproepen
    xhr.open("POST", "Controller?action=SetStatus",true);
    //header meegeven
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    //nieuwe status in header meegeven
    xhr.send(info);
    //data tonen als ready
    xhr.onreadystatechange = getData;
}
//status tonen
function getData() {
    //altijd bovenste lijnen toevoegen
    if(xhr.readyState === 4){
        if(xhr.status === 200){
            let serverResponse = JSON.parse(xhr.responseText);
            //status uit xml halen
            let statusXML = serverResponse.status;
            //div waar status inmoet zoeken
            let statusDiv = document.getElementById("status");
            //childnodes op 0 zetten
            let statusParagraph = statusDiv.childNodes[0];

            //als er niks in status staan
            if(statusParagraph == null){
                //paragraaf aanmaken
                statusParagraph = document.createElement("paragraph");
                //id op statustext zetten
                statusParagraph.id = "statusText";
                //textnode aanmaken
                let statusText = document.createTextNode(statusXML);
                //child eraan geven
                statusParagraph.appendChild(statusText);
                //als child de paragraaf toevoegen
                statusDiv.appendChild(statusParagraph);
            }else{
                //anders ook texnode aanmaken
                let statusText = document.createTextNode(statusXML);
                //child wedoen
                statusParagraph.removeChild(statusParagraph.childNodes[0]);
                //andere in de plaasts
                statusParagraph.appendChild(statusText);
            }
            //polling van getstatus
            setTimeout(getStatus, 1000);
        }
    }
    //polling van getstatus
    setTimeout(getStatus, 1000);
}
