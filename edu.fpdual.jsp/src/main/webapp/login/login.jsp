<!DOCTYPE html>
<html>
<head>
	 <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/login/style/style.css">
	<title>Login</title>

</head>
<body>
<hgroup>
    <h1>HackeaPalabra</h1>
</hgroup>
<form action="/servlet-login" method="POST">
    <div class="group">
        <input type="text" id="usuario" name="usuario"><span class="highlight"></span><span class="bar"></span>
        <label>Name</label>
    </div>
    <div class="group">
        <input type="password" id="contrasena" name="contrasena"><span class="highlight"></span><span class="bar"></span>
        <label>password</label>
    </div>
    <% String mensaje = (String) request.getAttribute("error");
        if (mensaje != null){  %>
    <p class='error'><%= mensaje %></p>
    <% } %>
    <button type="submit" class="button buttonBlue">Login
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
    <a href="/registro/registro.jsp" target="_blank">Dont have an account? Click here to register!</a>
</form>

	</div>
</body>
</html>
