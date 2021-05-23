package controller;


import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetStatus extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //asynchroon
        Controller.sendJSON();
        //sessiepersoon ophalen
        Person person = (Person) request.getSession().getAttribute("user");
        if(person.getStatus() != null){
            //standard status ophalen als status leeg is
            return transformJSON(person.getStatus());
        }else {
            //anders op unknown zetten
            return transformJSON("unknown");
        }
    }
    //status verzenden via json
    private String transformJSON(String status) {
        StringBuffer json = new StringBuffer();

        json.append("{ \"status\": \"");
        json.append(status);
        json.append("\"}");

        return json.toString();
    }
}
