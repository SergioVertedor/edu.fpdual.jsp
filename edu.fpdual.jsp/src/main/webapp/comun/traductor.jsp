<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.fpdual.jsp.client.dto.UsuarioDto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/comun/style/styleTraductor.css" type="text/css">
    <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <title>Traductor de palabras</title>
</head>
<body>
<%
    UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuarioSesion");
%>
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><p> Bienvenido, <%= usuario.getNombre() %></p></li>
                <li><a href="/index.html">Inicio</a></li>
                <li><a href="/ahorcado">Ahorcado</a></li>
                <li><a href="/traductor-servlet">Traduce las palabras</a></li>
                <li><a href="/memorias">Calcula la memoria</a></li>
                <li><a href="/ranking">Ranking</a></li>
                <%
                    if (usuario.getNombre().equals("admin")) {%>
                <li><a href="/cpanel-listar-usuarios-servlet">Acceso a Panel de Control</a></li>
                <%}%>
                <li><a href="/logout">Cerrar sesión</a></li>
            </ul>
        </nav>
    </div>
</section>
<br>
<br>
<h1>Juego de Traducción</h1>

<%
    String palabraActual = (String) session.getAttribute("palabraActual");
    boolean respuestaValida = request.getAttribute("mensaje") != null && request.getAttribute("mensaje").equals("¡Respuesta correcta!");
    int puntuacion = (int) session.getAttribute("puntuacion");
%>

<% if (palabraActual != null && !palabraActual.isEmpty()) { %>
    <form action="/traductor-servlet" method="post">
        <div>
            <p>Traduce la siguiente palabra en inglés:</p>
            <p><strong><%= palabraActual %></strong></p>
        </div>
        <br>
        <div>
            <input type="text" name="respuesta" placeholder="Escribe aquí tu respuesta" required>
        </div>
        <br>
        <button type="submit">Verificar</button>
    </form>
<% } %>

<% String mensaje = (String) request.getAttribute("mensaje");
if (mensaje != null) { %>
    <p><%= mensaje %></p>
<% } %>

<p>Puntuación: <%= puntuacion %></p>

<form action="/traductor-servlet" method="post">
    <input type="hidden" name="reiniciar" value="true">
    <% if (palabraActual == null || palabraActual.isEmpty()) { %>
        <button type="submit">Iniciar Juego</button>
    <% } else { %>
        <button type="submit">Reiniciar Juego</button>
    <% } %>
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
