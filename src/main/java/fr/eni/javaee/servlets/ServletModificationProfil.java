package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.bll.UserAccountManager;
import fr.eni.javaee.bo.UserAccount;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ServletModificationProfil")
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pseudoUser = request.getParameter("pseudoUser");
		String nomUser = request.getParameter("nomUser");
		String prenomUser = request.getParameter("prenomUser");
		String emailUser = request.getParameter("emailUser");
		String telephoneUser = request.getParameter("telephoneUser");
		String rueUser = request.getParameter("rueUser");
		String cpUser = request.getParameter("cpUser");
		String villeUser = request.getParameter("villeUser");
		
		UserAccount uA = new UserAccount();
		
		uA.setPseudo(pseudoUser);
		uA.setNom(nomUser);
		uA.setPrenom(prenomUser);
		uA.setEmail(emailUser);
		uA.setTelephone(telephoneUser);
		uA.setRue(rueUser);
		uA.setCode_postal(cpUser);
		uA.setVille(villeUser);
		
		request.setAttribute("monUserAccount", uA);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifProfil.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount uA = (UserAccount) session.getAttribute("useraccount");
		UserAccount userAccount = null;
		int noUtilisateur;
		noUtilisateur = uA.getNoUtilisateur();

		userAccount = UserAccountManager.getInstance().selectUser(noUtilisateur);
		request.setAttribute("userAccount", userAccount);

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String mot_de_passe = request.getParameter("mot_de_passe");

		if (mot_de_passe == null) {
			mot_de_passe = uA.getMot_de_passe();
		}

		request.setAttribute("pseudo", pseudo);
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);
		request.setAttribute("email", email);
		request.setAttribute("telephone", telephone);
		request.setAttribute("rue", rue);
		request.setAttribute("code_postal", codePostal);
		request.setAttribute("ville", ville);
		request.setAttribute("mot_de_passe", mot_de_passe);

		try {
			userAccount = UserAccountManager.getInstance().updateUser(pseudo, nom, prenom, email, telephone, rue,
					codePostal, ville, noUtilisateur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
