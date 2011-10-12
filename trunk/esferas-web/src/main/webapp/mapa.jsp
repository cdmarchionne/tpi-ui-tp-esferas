<%@ page isELIgnored ="false" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<?xml version='1.0' encoding='UTF-8'?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>DRAGON BALL</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>

	<body>
		<center>
		<h1>DRAGON BALL</h1>
		<h2>Mapa</h2>
		<c:if test="${sessionScope.mapa != null}" >
			<h3>Dimension:	${sessionScope.mapa.dimension} </h3>
			 <table>
					<tr>
						<td>Posicion</td>
				    	<td>Elemento</td>
				    </tr>
				<c:forEach items="${sessionScope.mapa.casilleros}" var="casillero" varStatus="status">
					<tr>
						<td>${casillero.posicion}</td>
				    	<td>${casillero.objeto}</td>
				    </tr>
				</c:forEach>
			</table>
			</br>
			<input type="submit" value="Agregar Esferas" />
			<input type="submit" value="Agregar Personajes" />
			</br>

		</c:if>
	</body>
</html>
