<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<<<<<<< HEAD:edu.fpdual.jsp/src/main/webapp/login/login.jsp
	<link rel="stylesheet" href="/login/style/style.css">
=======
	<link rel="stylesheet" href="/style/style.css">
	<link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
>>>>>>> main:edu.fpdual.jsp/src/main/webapp/index.jsp
	<title>Login</title>

</head>
<body>
<hgroup>
	<h1>Hackea la palabra</h1>
</hgroup>
<form action="/servlet-login" method="POST">
	<div class="group">
		<input type="text" id="usuario" name="usuario"><span class="highlight"></span><span class="bar"></span>
		<label>Name</label>
	</div>
	<div class="group">
		<input type="password" id="contrasena" name="contrasena"><span class="highlight"></span><span class="bar"></span>
		<label>password</label>
	</div>
	<% String mensaje = (String) request.getAttribute("error");
		if (mensaje != null){  %>
	<p class='error'><%= mensaje %></p>
	<% } %>
	<% mensaje = (String) request.getAttribute("notificacion");
		if (mensaje != null){  %>
	<p class='notificacion'><%= mensaje %></p>
	<% } %>
	<button type="submit" class="button buttonBlue">Login
		<div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
	</button>
	<a href="/registro/registro.jsp" target="_self">¿No tienes cuenta aún? ¡Haz click aquí para registrarte!</a>
</form>

</div>
</body>
</html>
