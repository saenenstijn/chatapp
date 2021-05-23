package controller;

import controller.Controller;
import controller.RequestHandler;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetFriends extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //asynchroon
        Controller.sendJSON();
        //sessie persoon ophalen
        Person person = (Person) request.getSession().getAttribute("user");
        String result = "";
        if (person != null){
            //lijst van vrienden van sessiepersoon
            List<Person> friends = person.getFriends();
            result += this.toJSON(friends);
        }
        response.setContentType("application/json");
        return result;
    }
}
