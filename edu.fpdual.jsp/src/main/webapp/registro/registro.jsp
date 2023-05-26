<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Registro de Usuarios</title>
       <link rel="stylesheet" type="text/css" href="/registro/style/style.css"/>
       <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
       <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
</head>
<body>
<hgroup>
    <h1>HackeaPalabra</h1>
</hgroup>
<form action="/registro-servlet" method="POST">
    <div class="group">
        <input type="text" id="nombre" name="nombre" required><span class="highlight"></span><span class="bar"></span>
        <label>Name</label>
    </div>
    <div class="group">
        <input type="email" id="correo" name="correo" required><span class="highlight"></span><span class="bar"></span>
        <label>Email</label>
    </div>
    <div class="group">
        <input type="password" id="password" name="password" required><span class="highlight"></span><span class="bar"></span>
        <label>Password</label>
    </div>
    <div class="group">
        <input type="password" id="confirm_password" name="confirm_password"><span class="highlight"></span><span class="bar"></span>
        <label>Confirm password</label>
    </div>
    <% String mensaje = (String) request.getAttribute("error");
     if (mensaje != null){  %>
     <p class='error'><%= mensaje %></p>
     <% } %>
    <button type="submit" class="button buttonBlue">Register
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
    <a href="/login/login.jsp" target="_blank" >You have an account? Login here!</a>
</form>
</body>
</html>
