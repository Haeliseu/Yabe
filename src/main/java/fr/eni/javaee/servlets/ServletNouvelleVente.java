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

import fr.eni.javaee.bll.ArticleVenduManager;
import fr.eni.javaee.bo.UserAccount;
import fr.eni.javaee.dal.DAOFactory;

@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// RECUP ID USER
		int idUser;
		HttpSession session = request.getSession();
		UserAccount uA = (UserAccount) session.getAttribute("useraccount");
		idUser = uA.getNoUtilisateur();
		
		// RECUP INFOS ARTICLE
		String nomArticle = request.getParameter("nomArticle");
		String descriptionArticle = request.getParameter("descriptionArticle");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		LocalDate debutEncheres = LocalDate.parse(request.getParameter("debutEncheres"));
		LocalDate finEncheres = LocalDate.parse(request.getParameter("finEncheres"));
		
		// RECUP INFOS RETRAIT
		String retraitRue = request.getParameter("retraitRue");
		int retraitCP = Integer.parseInt(request.getParameter("retaitCP"));
		String retraitVille = request.getParameter("retaitVille");
		
		
		System.out.println(nomArticle);
		System.out.println(descriptionArticle);
		System.out.println(categorie);
		System.out.println(miseAPrix);
		System.out.println(debutEncheres);
		System.out.println(finEncheres);
		
		System.out.println(retraitRue);
		System.out.println(retraitCP);
		System.out.println(retraitVille);
		

		ArticleVenduManager.getInstance().insertVente(
				nomArticle, descriptionArticle, categorie, 
				debutEncheres, finEncheres, 
				miseAPrix, idUser,
				retraitRue, retraitCP, retraitVille);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}
