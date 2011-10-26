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
				<div style="float: left; width: 48%;" align="right">
					<form method="post" action="agregarEsfera" >
						<input type="submit" id="agregarEsfera"  value="Agregar Esferas" />
					</form>
				</div>
				<div style="float: right; width: 48%;" align="left">
					<form method="post" action="agregarPersonaje">
						<input type="submit"  id="agregarPersonajes" value="Agregar Personajes" />
					</form>
				</div>
					<br/>
					<br/>
					<h3>Dimension: ${sessionScope.mapa.dimension}</h3>
					<table>
						<tr>
							<td></td>
							<c:forEach items="${sessionScope.mapa.getFila(statusColumna.index)}" var="fila" varStatus="statusFila">
								<td>
							 		<center>
										${statusFila.index}
							 		</center>
								</td>
							</c:forEach>
						</tr>
						<c:forEach items="${sessionScope.mapa.getColumna(0)}" var="columna" varStatus="statusColumna" >
							<tr>
								<td>
									${statusColumna.index}
								</td>
								<c:forEach items="${sessionScope.mapa.getFila(statusColumna.index)}" var="fila" varStatus="statusFila">
									<td>
										<img src="images/${fila.objeto.name.toLowerCase()}.png"/>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
					<br/>
			</c:if>
	 		</center>
		</div>
	
		<%@include file="pages/footer.jsp"%>
		</div>
	</body>
</html>
