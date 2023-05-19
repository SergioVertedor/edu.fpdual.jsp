<%@ page import="edu.fpdual.jsp.web.dto.UsuarioDto" %>
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
   <h1>Juego de memoria USB</h1>
       <%
           String userAnswer = request.getParameter("answer");
           if (userAnswer != null) {
               int remainingSpace = 32 * 1024 - (5 * 500);
               String resultMessage;

               if (userAnswer.equals(String.valueOf(remainingSpace))) {
                   resultMessage = "¡Respuesta correcta! Al pendrive le queda " + remainingSpace + "MB de memoria.";
               } else {
                   resultMessage = "Respuesta incorrecta. Inténtalo de nuevo.";
               }

               out.println("<p>" + resultMessage + "</p>");
           }
       %>
       <form action="/memorias" method="post">
           <label>Si tengo un pendrive con 32GB y agrego 5 imágenes que pesan 500MB cada una, ¿cuánta memoria le queda al pendrive?</label>
           <input type="text" name="answer" required>
           <br><br>
           <input type="submit" value="Enviar respuesta">
       </form>
</body>
</html>
