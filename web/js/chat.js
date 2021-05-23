var current = "";


//chat venster openen
function showChat(id){
    current = id;
    //polling van getchat elke tijd 500
    setInterval(getChat, 500);
    var html =
        "<div class=\"chat-popup\" id=\"myForm\">" +
        "<div class=\"form-container\">" +
        "<h1 id=\"chatid\">" + id + "</h1>" +
        "<label ><b>Message</b></label>" +
        "<ul id=\"chatlist\"></ul>" +
        "<p>" +
        "<input type=\" text\" id=\"message\" >"+
        "<button id=\"send\" onclick=\"sendMessage('" + id + "')\">send</button>" +
        "</div>" +
        "</div>";
    //html toevoegen aan chat id (#)
    $('#chat').append(html);
}

//bericht versturen wanneer je op sen klikt
function sendMessage(id){
    //The val() method returns or sets the value attribute of the selected elements.
    var message = $('#message').val();
    $('#message').val("");
    $.post("Controller", {action: "SendMessage", message: message, userId: id});
}

//chat ophalen elke zoveel seconden via polling
function getChat(){
    if(current !== ""){
        $.ajax({
            type: "GET",
            url: "Controller?action=GetConversation&userId=" + current,
            dataType: "json",
            //success na connectie met java
            success: function (result) {
                //chatlist leegmaken
                $('#chatlist').empty();
                //over chatlijst gaan
                for(var i = 0; i < result.length; i++){
                    //berichten toevoegen aan html als die er zijn in lijst
                    $('#chatlist').append("<li>" + result[i] + "</li>");
                }
            },
            //error na connectie met java
            error: function () {
                console.log("error has occured");
            }
        });
    }
}
