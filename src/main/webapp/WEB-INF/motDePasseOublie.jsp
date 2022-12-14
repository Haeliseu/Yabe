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

<form action="<%=request.getContextPath() %>/ServletMotDePasseOublie" method="post">

<h3 style="margin-bottom:.5em;">Pseudo:</h3>
                <input type="text" class="form-control" name="pseudo" style="margin-bottom:1em;"
                    aria-label="Sizing example input"
                    aria-describedby="inputGroup-sizing-default">
                    


<h3 style="margin-bottom:.5em;">Email:</h3>
                <input type="text" class="form-control" name="email" style="margin-bottom:1em;"
                    aria-label="Sizing example input"
                    aria-describedby="inputGroup-sizing-default">



<input type="submit" class="btn btn-primary btn-lg"value ="Validez"/>
</form>
</body>
</html>