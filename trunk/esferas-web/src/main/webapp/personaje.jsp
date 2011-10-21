<%@ page isELIgnored ="false" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<?xml version='1.0' encoding='UTF-8'?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>DRAGON BALL</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<script type="text/javascript" src="scripts/formulario.js"></script>
		<script>
			function validar_crear_personaje(){
			    return (validar_campo("x") && validar_campo("y"));
			}
		</script>
	</head>

	<body>
		<div>
		<%@include file="pages/header.jsp"%>
	
		<div>
			<center>
			<h2>Personaje</h2>
			<br/>
			<form name="crearPersonaje" method="post" action="crearPersonaje" onsubmit="return validar_crear_personaje()">
				<br/>
					<h3>Posicion</h3>
				<br/>
					<label for="posX">X</label>
					<input type="text" id="x" name="x" value="${param.x}" />
				<br/>
					<label for="posY">Y</label>
					<input type="text" id="y" name="y" value="${param.y}" />
				<br/>
				<br/>
					<h3>Nombre</h3>
					<select id="personaje" name="personaje" class="Personaje.NombrePersonaje" >
						<c:forEach items="${sessionScope.mapa.listaPersonajesNoCreadas}" var="personaje" varStatus="status">
								<option value="${status.index}">${personaje.nombrePersonaje}</option>
						</c:forEach>
					</select>
				<br/>
				<br/>
					<label for="distancia">Distancia</label>
					<input type="text" id="distancia" name="distancia" value="${param.distancia}" />
				<br/>
				
				<br/>
					<input type="submit" value="Agregar" />
					<input type="button" value="Cancelar" />
				<br/>
			</form>
	 		</center>
		</div>
	
		<%@include file="pages/footer.jsp"%>
		</div>
	</body>
</html>
