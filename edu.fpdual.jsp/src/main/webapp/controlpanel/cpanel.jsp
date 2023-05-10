<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar usuario</title>
</head>
<body>
<h1>Eliminar usuario</h1>
<form method="post" action="${pageContext.request.contextPath}/cpanel-eliminar-usuario-servlet">
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
<% String usuario = (String) request.getAttribute("usuario");
    String correo = (String) request.getAttribute("correo");
    String password = (String) request.getAttribute("password");%>
<h1>Modificar Usuario</h1>
<form action="/cpanel-modificar-usuario-servlet" method="post">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" value="<%= usuario %>"><br>

    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" value="<%= correo %>"><br>

    <label for="contraseña">Contraseña:</label>
    <input type="password" id="contraseña" name="contraseña" value="<%= password %>"><br>

    <button type="submit">Registrarse</button>
</form>
</body>
</html>