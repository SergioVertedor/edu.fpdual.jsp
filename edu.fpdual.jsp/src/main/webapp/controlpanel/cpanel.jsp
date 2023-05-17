<%@ page import="edu.fpdual.jsp.persistence.dao.UsuarioDao" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Panel de Control</title>
	<link rel="stylesheet" type="text/css" href="/controlpanel/style/style.css"/>
</head>
<body>
<hgroup>
    <h1>Lista de usuarios</h1>
</hgroup>
<form action="/cpanel-listar-usuarios-servlet" method="post">
	<button type="submit" class="button buttonBlue">Mostrar usuarios
	    <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
	</button>
</form>
<br><br>
<%List<UsuarioDao> lista = (List<UsuarioDao>) request.getAttribute("lista");
if (lista != null) {%>
<% String mensaje = (String) request.getAttribute("notificacion");
	if (mensaje != null) { %>
<p class='notificacion'><%= mensaje %>
<% } %>
<form>
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
</form>
<%}%>
</body>
</html>