package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PersonService;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean sendJSON = false;

	private PersonService model = new PersonService();
	private ControllerFactory controllerFactory = new ControllerFactory();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//angular deel
		response.setHeader("Access-Control-Allow-Origin","*");
		String action = request.getParameter("action");
		String destination = "index.jsp";
		if (action != null) {
			RequestHandler handler;
			try {
				handler = controllerFactory.getController(action, model);
				destination = handler.handleRequest(request, response);
			}
			catch (NotAuthorizedException exc) {
				List<String> errors = new ArrayList<String>();
				errors.add(exc.getMessage());
				request.setAttribute("errors", errors);
				destination="index.jsp";
			}
		}
		//synchroon
		if (!sendJSON ){
			RequestDispatcher view = request.getRequestDispatcher(destination);
			view.forward(request, response);
		}
		//asynchroon
		if (sendJSON){
			response.getWriter().write(destination);
			sendJSON = false;
		}
	}
	//asynchroon
	public static void sendJSON(){
		sendJSON = true;
	}

	public static boolean getJSON(){
		return sendJSON;
	}
	public static void sendHTML(){
		sendJSON = false;
	}

}
