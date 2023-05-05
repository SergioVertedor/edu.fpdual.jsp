<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Registro de Usuarios</title>
</head>
<body>
<h1>Registro de Usuarios</h1>
<form action="/servlet-registro" method="POST">
    <label for="nombre">Usuario:</label>
    <input type="text" id="nombre" name="nombre" required/><br/><br/>

    <label for="correo">Email:</label>
    <input type="email" id="correo" name="correo" required/><br/><br/>

    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required/><br/><br/>

    <label for="confirm_password">Confirmar Contraseña:</label>
    <input type="password" id="confirm_password" name="confirm_password" required/><br/><br/>

    <input type="submit" value="Registrarse"/>
</form>
</body>
</html>
