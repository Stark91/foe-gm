<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Liste des GM</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/style.css"></c:url>" />
        <link rel="shortcut icon" href="#">
	</head>
	<body>
		<%@ include file="sidenav.jsp" %>
		<div class="content">
			<div class="gm-grid-list grid-list">
				<c:forEach items="${mapGM}" var="gm" varStatus="loop">
					<div class="gm-grid-list-box" style="background-image:url(<c:url value="/inc/img/gm/${gm.value.image}"></c:url>); background-repeat: no-repeat; background-position: center;">
						<a class="gm-grid-list-button grid-list-button button" href="<c:url value="GestionRecompensesGM"></c:url>?paramTab=0&idAge=<c:out value="${gm.value.age.id}"></c:out>&idGM=<c:out value="${gm.value.id}"></c:out>"><span><c:out value="${gm.value.name}"></c:out></span></a>
					</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>