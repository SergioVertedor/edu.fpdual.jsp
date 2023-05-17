<%--
  Created by IntelliJ IDEA.
  User: s.vertedor.beltran
  Date: 11/05/2023
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<br><br>
<%
	String identificador = (String) request.getAttribute("id");
	String usuario = (String) request.getAttribute("nombreUsuario");
	String correo = (String) request.getAttribute("correo");
	String password = (String) request.getAttribute("password");
	if (identificador == null) {
		identificador = "";
	}
	if (usuario == null) {
		usuario = "";
	}
	if (correo == null) {
		correo = "";
	}
	if (password == null) {
		password = "";
	}%>
<form action="/cpanel-modificar-usuario-servlet" method="post">
	<label for="identificador">ID:</label>
	<input type="text" id="identificador" name="identificador" value="<%= identificador %>" readonly><br>
	<label for="nombreUsuario">Usuario:</label>
	<input type="text" id="nombreUsuario" name="nombreUsuario" value="<%= usuario %>" readonly><br>

	<label for="correo">Correo:</label>
	<input type="email" id="correo" name="correo" value="<%= correo %>"><br>

	<label for="password">Contraseña:</label>
	<input type="password" id="password" name="password" value="<%= password %>"><br>
	<% String mensaje = (String) request.getAttribute("notificacionUpdate");
		if (mensaje != null) { %>
	<p class='notificacionUpdate'><%= mensaje %>
	</p>
	<% } %>
	<button type="submit">Modificar</button>

</form>
</body>
</html>
