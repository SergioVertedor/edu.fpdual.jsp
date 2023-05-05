<!DOCTYPE html>
<html lang="es">
<% >
<head>
    <meta charset="UTF-8"/>
    <title>Registro de Usuarios</title>
    <link rel="stylesheet" href="./style/style.css">
</head>
<body>
<script src="js.js"></script>
<hgroup>
    <h1>HackeaPalabra</h1>
</hgroup>
<form action="/servlet-registro" method="POST">
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
    <button type="submit" class="button buttonBlue">Register
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
    <a href="login.html" target="_blank" >You have an account? Login here!</a>
</form>
</body>
</html>
