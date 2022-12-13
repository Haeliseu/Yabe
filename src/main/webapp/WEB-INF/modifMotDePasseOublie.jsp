<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau mot de passe</title>
</head>
<body>

	<header>
		<jsp:include page="header.jsp" />
	</header>

<h3 style="margin-bottom:.5em;">Nouveau mot de passe:</h3>
                <input type="text" class="form-control" name="nouveauMotDePasse" style="margin-bottom:1em;"
                    aria-label="Sizing example input"
                    aria-describedby="inputGroup-sizing-default">

<h3 style="margin-bottom:.5em;">Confirmation mot de passe:</h3>
                <input type="text" class="form-control" name="ConfirmationNouveauMotDePasse" style="margin-bottom:1em;"
                    aria-label="Sizing example input"
                    aria-describedby="inputGroup-sizing-default">



<a href=""><button type="button" class="btn btn-primary btn-lg">Validez</button></a>

</body>
</html>