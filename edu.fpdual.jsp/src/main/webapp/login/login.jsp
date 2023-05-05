<!DOCTYPE html>
<html>
<head>
	 <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<style>
		body {
			background-color: turquoise;
			font-family: Arial, sans-serif;
		}

		.container {
			margin: 50px auto;
			max-width: 500px;
			padding: 30px;
			background-color: pink;
			box-shadow: 0px 0px 10px 1px rgba(0,0,0,0.1);
			border-radius: 5px;
		}

		h1 {
			text-align: center;
			margin-bottom: 30px;
		}

		label {
			display: block;
			margin-bottom: 10px;
			font-weight: bold;
		}

		input[type="text"], input[type="password"] {
			width: 100%;
			padding: 10px;
			border-radius: 3px;
			border: 1px solid #CCC;
			box-sizing: border-box;
			margin-bottom: 20px;
		}

		button[type="submit"], button[type="reset"] {
			background-color: #4CAF50;
			border: none;
			color: white;
			padding: 10px 20px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 14px;
			margin: 4px 2px;
			cursor: pointer;
			border-radius: 3px;
			box-shadow: 0px 0px 5px 1px rgba(0,0,0,0.1);
		}

		button[type="submit"]:hover, button[type="reset"]:hover {
			background-color: #3E8E41;
		}

		button[type="reset"] {
			background-color: #F44336;
			margin-left: 10px;
		}

		button[type="reset"]:hover {
			background-color: #F44336;
		}
	</style>
</head>
<body>
<<<<<<< HEAD
<script src="js.js"></script>
<hgroup>
    <h1>HackeaPalabra</h1>
</hgroup>
<form action="/servlet-login" method="POST">
    <div class="group">
        <input type="text" id="nombre" name="nombre"><span class="highlight"></span><span class="bar"></span>
        <label>Name</label>
    </div>
    <div class="group">
        <input type="password" id="password" name="password"><span class="highlight"></span><span class="bar"></span>
        <label>password</label>
    </div>
    <button type="submit" class="button buttonBlue">Login
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
    <a href="../registro/registro.jsp" target="_blank">Dont have an account? Click here to register!</a>
</form>
=======
	<div class="container">
		<h1>Login</h1>
		<form action="/servlet-login" method="POST">
			<label for="usuario">User:</label>
			<input type="text" name="usuario" id="usuario">

			<label for="contrasena">Password:</label>
			<input type="password" name="contrasena" id="contrasena">
			<div>
				<button type="submit">Iniciar Sesión</button>
				<button type="reset">Cancelar</button>
			</div>
		</form>
	</div>
>>>>>>> dori
</body>
</html>
