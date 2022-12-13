package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.dal.ConnectionProvider;

@WebServlet("/ServletRegister")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 
			
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String code_postal = request.getParameter("code_pôstal");
			String ville = request.getParameter("ville");
			String mot_de_passe = request.getParameter("mot_de_passe");
			String confirmation = request.getParameter("confirmation");
	 
			if (pseudo!="" && nom!="" && prenom!="" && email!="" && telephone!="" && rue!="" && code_postal!="" && ville!="" && mot_de_passe!="" && confirmation!=""){
	 
	 
				try (Connection cnx = ConnectionProvider.getConnection()){

					stmt = cnx.createStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
					
					String sql = ;
					stmt.executeUpdate(sql);
	 
				} catch (SQLException e)
				{
					e.printStackTrace();
				}catch (ClassNotFoundException e)
				{
					e.printStackTrace();				
				}
	 
				response.sendRedirect("");
	 
	 
		}
			else 
			{
				response.sendRedirect("register.jsp");
	}
	}
		
	}



