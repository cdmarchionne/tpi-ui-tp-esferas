<%@ page isELIgnored ="false" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<?xml version='1.0' encoding='UTF-8'?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>DRAGON BALL</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<script type="text/javascript" >
			function validar_habilitar_agregar_esfera(){
			    return ($sessionScope.mapa.faltanCrearEsferas);
			}
		</script>
	</head>

	<body>
		<div>
		<%@include file="pages/header.jsp"%>
	
		<div>
			<center>
			<h2>Mapa</h2>
			<c:if test="${sessionScope.mapa != null}">
				<form method="post" action="agregarEsfera" >
					<input type="submit" id="agregarEsfera"  value="Agregar Esferas" />
				</form>
				<form method="post" action="agregarPersonaje">
					<input type="submit"  id="agregarPersonajes" value="Agregar Personajes" />
				</form>
				 <br/>
				<h3>Dimension: ${sessionScope.mapa.dimension}</h3>
				<table>
					<tr>
						<td>Posicion</td>
						<td>Elemento</td>
						<td>Imagen</td>
					</tr>
					<c:forEach items="${sessionScope.mapa.casilleros}" var="casillero" >
						<tr>
							<td>${casillero.posicion}</td>
							<td>${casillero.objeto}</td>
							<td><img src="images/${casillero.objeto.name.toLowerCase()}.png"/></td>
						</tr>
					</c:forEach>

					<!--
					<c:forEach items="${sessionScope.mapa.getFila(0)}" var="fila" >
						<tr>
							<td>
								${fila.objeto}
								<img src="images/${casillero.objeto.name.toLowerCase()}.png" class="Posicionable"/>
							</td>
						</tr>
					</c:forEach>
					 -->
				</table>
				<br/>

			</c:if>
	 		</center>
		</div>
	
		<%@include file="pages/footer.jsp"%>
		</div>
	</body>
</html>
