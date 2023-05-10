<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Eliminar usuario</title>
</head>
<body>
	<h1>Eliminar usuario</h1>
	<form method="post" action="/cpanel-eliminar-usuario-servlet">
		<label for="id_usuario">ID de usuario:</label>
		<input type="text" id="id_usuario" name="id_usuario">
		<br><br>
		<% String mensaje = (String) request.getAttribute("notificacion");
             if (mensaje != null){  %>
             <p class='notificacion'><%= mensaje %></p>
             <% } %>
		<input type="submit" value="Eliminar">
	</form>

</body>
</html>