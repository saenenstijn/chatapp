let webSocket;


// connectie met websocket openen
function open() {
    webSocket = new WebSocket("ws://localhost:8080/Blog");

    webSocket.onopen = function (event) {
    };

    webSocket.onmessage = function (event) {
        writeResponse(event.data);
    };

    webSocket.onclose = function (event) {
    };
}

//feedback opslaan die ingegeven is in de input velden
function send(id) {
    let feedbacks = {};
    feedbacks.topicID = id;
    feedbacks.name = document.getElementById("name" + id).value;
    feedbacks.topic = document.getElementById("feedback" + id + "text").value;
    feedbacks.rating = document.getElementById("feedback" + id + "nr").value;
    webSocket.send(JSON.stringify(feedbacks));
}

//websocket sluiten
function close() {
    webSocket.close();
}


//functie om resultaat uit te printen na je op knop klikt
function writeResponse(text) {
    let result = JSON.parse(text);
    for (let i = 0; i < result.length; i++) {
        let commentsUL = document.getElementById("feedback" + result[i].topicID);
        commentsUL.innerHTML += "<li>" + result[i].name + " (" + result[i].rating + "/10) : " + result[i].topic + "</li>";
    }
}

open();
