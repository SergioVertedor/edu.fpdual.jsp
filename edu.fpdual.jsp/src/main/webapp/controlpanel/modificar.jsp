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
	<meta charset="UTF-8">
    	<title>Panel de Control-Modificar</title>
    	<link rel="stylesheet" type="text/css" href="/controlpanel/style/style.css"/>
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
<hgroup>
    <h1>Modificar usuario</h1>
</hgroup>
<form action="/cpanel-modificar-usuario-servlet" method="post">
    <div class="group">
        <label for="identificador"></label>
        <input type="text" id="identificador" name="identificador" value="<%= identificador %>" readonly><br>
	</div>
	<div class="group">
        <label for="nombreUsuario"></label>
        <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%= usuario %>" readonly><br>
    </div>
    <div class="group">
        <label for="correo"></label>
        <input type="email" id="correo" name="correo" value="<%= correo %>"><br>
    </div>
    <div class="group">
        <label for="password"></label>
        <input type="password" id="password" name="password" value="<%= password %>"><br>
    </div>
	<% String mensaje = (String) request.getAttribute("notificacionUpdate");
		if (mensaje != null) { %>
	<p class='notificacionUpdate'><%= mensaje %>
	</p>
	<% } %>
	<button type="submit" class="button buttonBlue">Modificar
	    <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
	</button>

</form>
</body>
</html>
