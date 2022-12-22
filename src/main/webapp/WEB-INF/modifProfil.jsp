<%@page import="fr.eni.javaee.bo.UserAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier mon profil</title>
</head>
<body style= "text-align : center">

<header>
<jsp:include page="header.jsp" />
</header>
<form action="/ServletModificationProfil" method="post" style="background-color: lightgray; padding:15px; border-radius:5px; box-shadow: 10px 5px 5px gray; margin:1em;">
<div class="container">
    <div class="row">
		<%UserAccount uA = (UserAccount) request.getAttribute("monUserAccount"); %>
        <div class="col-lg-6 col-md-6">
         <label for="pseudo" class="form-label">Pseudo :</label>
         <input type="text" class="form-control" id="pseudo"  name="pseudo" value="<%=uA.getPseudo() %>" required>
       </div>
 
       <div class="col-lg-6 col-md-6">
         <label for="name" class="form-label">Nom :</label>
         <input type="text" class="form-control" id="name"  name="nom" value="<%=uA.getNom() %>" required>
       </div>
 	</div>
 	<div class="row">
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Prenom :</label>
         <input type="text" class="form-control" id="prenom"  name="prenom" value="<%=uA.getPrenom() %>" required>
       </div>
 
 	  <div class="col-lg-6 col-md-6">
         <label for="inputEmail" class="form-label">Email :</label>
         <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" value="<%=uA.getEmail() %>" required>
       </div>
       
    </div>
 	<div class="row">
       <div class="col-lg-6 col-md-6">
         <label for="tel" class="form-label">Téléphone :</label>
         <input type="tel" class="form-control" id="tel"  name="tel" value="<%=uA.getTelephone() %>" required>
       </div>
       
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Rue :</label>
         <input type="text" class="form-control" id="rue"  name="rue" value="<%=uA.getRue() %>" required>
       </div>
       
       </div>
 	<div class="row">
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Code Postal :</label>
         <input type="text" class="form-control" id="code_postal"  name="code_postal" value="<%=uA.getCode_postal() %>" required>
       </div>
       
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Ville :</label>
         <input type="text" class="form-control" id="ville"  name="ville" value="<%=uA.getVille() %>" required>
       </div>
       </div>
 	<div class="row">
       <div class="col-lg-6 col-md-6">
         <label for="inputPassword" class="form-label">Mot de Passe :</label>
         <input type="password" class="form-control" id="password"  name="mot_de_passe" value="" required>
       </div>
       <!-- controller le password -->
       <div class="col-lg-6 col-md-6">
         <label for="inputPassword" class="form-label">Confirmation :</label>
         <input type="password" class="form-control" id="password"  name="mot_de_passe" value="" required>  
       </div>
       </div>
 	<div class="row">
       <br>
       <div>
       <button type="submit" class="btn btn-primary" name="Inscription" value="Modifier" style="margin:1em;">Modifier</button>
      
             
      
       </div>
     </div>
     </div>
     </form>
       <a href="<%=request.getContextPath() %>/ServletAccueil"><button class="btn btn-secondary" name="reset" value="Annuler">Annuler</button></a>
     
     <form action="/ServletSupprimerMonCompte.java"method="post">
     	<input type="submit" class="btn btn-primary" name="Supprimer" value="Supprimer mon compte">  
     </form>
</body>
<jsp:include page="footer.jsp" />
</html>