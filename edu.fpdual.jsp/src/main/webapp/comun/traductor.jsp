<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/comun/style/stylee.css" type="text/css">
    <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <title>Traductor de palabras</title>
</head>
<body>

      <h1>Juego de Traducción</h1>

          <% String palabra = (String) request.getAttribute("palabra");
          if (palabra != null && !palabra.isEmpty()) { %>
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

          <form action="/traductor-servlet" method="post">
              <input type="hidden" name="reiniciar" value="true">
              <% if (palabra == null || palabra.isEmpty()) { %>
                  <button type="submit">Iniciar Juego</button>
              <% } else { %>
                  <button type="submit">Reiniciar Juego</button>
              <% } %>
          </form>
</body>
</html>
