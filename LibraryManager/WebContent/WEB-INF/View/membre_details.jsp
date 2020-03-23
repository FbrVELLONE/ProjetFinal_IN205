<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Fiche membre</h1>
      </div>
      <div class="row">
      <div class="container">
      <h5>Détails du membre n°${member.id}</h5> 
        <div class="row">
	      <form action="membre_details?id=${member.id}" method="post" class="col s12"> 
	        <div class="row">
	          <div class="input-field col s4">
	            <input id="nom" type="text" value=${member.lastName} name="nom"> <!-- TODO : remplacer nomDuMembre par le nom du membre -->
	            <label for="nom">Nom</label>
	          </div>
	          <div class="input-field col s4">
	            <input id="prenom" type="text" value=${member.firstName} name="prenom"> <!-- TODO : remplacer prenomDuMembre par le pr�nom du membre -->
	            <label for="prenom">Prénom</label>
	          </div>
	          <div class="input-field col s4">
	            <select name="abonnement" class="browser-default">
	              <!-- TODO : faire en sorte que l'option correspondant � l'abonnement du membre soit s�lectionn�e par d�faut -->
	              <!-- Pour cela, vous devez rajouter l'attribut selecter sur la balise <option> concern�e -->
					  <!--Code-->
					<c:choose>
						<c:when test="${member.subscription == 'BASIC'}">
							<option value="BASIC" selected>Abonnement BASIC</option>
							<option value="PREMIUM">Abonnement PREMIUM</option>
							<option value="VIP">Abonnement VIP</option>
						</c:when>
						<c:when test="${member.subscription == 'PREMIUM'}">
							<option value="BASIC">Abonnement BASIC</option>
							<option value="PREMIUM" selected>Abonnement PREMIUM</option>
							<option value="VIP">Abonnement VIP</option>
						</c:when>
						<c:when test="${member.subscription == 'VIP'}">
							<option value="BASIC">Abonnement BASIC</option>
							<option value="PREMIUM">Abonnement PREMIUM</option>
							<option value="VIP" selected>Abonnement VIP</option>
						</c:when>
						<c:otherwise>
							<option value="" disabled selected>---</option>
							<option value="BASIC">Abonnement BASIC</option>
							<option value="PREMIUM">Abonnement PREMIUM</option>
							<option value="VIP">Abonnement VIP</option>
						</c:otherwise>
					</c:choose>
					
	            </select>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s12">
	            <input id="adresse" type="text" value=${member.adress} name="adresse"> <!-- TODO : remplacer adresseDuMembre par l'adresse du membre -->
	            <label for="adresse">Adresse</label>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s6">
	            <input id="email" type="email" value=${member.email} name="email"> <!-- TODO : remplacer emailDuMembre par l'email du membre -->
	            <label for="email">E-mail</label>
	          </div>
	          <div class="input-field col s6">
	            <input id="telephone" type="tel" value=${member.telephone} name="telephone"> <!-- TODO : remplacer telephoneDuMembre par le t�l�phone du membre -->
	            <label for="telephone">Téléphone</label>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	      
	      <form action="membre_delete" method="get" class="col s12">
	        <input type="hidden" value=${member.id} name="id"> <!-- TODO : remplacer idDuMembre par l'id du membre -->
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer le membre
	            <i class="material-icons right">delete</i>
	          </button>
	        </div>
	      </form>
	    </div>
        <div class="row">
          <div class="col s12">
	        <table class="striped">
              <thead>
                <tr>
                  <th>Livre</th>
                  <th>Date d'emprunt</th>
                  <th>Retour</th>
                </tr>
              </thead>
              <tbody id="results">
				  <!--Code-->
				<c:if test="${!currentByMember.isEmpty()}">
					<c:forEach items="${currentByMember}" var="current">
						<tr>
						<td>"${current.book.title}" de ${current.book.author}</td>
						<td>${current.loanDate}</td>
						<td>
							<a href="emprunt_return?id=${current.id}"><ion-icon class="table-item" name="log-in"></a>
						</td>
						</tr>
					</c:forEach>
				</c:if>

				<!-- TODO : parcourir la liste des emprunts en cours pour ce membre et les afficher selon la structure d'exemple ci-dessus -->
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
