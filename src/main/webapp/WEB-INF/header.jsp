<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Import BOOTSTRAP A FAIRE DANS LE HEAD DE LA PAGE-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- Import DE LA FEUILLE DE STYLE -->
<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
 <nav class="navbar bg-light"> 
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Yabe - ENI-Enchères</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Naviguation</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      
      <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
      
      <!-- TODO RECUPERATION DE LA VARIABLE DE CONNEXION -->
      <% boolean checkConnect = true;%>
      
      <% if (checkConnect == true){ %>
      <!-- HEADER CONNECTE -->
          <li class="nav-item">
            <a class="nav-link" href="#">Enchères</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Vendre un article</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Mon Profil</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/ServletDisconnect">Déconnexion</a>
          </li>
       
       <%}else {%>
          <!-- HEADER DECONNECTE -->
          <li class="nav-item">
            <a class="nav-link" href="#">S'incrire</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Se connecter</a>
          </li>
		
		<%} %>

          
        </ul>
      </div>
    </div>
  </div>
</nav>


