<%@ page import="edu.fpdual.jsp.persistence.dao.Usuario" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Panel de Control</title>
</head>
<% %>
<body>
<h1>Eliminar usuario</h1>
<form method="post" action="/cpanel-eliminar-usuario-servlet">
	<label for="id_usuario">ID de usuario:</label>
	<input type="text" id="id_usuario" name="id_usuario">
	<br><br>
	<% String mensaje = (String) request.getAttribute("notificacion");
		if (mensaje != null) { %>
	<p class='notificacion'><%= mensaje %>
	</p>
	<% } %>
	<input type="submit" value="Eliminar">
</form>

<h1>Modificar Usuario</h1>
<form action="/cpanel-consultar-usuario-servlet" method="post">
	<label for="id">Introduzca ID:</label>
	<input type="text" id="id" name="id" required>
	<% mensaje = (String) request.getAttribute("notificacionConsulta");
		if (mensaje != null) { %>
	<p class='notificacion'><%= mensaje %>
	</p>
	<% } %>
	<button type="submit">Comprobar</button>
</form>
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
<br><br>
<form action="/cpanel-modificar-usuario-servlet" method="post">
	<label for="identificador">ID:</label>
	<input type="text" id="identificador" name="identificador" value="<%= identificador %>" readonly><br>
	<label for="nombreUsuario">Usuario:</label>
	<input type="text" id="nombreUsuario" name="nombreUsuario" value="<%= usuario %>" readonly><br>

	<label for="correo">Correo:</label>
	<input type="email" id="correo" name="correo" value="<%= correo %>"><br>

	<label for="password">Contraseña:</label>
	<input type="password" id="password" name="password" value="<%= password %>"><br>
	<% mensaje = (String) request.getAttribute("notificacionUpdate");
		if (mensaje != null) { %>
	<p class='notificacionUpdate'><%= mensaje %>
	</p>
	<% } %>
	<button type="submit">Modificar</button>
</form>
<form action="/cpanel-listar-usuarios-servlet" method="post">
	<h2>Lista de usuarios</h2>
	<button type="submit">Mostrar usuarios</button>
</form>
<br><br>
<%List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
if (lista != null) {%>
<table>
	<thead>
	<tr>
		<th>ID</th>
		<th>Usuario</th>
		<th>Correo</th>
	</tr>
	</thead>
	<tbody>
	<% for (Usuario user : lista) { %>
	<tr>
		<td><%= user.getId() %>
		</td>
		<td><%= user.getNombre() %>
		</td>
		<td><%= user.getCorreo() %>
		</td>
		<td><a href="/cpanel-consultar-usuario-servlet?id=<%= user.getId() %>">Editar</a></td>
		<td><a href="URL">Eliminar</a></td>
	</tr>
	<%}%>
	</tbody>
</table>
<%}%>
</body>
</html>