<%@ page isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<?xml version='1.0' encoding='UTF-8'?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>ERROR - DRAGON BALL</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>

	<body>
		<%@include file="pages/header.jsp"%>
	
		<div>
			<center>
			<h1>${mensajeError}</h1>
			</center>
		</div>
	
		<%@include file="pages/footer.jsp"%>
	</body>
</html>
