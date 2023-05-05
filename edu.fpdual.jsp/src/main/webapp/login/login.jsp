<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <link rel="stylesheet" href="./style/style.css">
</head>
<body>
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
    <a href="register.html" target="_blank">Dont have an account? Click here to register!</a>
</form>
</body>
</html>