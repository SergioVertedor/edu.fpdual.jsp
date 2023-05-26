<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><a href="/comun/ahorcado.jsp">Ahorcado</a></li>
                <li><a href="/comun/traductor.jsp">Traduce las palabras</a></li>
                <li><a href="/comun/memoria.jsp">Calcula la memoria</a></li>
                <li><a href="/contact/form.jsp">Contactanos</a></li>
            </ul>
        </nav>
    </div>
</section>
<br>
<br>
    <h1>Juego de Traducción</h1>

    <% String palabra = (String) request.getAttribute("palabra");
       boolean respuestaValida = request.getAttribute("respuestaValida") != null ? (boolean) request.getAttribute("respuestaValida") : false;
       int puntuacion = request.getAttribute("puntuacion") != null ? (int) request.getAttribute("puntuacion") : 0;
    %>

    <% if (palabra != null && !palabra.isEmpty()) { %>
        <form action="/traductor-servlet" method="post">
            <p>Traduce la siguiente palabra:</p>
            <p><strong><%= palabra %></strong></p>
            <input type="text" name="respuesta" placeholder="Escribe aquí tu respuesta" required>
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
        <% if (palabra == null || palabra.isEmpty()) { %>
            <button type="submit">Iniciar Juego</button>
        <% } else { %>
            <button type="submit">Reiniciar Juego</button>
        <% } %>
    </form>

    <h1>Juego de calcular el espacio de dispositivos de almacenamiento</h1>
    <form action="/comun/memoria.jsp" method="post">
        <input type="submit" value="Jugar">
    </form>
<footer id="footer" class="wrapper style1-alt">
    <div class="inner">
        <ul class="menu">
            <li>&copy; HackealaPalabra. Todos los derechos reservados.</li>
        </ul>
    </div>
</footer>
</body>
</html>
