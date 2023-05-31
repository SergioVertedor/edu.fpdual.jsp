<%@ page import="edu.fpdual.jsp.client.dto.UsuarioDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: s.vertedor.beltran
  Date: 26/05/2023
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ranking</title>
<link rel="icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
<link rel="shortcut icon" href="/comun/images/favicon.jpg" type="image/jpeg" />
<link rel="stylesheet" href="/leaderboard/style/style.css">
</head>
<body>
<%
    UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuarioSesion");
%>
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><p> Bienvenido, <%= usuario.getNombre() %></p></li>
                <li><a href="/ahorcado">Ahorcado</a></li>
                <li><a href="/traductor-servlet">Traduce las palabras</a></li>
                <li><a href="/memorias">Calcula la memoria</a></li>
                <li><a href="/ranking">Ranking</a></li>
                <%
                    if (usuario.getNombre().equals("admin")) {%>
                <li><a href="/cpanel-listar-usuarios-servlet">Acceso a Panel de Control</a></li>
                <%}%>
                <li><a href="/logout">Cerrar sesión</a></li>
            </ul>
        </nav>
    </div>
</section>
<br>
<br>
<h1>Ranking:</h1>
<%List<UsuarioDto> lista = (List<UsuarioDto>) request.getAttribute("lista"); %>
<table>
    <thead>
    <tr>
        <th>Usuario</th>
        <th>Puntos</th>
    </tr>
    </thead>
    <tbody>
        <% for (UsuarioDto user : lista) { %>
    <tr>
        <td><%= user.getNombre() %>
        </td>
        <td><%= user.getPuntos() %>
        </td>
    </tr>
        <%}%>
</body>
</html>
