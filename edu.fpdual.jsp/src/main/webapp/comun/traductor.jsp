<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/comun/style/stylee.css" type="text/css" >
    <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
<title>Traductor de palabras</title>
</head>
<body>
  <%
      // Obtener la lista de palabras de la sesión o crear una nueva si no existe
      List<String[]> palabras = (List<String[]>) session.getAttribute("palabras");
      if (palabras == null) {
          palabras = new ArrayList<String[]>();
          palabras.add(new String[]{"hola", "hello"});
          palabras.add(new String[]{"adios", "goodbye"});
          palabras.add(new String[]{"buenos dias", "good morning"});
          palabras.add(new String[]{"buenas tardes", "good afternoon"});
          palabras.add(new String[]{"buenas noches", "good night"});
          Collections.shuffle(palabras);
          session.setAttribute("palabras", palabras);
      }

      String palabra = "";
      String traduccion = "";

      if (!palabras.isEmpty()) {
          String[] palabraAleatoria = palabras.get(0);
          palabra = palabraAleatoria[0];
          traduccion = palabraAleatoria[1];
      }

      // Obtener la respuesta del usuario
      String respuesta = request.getParameter("respuesta");
      boolean respuestaValida = false;

      // Validar la respuesta
      if (respuesta != null && respuesta.equals(traduccion)) {
          respuestaValida = true;
      }

      // Mostrar el resultado
      if (respuestaValida) {
          out.println("<p>¡Respuesta correcta!</p>");
          palabras.remove(0); // Eliminar la palabra actual de la lista
          session.setAttribute("palabras", palabras); // Actualizar la lista en la sesión
      } else if (respuesta != null) {
          out.println("<p>Respuesta incorrecta. La traducción correcta es: " + traduccion + "</p>");
      }
  %>

  <form method="post">
      <p>Traduce la siguiente palabra:</p>
      <% if (!palabras.isEmpty()) { %>
          <p>Palabra a traducir: <%= palabra %></p>
          <label for="respuesta">Traducción:</label>
          <input type="text" id="respuesta" name="respuesta">
          <input type="submit" value="Enviar">
      <% } else { %>
          <p>No hay más palabras para traducir.</p>
      <% } %>
  </form>

  <form action="" method="post">
      <input type="hidden" name="action" value="reset">
      <input type="submit" value="Reiniciar juego">
  </form>


</body>
</html>
