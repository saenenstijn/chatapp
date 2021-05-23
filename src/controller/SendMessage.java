package controller;

import domain.Conversation;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendMessage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //asynchroon
        //sessie user ophalen
        Person user = (Person) request.getSession().getAttribute("user");

        if(user != null){
            //userid van persoon waarmee je wilt chatten ophalen
            String userid = request.getParameter("userId");
            Person p2 = getPersonService().getPerson(userid);

            Conversation found = null;

            //checken of sessieuser chatuser als friend heeft
            if(user.getFriends().contains(getPersonService().getPerson(userid))){
                //alle conversaties tussen deze 2 ophalen
                for(Conversation c : this.getPersonService().conversations){
                    //checken of user gelijk izjn
                    if((c.p1.getUserId().equals(user.getUserId()) && c.p2.getUserId().equals(userid)) ||
                            (c.p2.getUserId().equals(user.getUserId()) && c.p1.getUserId().equals(userid))){
                        //conversatie gebruiken
                        found = c;
                        break;
                    }
                }
                //nieuwe aanmaken
                if(found == null){
                    found = new Conversation(user, getPersonService().getPerson(userid));
                    this.getPersonService().conversations.add(found);
                }
                //message toevoegen
                if(!request.getParameter("message").isEmpty()){
                    found.messages.add(user.getUserId()+": " + request.getParameter("message"));
                }
            }
        }
        return null;
    }
}
