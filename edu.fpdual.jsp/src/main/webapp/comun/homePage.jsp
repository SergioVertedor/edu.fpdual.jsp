<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/comun/style/style.css" type="text/css" >

<title>Juego de Adivinanza de Palabras</title>
</head>
<body>
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
<form method="get" action="">
<input type="submit" value="Reiniciar">
</form>
<% } else { %>
<form method="post" action="">
<input type="text" name="letra" maxlength="1" size="1">
<input type="submit" value="Adivinar">
</form>
<% } %>
</div>

<img src="/comun/images/adivina.png"></img>

</body>
</html>