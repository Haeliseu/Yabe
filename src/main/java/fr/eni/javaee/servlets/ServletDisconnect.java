package fr.eni.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDisconnect
 */
@WebServlet("/ServletDisconnect")
public class ServletDisconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletDisconnect(){
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("useraccount");
             
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletAccueil");
            dispatcher.forward(request, response);
		
        }
	}

}
