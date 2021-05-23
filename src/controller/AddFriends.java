package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFriends extends RequestHandler {
// method for adding friends to friendslist
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //Asynchroon
        Controller.sendJSON();
        //user van sessie hale = ingelogde user
        Person person = (Person) request.getSession().getAttribute("user");
        //userid van toegevoegde vriend = gehaald uit input veld
        String userid = request.getParameter("vriend");
        //sessieperson voegt vriend toe
        person.addFriend(getPersonService().getPerson(userid));
        //get toegevoegde friend
        Person friendo = getPersonService().getPerson(userid);
        //vriend voegt sessie persoon toe
        friendo.addFriend(person);
        return "";
    }
}
