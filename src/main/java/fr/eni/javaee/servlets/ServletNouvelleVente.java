package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bll.ArticleVenduManager;
import fr.eni.javaee.bll.UserAccountManager;
import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.bo.UserAccount;
import fr.eni.javaee.dal.DAOFactory;

@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// RECUP ID USER
		int idUser;
		HttpSession session = request.getSession();
		UserAccount uA = (UserAccount) session.getAttribute("useraccount");
		idUser = uA.getNoUtilisateur();
		
		UserAccount uARech;
		uARech = UserAccountManager.getInstance().selectUser(idUser);
				
		request.setAttribute("Rue", uARech.getRue());
		request.setAttribute("CP", uARech.getCode_postal());
		request.setAttribute("Ville", uARech.getVille());
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// RECUP ID USER
		int idUser;
		HttpSession session = request.getSession();
		UserAccount uA = (UserAccount) session.getAttribute("useraccount");
		idUser = uA.getNoUtilisateur();
		uA = UserAccountManager.getInstance().selectUser(idUser);
		
		// RECUP INFOS ARTICLE
		String nomArticle = request.getParameter("nomArticle");
		String descriptionArticle = request.getParameter("descriptionArticle");
		Categorie categorie = new Categorie(Integer.parseInt(request.getParameter("categorie")));
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		LocalDate debutEncheres = LocalDate.parse(request.getParameter("debutEncheres"));
		LocalDate finEncheres = LocalDate.parse(request.getParameter("finEncheres"));
		
		// RECUP INFOS RETRAIT
		String retraitRue = request.getParameter("retraitRue");
		int retraitCP = Integer.parseInt(request.getParameter("retaitCP"));
		String retraitVille = request.getParameter("retaitVille");

			try {
				ArticleVenduManager.getInstance().insertVente(
						nomArticle, descriptionArticle, categorie, 
						debutEncheres, finEncheres, 
						miseAPrix, uA,
						retraitRue, retraitCP, retraitVille);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}
