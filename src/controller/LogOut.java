package controller;


import domain.Person;
import controller.RequestHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut extends RequestHandler {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//synchroon logoutsysteem
		HttpSession session = request.getSession();
			Person sessionperson = (Person) session.getAttribute("user");
			sessionperson.setStatusOffline();

			session.invalidate();
			return "index.jsp";
	}
}
