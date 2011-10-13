<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<?xml version='1.0' encoding='UTF-8'?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>DRAGON BALL</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>

<body>
	<center>
		<div>
			<%@include file="/WEB-INF/header.jsp"%>
			
			<div>
			<c:choose>
				<c:when test="${sessionScope.mapa == null}">
					<h2>Creacion de Mapa</h2>
					<form method="post" action="mapa">
						<label for="dimencion">Dimencion</label> <br />
						<label for="dimencionX">X</label> <input type="text" name="x"
							value="${param.x}" /> <br /> <label for="dimencionY">Y</label> 
							<input type="text" name="y" value="${param.y}" /> <br /> 
							<input type="submit" value="Crear Mapa" />
					</form>
				</c:when>
				<c:otherwise>
					<c:redirect url="mapa.jsp"/>
				</c:otherwise>
			</c:choose>
			</div>
			
			<%@include file="/WEB-INF/footer.jsp"%>
		</div>	
</body>
</html>
