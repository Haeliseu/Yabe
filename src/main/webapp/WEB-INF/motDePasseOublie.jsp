<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de passe oublié</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp" />
	</header>

<form action="" method="post"></form>

<h3 style="margin-bottom:.5em;">Pseudo:</h3>
                <input type="email" class="form-control" name="pseudo" style="margin-bottom:1em;"
                    aria-label="Sizing example input"
                    aria-describedby="inputGroup-sizing-default">
                    


<h3 style="margin-bottom:.5em;">Email:</h3>
                <input type="email" class="form-control" name="email" style="margin-bottom:1em;"
                    aria-label="Sizing example input"
                    aria-describedby="inputGroup-sizing-default">

<a href="/ServletMotDePasseOublie"><button type="button" class="btn btn-primary btn-lg">Validez</button></a>

</body>
</html>