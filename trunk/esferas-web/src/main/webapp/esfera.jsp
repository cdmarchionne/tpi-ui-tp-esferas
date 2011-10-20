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
			function validar_crear_esfera(){
			    return (validar_campo("x") && validar_campo("y"));
			}
		</script>
	</head>

	<body>
		<div>
		<%@include file="pages/header.jsp"%>
	
		<div>
			<center>
			<h2>Esferas</h2>
			<br/>
			<form name="crearEsfera" method="post" action="crearEsfera" onsubmit="return validar_crear_esfera()">
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
					<h3>Numero</h3>
					<!-- 
					<input type="text" name="numero" value="${param.numero}" />
					 -->
					 
					<select id="numero" name="numero" class="Esfera.CantidadEstrellas" >
						<c:forEach items="${sessionScope.mapa.listaEsferasNoCreadas}" var="esfera" varStatus="status">
								<option value="${esfera.cantidadEstrellas}">Esfera ${esfera.cantidadEstrellas}</option>
						</c:forEach>
					</select>
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
