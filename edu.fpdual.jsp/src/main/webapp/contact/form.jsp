<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Formulario de Contacto</title>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<h1>Formulario de Contacto</h1>

<form action="/send" method="POST">
	<label for="nombre">Nombre:</label><br>
	<input type="text" id="nombre" name="nombre"><br><br>

	<label for="email">Correo electrónico:</label><br>
	<input type="email" id="email" name="email"><br><br>

	<label for="opinion">Opinión o Sugerencias:</label><br>
	<textarea id="opinion" name="opinion" rows="5" cols="30"></textarea><br><br>

	<input type="submit" value="Enviar">
</form>
</body>
</html>
