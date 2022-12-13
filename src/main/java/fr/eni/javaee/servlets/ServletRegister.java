package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			String url = "jdbc:sqlserver://localhost;databasename=BDD_YABE;IntegratedSecurity=false;encrypt=false;trustServerCertificate=false";
			String login = "utilisateurBDD";
			String passwd = "Pa$$w0rd";
			java.sql.Connection cnx = null;
			java.sql.Statement stmt = null;
	 
			if (pseudo!="" && nom!="" && prenom!="" && email!="" && telephone!="" && rue!="" && code_postal!="" && ville!="" && mot_de_passe!="" && confirmation!=""){
	 
	 
				try {
					Class.forName("com.mysql.jdbc.Driver");
					cnx = DriverManager.getConnection(url, login, passwd);
					stmt = cnx.createStatement();
					
					String sql = "INSERT INTO utilisateurs"
							+ "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)\r\n"
							+ "VALUES"
							+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0);";
					stmt.executeUpdate(sql);
	 
				} catch (SQLException e)
				{
					e.printStackTrace();
				}catch (ClassNotFoundException e)
				{
					e.printStackTrace();				
				}finally {
					try {
						cnx.close();
						stmt.close();
						}catch (SQLException e)
						{
							e.printStackTrace();
						}
				}
	 
				response.sendRedirect("");
	 
	 
		}
			else 
			{
				response.sendRedirect("register.jsp");
	}
	}
		
	}



