<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil Utilisateur</title>
</head>
<body>

	<header>
		<jsp:include page="header.jsp" />
	</header>

	<ul style="text-align: center">
		<li>Pseudo : ${requestScope.userAccount.pseudo }</li>
		<li>Nom : ${requestScope.userAccount.nom }</li>
		<li>Prenom : ${requestScope.userAccount.prenom }</li>
		<li>Email : ${requestScope.userAccount.email }</li>
		<li>Telèphone : ${requestScope.userAccount.telephone }</li>
		<li>Rue : ${requestScope.userAccount.rue }</li>
		<li>Code postal : ${requestScope.userAccount.code_postal }</li>
		<li>Ville : ${requestScope.userAccount.ville }</li>
	</ul>
		
		<a href="<%=request.getContextPath() %>/ServletModificationProfil" >Modifier</a>

</body>
</html>