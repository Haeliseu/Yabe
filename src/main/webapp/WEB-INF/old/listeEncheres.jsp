<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des enchères</title>
<!-- IMPORT BOOTSTRAP -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>

<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>

<body>

	
	<h2>Liste des enchères</h2>
	
	<div style=""> <!-- MENU -->
	
		<div> <!-- FILTERS -->
		
			<form action="POST" > <!-- ACHATS -->
			
				<div class="input-group mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-default">🔎</span>
				  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
				</div>
			
			</form>
			
			<form action="POST" > <!-- VENTES -->
			
				
			
			</form>
		
		</div> 
		
		<div><button type="button" class="btn btn-primary btn-lg">Rechercher</button></div> <!-- SEARCH -->
	
	</div>
	
</body>
</html>