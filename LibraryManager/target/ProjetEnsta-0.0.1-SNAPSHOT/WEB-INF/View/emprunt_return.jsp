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
        <h1 class="page-announce-text valign">Retour d'un livre</h1>
      </div>
      <div class="row">
      <div class="container">
        <h5>Sélectionnez le livre à retourner</h5>
        <div class="row">
	      <form action="emprunt_return" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s12">
	            <select id="id" name="id" class="browser-default">
                <c:choose>
                  <c:when test="${id != null && !currentLoan.isEmpty()}">
                    <c:forEach items="${currentLoan}" var="loan">
                      <option value="${id}">"${loan.book.title}", emprunté par ${loan.member.firstName} ${loan.member.lastName}</option>
                    </c:forEach>
                  </c:when>
                  <c:otherwise>
                    <option value="" disabled selected>---</option>
                    <c:if test="${!currentLoan.isEmpty()}">
                      <c:forEach items="${currentLoan}" var="loan">
                        <option value="${loan.id}">"${loan.book.title}", emprunté par ${loan.member.firstName} ${loan.member.lastName}</option>
                      </c:forEach>
                    </c:if>
                  </c:otherwise>
                </c:choose>
                  <!-- TODO : parcourir la liste des emprunts non rendus et afficher autant d'options que n�cessaire, sur la base de l'exemple ci-dessous -->
                  <!-- TODO : si l'attribut id existe, l'option correspondante devra �tre s�lectionn�e par d�faut (ajouter l'attribut selected dans la balise <option>) -->
                  
	            </select>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Retourner le livre</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	    </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
  <script>
    document.addEventListener('DOMContentLoaded', function() {
	  var elems = document.querySelectorAll('select');
	  var instances = M.FormSelect.init(elems, {});
	});
  </script>
</body>
</html>
