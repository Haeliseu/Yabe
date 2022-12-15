<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>S'inscrire</title>
</head>
<body>

<header>
<jsp:include page="header.jsp" />
</header>
<form action="" method="post" style="background-color: lightgray; padding:15px; border-radius:5px; box-shadow: 10px 5px 5px gray;">
<div class="container">
    <div class="row">

        <div class="col-lg-6 col-md-6">
         <label for="pseudo" class="form-label">Pseudo :</label>
         <input type="text" class="form-control" id="pseudo"  name="pseudo" value="" required pattern="[a-zA-Z0-9\s]+" required>
       </div>
 
       <div class="col-lg-6 col-md-6">
         <label for="name" class="form-label">Nom :</label>
         <input type="text" class="form-control" id="name"  name="nom" value="" required>
       </div>
 	</div>
 	<div class="row">
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Prenom :</label>
         <input type="text" class="form-control" id="prenom"  name="prenom" value="" required>
       </div>
 
 	  <div class="col-lg-6 col-md-6">
         <label for="inputEmail" class="form-label">Email :</label>
         <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" value="" required>
       </div>
       
    </div>
 	<div class="row">
       <div class="col-lg-6 col-md-6">
         <label for="tel" class="form-label">Téléphone :</label>
         <input type="tel" class="form-control" id="tel"  name="tel" value="" required>
       </div>
       
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Rue :</label>
         <input type="text" class="form-control" id="rue"  name="rue" value="" required>
       </div>
       
       </div>
 	<div class="row">
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Code Postal :</label>
         <input type="text" class="form-control" id="code_postal"  name="code_postal" value="" required>
       </div>
       
       <div class="col-lg-6 col-md-6">
         <label for="prenom" class="form-label">Ville :</label>
         <input type="text" class="form-control" id="ville"  name="ville" value="" required>
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
         <input type="password" class="form-control" id="password"  name="Confirmation" value="" required>  
       </div>
       </div>
 	<div class="row">
       <br>
       <div>
       <button type="submit" class="btn btn-primary" name="Inscription" value="Créer">Créer</button>
       <button type="reset" class="btn btn-secondary" name="reset" value="annuler">Annuler</button>
       </div>
     </div>
     </div>
     </form>
</body>
</html>