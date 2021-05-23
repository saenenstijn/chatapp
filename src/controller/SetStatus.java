package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetStatus extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //asynchroon
        Controller.sendJSON();
        //code wanneer setstatus via chatapp
        if(request.getParameter("user") == null){
            HttpSession session = request.getSession();
            Person person = (Person) session.getAttribute("user");
            person.setStatusToString(request.getParameter("status"));
            return transformJSON(person.getStatus());
        }
        // code wanneer setstatus via angular
        else{
            getPersonService().getPerson(request.getParameter("user")).setStatusToString(request.getParameter("status"));
            return transformJSON(getPersonService().getPerson(request.getParameter("user")).getStatus());
        }
    }
    private String transformJSON(String status) {
        StringBuffer json = new StringBuffer();

        json.append("{ \"status\": \"");
        json.append(status);
        json.append("\"}");

        return json.toString();
    }
}
