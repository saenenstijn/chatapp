package controller;

import domain.DomainException;
import domain.Person;
import domain.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //synchroon registersysteem
        Person test = new Person();
        ArrayList<String> errors = new ArrayList<>();
        if(!request.getParameter("passwordr").equals(request.getParameter("passwordrepeat"))){
            errors.add("passwords don't match");
        }

        try{
            test.setFirstName(request.getParameter("fname"));
        } catch (DomainException e){
            errors.add(e.getMessage());
        }
        try{
            test.setLastName(request.getParameter("lname"));
        } catch (DomainException e){
            errors.add(e.getMessage());
        }
        try{
            test.setUserId(request.getParameter("emailr"));
        } catch (DomainException e){
            errors.add(e.getMessage());
        }
        try{
            test.setPassword(request.getParameter("passwordr"));
        } catch (DomainException e){
            errors.add(e.getMessage());
        }

        if(errors.size() == 0){
            Person user = new Person(
                    request.getParameter("emailr"),
                    request.getParameter("passwordr"),
                    request.getParameter("fname"),
                    request.getParameter("lname"),
                    Role.LID
            );
            getPersonService().addPerson(user);
        }else {
            request.setAttribute("errors", errors);
        }
        return "index.jsp";
    }
}
