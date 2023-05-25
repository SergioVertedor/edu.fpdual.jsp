<%@ page import="edu.fpdual.jsp.client.dto.UsuarioDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/comun/style/style.css" type="text/css" >
    <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />

    <title>Juego de Adivinanza de Palabras</title>
</head>
<body>
<%
    UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuarioSesion");
%>
<p> Bienvenido, <%= usuario.getNombre() %></p>
<%
    if (usuario.getNombre().equals("admin")) {%>
<a href="/cpanel-listar-usuarios-servlet">Acceso a Panel de Control.</a>
<%}%>
    <div class="container">
        <h1>Juego de Adivinanza de Palabras</h1>
        <% String letrasAdivinadas = (String) request.getAttribute("letrasAdivinadas");
        int intentos = (int) request.getAttribute("intentos");
        String mensaje = (String) request.getAttribute("mensaje");

        Boolean reiniciar = (Boolean) request.getAttribute("reiniciar");
        boolean reiniciarBool = false;
        if (reiniciar != null) {
            reiniciarBool = reiniciar.booleanValue();
        }

        if (mensaje != null && !mensaje.isEmpty()) { %>
            <p class="message"><%= mensaje %></p>
        <% } %>

        <p><%= letrasAdivinadas %></p>
        <p>Te quedan <%= intentos %> intentos.</p>

        <% if (reiniciar != null && reiniciar.booleanValue()) { %>
            <form method="get" action="/ahorcado">
                <input type="submit" value="Reiniciar">
            </form>
        <% } else { %>
            <form method="post" action="/ahorcado">
                <input type="text" name="letra" maxlength="1" size="1">
                <input type="submit" value="Adivinar">
            </form>
        <% } %>
        <br><br>

        <img src="/comun/images/pensando.gif" id="imagen"></img>
    </div>

<h1>Presiona el botón para continuar</h1>
    <form action="/comun/traductor.jsp" method="post">
      <input type="submit" value="Continuar">
    </form>

</body>
</html>
