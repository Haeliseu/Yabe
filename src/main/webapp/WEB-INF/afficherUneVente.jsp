<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.messages.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail de la vente</title>
</head>
<body>
	
	<jsp:include page="header.jsp" />
	
	<div class="container align-items-center">
		
		<ul style="text-align: center">
		
		<li>Vendeur : ${article.pseudoVendeur }</li>
		
		<li>Nom : ${requestScope.article.nomArticle }</li>
		<li>Description : ${requestScope.article.description }</li>
		<li>Catégorie : ${requestScope.article.categorie }</li>
		<li>Mise à prix : ${requestScope.article.prix }</li>
		<li>Début de l'enchère : ${requestScope.article.dateDebutEncheres }</li>
		<li>Fin de l'enchère : ${requestScope.article.dateFinEncheres }</li>
		
		<li>Code postal : ${requestScope.article.rue }</li>
		<li>Code postal : ${requestScope.article.codePostal }</li>
		<li>Ville : ${requestScope.article.ville }</li>
	</ul>
		
	</div>
	
	<div style="text-align: center";>
		<%
		List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
		if (listeCodesErreur != null) {
		%>
		<p style="color: red">Erreur, l'utilisateur n'a pas pu être ajouté
			:</p>
		<%
		for (int code : listeCodesErreur) {
		%>
		<p><%=code%>
			:
			<%=LecteurMessage.getMessageErreur(code)%></p>
		<%
		}
		}
		%>
	</div>
	
	<jsp:include page="footer.jsp" />
	
</body>
</html>