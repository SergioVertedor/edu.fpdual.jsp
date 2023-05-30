<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/login/style/style.css">
	<title>Login</title>

</head>
<body>
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><a href="/index.html">Inicio</a></li>
                <li><a href="/contact/form.jsp">Contactanos</a></li>
            </ul>
        </nav>
    </div>
</section>
<hgroup>
	<h1>Hackea la palabra</h1>
</hgroup>
<form action="/servlet-login" method="POST">
	<div>
		<input type="text" id="usuario" name="usuario" placeholder="Usuario"><span class="highlight"></span><span class="bar"></span>
	</div>
	<br>
	<div>
		<input type="password" id="contrasena" name="contrasena" placeholder="Contraseña"><span class="highlight"></span><span class="bar"></span>
	</div>
	<br>
	<% String mensaje = (String) request.getAttribute("error");
	if (mensaje != null){
			%>
	<div><p class='error'><%= mensaje %></p></div>
	<% } %>
	<% mensaje = (String) request.getAttribute("notificacion");
		if (mensaje != null){
	%>
	<div><p class='notificacion'><%= mensaje %></p></div>
	<% } %>
	<button type="submit" class="button buttonBlue">Login
		<div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
	</button>
	<br>
	<br>
	<div>
	    <a href="/registro/registro.jsp" target="_self">¿No tienes cuenta aún? ¡Haz click aquí para registrarte!</a>
    </div>
</form>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<footer id="footer" class="wrapper style1-alt">
    <div class="inner">
        <ul class="menu">
            <li>&copy; HackealaPalabra. Todos los derechos reservados.</li>
        </ul>
    </div>
</footer>
</body>
</html>
