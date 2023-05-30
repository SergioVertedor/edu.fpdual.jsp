<%@ page import="edu.fpdual.jsp.client.dto.UsuarioDto" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="stylesheet" href="/controlpanel/style/style.css" type="text/css" >
	<title>Panel de Control</title>
	<link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
</head>
<body>
<%
    UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuarioSesion");
%>
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><p> Bienvenido, <%= usuario.getNombre() %></p></li>
                <li><a href="/index.html">Inicio</a></li>
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
<h1>Lista de usuarios:</h1>
<%List<UsuarioDto> lista = (List<UsuarioDto>) request.getAttribute("lista");
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
	<% for (UsuarioDto user : lista) { %>
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
<br><br>
<button onclick="window.location.href='/ahorcado'">Salir</button>

</body>
</html>