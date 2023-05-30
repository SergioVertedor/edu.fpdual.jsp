<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuarios</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/registro/style/style.css">
    <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg">
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg">
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
<form action="/registro-servlet" method="POST">
    <div>
        <input type="text" id="nombre" name="nombre" placeholder="Nombre" required><span class="highlight"></span><span class="bar"></span>
    </div>
    <br>
    <div>
        <input type="email" id="correo" name="correo" placeholder="Email" required><span class="highlight"></span><span class="bar"></span>
    </div>
    <br>
    <div>
        <input type="password" id="password" name="password" placeholder="Contraseña" required><span class="highlight"></span><span class="bar"></span>
    </div>
    <br>
    <div>
        <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirma la contraseña" required><span class="highlight"></span><span class="bar"></span>
    </div>
    <br>
    <% String mensaje = (String) request.getAttribute("error");
     if (mensaje != null){  %>
     <p class='error'><%= mensaje %></p>
     <% } %>
    <button type="submit" class="button buttonBlue">Register
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
    <br>
    <br>
    <div>
        <a href="/login/login.jsp" target="_blank" >¿Tienes un usuario? ¡Inicia sesion aquí!</a>
    </div>
</form>
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
