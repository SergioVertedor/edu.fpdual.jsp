<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<div class="login">
	<h1>Login</h1>
    <form action="/servlet-login" method="POST">
    	<input type="text" name="u" placeholder="Username" required="required" />
        <input type="password" name="p" placeholder="Password" required="required" />
        	<% String mensaje = (String) request.getAttribute("error");
        		if (mensaje != null){  %>
        	<p class='error'><%= mensaje %></p>
        	<% } %>
        	<% mensaje = (String) request.getAttribute("notificacion");
        		if (mensaje != null){  %>
        	<p class='notificacion'><%= mensaje %></p>
        	<% } %>
        <button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
    </form>
</div>
</body>
</html>