
<%@ page import="edu.fpdual.jsp.client.dto.UsuarioDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/comun/style/styleMemoria.css" type="text/css" >
    <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
<body>
<%
    UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuarioSesion");
%>
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><p> Bienvenido, <%= usuario.getNombre() %></p></li>
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
   <h1>Juego de memoria USB</h1>
      <% String mensaje = (String) request.getAttribute("mensaje");
         Integer puntuacion = (Integer) request.getAttribute("puntuacion"); // Cambio aquí

         if (mensaje != null) {
             out.println("<p>" + mensaje + "</p>");
         }
         if (puntuacion != null) { // Verificación adicional
             out.println("<p>Puntuación: " + puntuacion + "</p>");
         }
      %>
      <form action="/memorias" method="post">
          <label>Si tengo un pendrive con 32GB y agrego 5 imágenes que pesan 500MB cada una, ¿cuánta memoria le queda al pendrive?</label>
          <div>
          <input type="text" name="answer" required>
          <div>
          <br><br>
          <input type="submit" value="Enviar respuesta">
      </form>
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