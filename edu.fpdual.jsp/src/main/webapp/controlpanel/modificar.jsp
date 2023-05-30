<%--
  Created by IntelliJ IDEA.
  User: s.vertedor.beltran
  Date: 11/05/2023
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="edu.fpdual.jsp.client.dto.UsuarioDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
<link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
<link rel="stylesheet" href="/controlpanel/style/style.css" type="text/css" >
	<title>Modificacion</title>
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
<h1>Modificacion de usuario:</h1>
<%
	String identificador = (String) request.getAttribute("id");
	String usuarioModificado = (String) request.getAttribute("nombreUsuario");
	String correo = (String) request.getAttribute("correo");
	String password = (String) request.getAttribute("password");
	if (identificador == null) {
		identificador = "";
	}
	if (usuarioModificado == null) {
		usuarioModificado = "";
	}
	if (correo == null) {
		correo = "";
	}
	if (password == null) {
		password = "";
	}%>
<form action="/cpanel-modificar-usuario-servlet" method="post">
	<label class="noMod" for="identificador">ID:</label>
	<input type="text" id="identificador" name="identificador" value="<%= identificador %>" readonly><br>
	<label class="noMod" for="nombreUsuario">Usuario:</label>
	<input type="text" id="nombreUsuario" name="nombreUsuario" value="<%= usuarioModificado %>" readonly><br>

	<label for="correo">Correo:</label>
	<input type="email" id="correo" name="correo" value="<%= correo %>"><br>

	<label for="password">Contraseña:</label>
	<input type="password" id="password" name="password" value="<%= password %>"><br>
	<% String mensaje = (String) request.getAttribute("notificacion");
		if (mensaje != null) { %>
	<p class='notificacion'><%= mensaje %>
	</p>
	<% } %>
	<button type="submit">Modificar</button>

</form>
</body>
</html>
