<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario de Contacto</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/contact/style/style.css">
    <link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
    <link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
</head>
<body>
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><a href="/index.html">Inicio</a></li>
                <li><a href="/send">Contactanos</a></li>
            </ul>
        </nav>
    </div>
</section>
<br>
<div>
    <h1>Formulario de Contacto</h1>
</div>
<br>

<form action="/send" method="POST">
    <input type="text" id="nombre" name="nombre" placeholder="Usuario" required><br>

    <input type="email" id="email" name="email" placeholder="Email" required><br>

    <textarea id="opinion" name="opinion" rows="5" cols="30" placeholder="Opinión o Sugerencias"></textarea><br>

    <% String mensaje = (String) request.getAttribute("notificacion");
        if (mensaje != null) { %>
    <p class='notificacion'><%= mensaje %>
    </p>
    <% } %>
    <input type="submit" value="Enviar">

</form>
</body>
</html>
