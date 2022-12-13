<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Se connecter</title>
</head>
<body>
<header>
<jsp:include page="header.jsp" />
</header>
<div class="container h-100">
 <div class="row align-items-center h-100">
    <div class="col-md-3"></div>
    <div class="col-md-6">
         <div class="card" style="width: auto; box-shadow: 10px 5px 5px gray;">
            <div class="card-body">
               <form method="POST" action="" style="text-align: center;">
               <label for="pseudo">Identifiant :</label>
                  <input type="text" name="identifiant" id="identifiant" class="form-control" required/>
                  <br>
                  <br>
                  <label for="mot_de_passe">Mot de passe :</label>
                  <input type="password" name="mot_de_passe" id="mot_de_passe" class="form-control" required/>
                  <br /><br />   
                  <div class="row">	  
                  <div class="col">            
                  <input class="btn btn-primary" type="submit" name="formconnexion" value="Connexion"/> 
                  </div> 
                  <div class="col"> 
                  <input type="checkbox" name="rememberMe" value="Se souvenir de moi" id="rememberMe"/>                
                  <label for="rememberMe" id="rememberMe">Se souvenir de moi</label>
                  </div> 
                  <div class="w-70"></div>
                  <div class="col"> 
                  </div>
			      <div class="col"> 
                  <a id="mot_de_passe_oubli� class="text-forgot-password" href="">Mot de passe oubli�</a>
                  </div>
                  </div>
                  <br>
				  <div class="col"> 
                  <input class="btn btn-primary" type="submit" name="formconnexion" value="Cr�er un compte"/>
                  </div>
                  </form>
                  </div>
            </div>
         </div>
      </div>
</div>
</body>
</html>