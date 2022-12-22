<%@page import="fr.eni.javaee.bo.UserAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil Utilisateur</title>
</head>
<body style= "text-align : center">

	<header>
		<jsp:include page="header.jsp" />
	</header>

	<ul style="text-align: center">
		<li>Pseudo : ${requestScope.userAccount.pseudo }</li>
		<li>Nom : ${requestScope.userAccount.nom }</li>
		<li>Prenom : ${requestScope.userAccount.prenom }</li>
		<li>Email : ${requestScope.userAccount.email }</li>
		<li>Téléphone : ${requestScope.userAccount.telephone }</li>
		<li>Rue : ${requestScope.userAccount.rue }</li>
		<li>Code postal : ${requestScope.userAccount.code_postal }</li>
		<li>Ville : ${requestScope.userAccount.ville }</li>
		
		<%UserAccount userAccount = (UserAccount) request.getAttribute("userAccount"); %>
		<input type="text" name="idUser" value="<%=userAccount.getNoUtilisateur() %>" disabled hidden>
		<input type="text" name="pseudoUser" value="<%=userAccount.getPseudo() %>" disabled hidden>
		<input type="text" name="nomUser" value="<%=userAccount.getNom() %>" disabled hidden>
		<input type="text" name="prenomUser" value="<%=userAccount.getPrenom() %>" disabled hidden>
		<input type="text" name="emailUser" value="<%=userAccount.getEmail() %>" disabled hidden>
		<input type="text" name="telephoneUser" value="<%=userAccount.getTelephone() %>" disabled hidden>
		<input type="text" name="rueUser" value="<%=userAccount.getRue() %>" disabled hidden>
		<input type="text" name="cpUser" value="<%=userAccount.getCode_postal() %>" disabled hidden>
		<input type="text" name="villeUser" value="<%=userAccount.getVille() %>" disabled hidden>
	</ul>
		
		<a href="<%=request.getContextPath() %>/ServletModificationProfil" >Modifier</a>

</body>
<jsp:include page="footer.jsp" />
</html>