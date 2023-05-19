<%@ page import="edu.fpdual.jsp.persistence.dao.UsuarioDao" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
	<title>Panel de Control</title>
</head>
<body>

<form action="/cpanel-listar-usuarios-servlet" method="post">
	<h2>Lista de usuarios</h2>
	<button type="submit">Mostrar usuarios</button>
</form>
<br><br>
<%List<UsuarioDao> lista = (List<UsuarioDao>) request.getAttribute("lista");
if (lista != null) {%>
<% String mensaje = (String) request.getAttribute("notificacion");
	if (mensaje != null) { %>
<p class='notificacion'><%= mensaje %>
<% } %>
<table>
	<thead>
	<tr>
		<th>ID</th>
		<th>Usuario</th>
		<th>Correo</th>
	</tr>
	</thead>
	<tbody>
	<% for (UsuarioDao user : lista) { %>
	<tr>
		<td><%= user.getId() %>
		</td>
		<td><%= user.getNombre() %>
		</td>
		<td><%= user.getCorreo() %>
		</td>
		<td><a href="/cpanel-consultar-usuario-servlet?id=<%= user.getId() %>">Editar</a></td>
		<td><a href="/cpanel-eliminar-usuario-servlet?id=<%= user.getId() %>">Eliminar</a></td>
	</tr>
	<%}%>
	</tbody>
</table>
<%}%>
</body>
</html>