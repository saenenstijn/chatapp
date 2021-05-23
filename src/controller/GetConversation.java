package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.Conversation;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetConversation extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //asynchroon
        Controller.sendJSON();
        //sessieuser ophalen
        Person user = (Person) request.getSession().getAttribute("user");
        if(user != null){
            //userid van ingelogde persoon ophalen
            String userid = request.getParameter("userId");
            Conversation found = null;

            //checken of deze persoon vrienden heeft
            if(user.getFriends().contains(getPersonService().getPerson(userid))){
                //checken of deze persoon al in conversaties zit
                for(Conversation c : this.getPersonService().conversations){
                    //testen voor juiste persoon
                    if((c.p1.getUserId().equals(user.getUserId()) && c.p2.getUserId().equals(userid)) ||
                            (c.p2.getUserId().equals(user.getUserId()) && c.p1.getUserId().equals(userid))){
                        //al bestaande conversatie gebruiken
                        found = c;
                        break;
                    }
                }
                //geen bestaande conversatie
                if(found == null){
                    //nieuwe aanmaken
                    found = new Conversation(user, getPersonService().getPerson(userid));
                    this.getPersonService().conversations.add(found);
                }
                //verzenden
                String result = this.ListToJSON(found.messages);
                response.setContentType("application/json");
                response.getWriter().write(result);
            }
        }
        return "";
    }
}

