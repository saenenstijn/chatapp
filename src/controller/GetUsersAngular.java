package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUsersAngular extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //asynchroon
        Controller.sendJSON();
        //angular
        response.setContentType("application/json");
        //alle personen verzenden via json
        return personsTOJSON(getPersonService().getPersons());
    }

    public String personsTOJSON(List<Person> persons){
        String json = "[";
        int i = 0;
        //lijst personen verzenden via json
        for (Person p : persons) {
            json += "{\"userid\": \"" + p.getUserId() + "\", " +
                    "\"firstName\": \"" + p.getFirstName() + "\"," +
                    "\"lastname\": \"" + p.getLastName() + "\"," +
                    "\"status\": \"" + p.getStatus() + "\"}";
            i++;
            if (persons.size() != i) {
                json += ",";
            }
        }
        json += "]";
        return json;
    }
}
