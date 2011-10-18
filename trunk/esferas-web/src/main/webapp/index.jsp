<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<?xml version='1.0' encoding='UTF-8'?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>DRAGON BALL</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<!-- 
		<script language="JavaScript" type="text/javascript" src="scripts/formulario.js" />
		 -->
	</head>

	<body>
		<div>
		<%@include file="pages/header.jsp"%>
	
		<div>
			<center>
				<c:choose>
					<c:when test="${sessionScope.mapa == null}">
						<h2>Creacion de Mapa</h2>
						<form name="mapa" method="post" action="mapa">
							<label for="dimencion">Dimencion</label>
							<br/>
							<label for="dimencionX">X</label>
							<input type="text" name="dimX" value="${param.dimX}" maxlength="2"/>
							<br/>
							<label for="dimencionY">Y</label>
							<input type="text" name="dimY" value="${param.dimY}" maxlength="2"/>
							<br/>
							<input type="submit" value="Crear Mapa" onclick="valida_crear_mapa()"/>
						</form>
					</c:when>
					<c:otherwise>
						<c:redirect url="mapa.jsp" />
					</c:otherwise>
				</c:choose>
			</center>
		</div>
	
		<%@include file="pages/footer.jsp"%>
		</div>
	</body>
</html>
