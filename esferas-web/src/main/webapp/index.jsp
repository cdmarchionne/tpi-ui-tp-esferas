<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<?xml version='1.0' encoding='UTF-8'?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>DRAGON BALL</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<script type="text/javascript" src="scripts/formulario.js"></script>
		<script>
			function validar_crear_mapa(){
			    return (validar_campo("dimX") && validar_campo("dimY"));
			}
		</script>
	</head>

	<body>
		<%@include file="pages/header.jsp"%>
	
		<div>
			<center>
				<c:choose>
					<c:when test="${sessionScope.mapa == null}">
						<h2>Creacion de Mapa</h2>
						<form name="mapa" method="post" action="mapa" onsubmit="return validar_crear_mapa()">
							<label for="dimencion">Dimencion</label>
							<br/>
							<label for="dimencionX">X</label>
							<input type="text" id="dimX" name="dimX" title="Horizontal" value="${param.dimX}" maxlength="2" />
							<br/>
							<label for="dimencionY">Y</label>
							<input type="text" id="dimY" name="dimY" title="Vertical" value="${param.dimY}" maxlength="2"/>
							<br/>
							<input type="submit" value="Crear Mapa" />
						</form>
					</c:when>
					<c:otherwise>
						<c:redirect url="mapa.jsp" />
					</c:otherwise>
				</c:choose>
			</center>
		</div>
	
		<%@include file="pages/footer.jsp"%>
	</body>
</html>
