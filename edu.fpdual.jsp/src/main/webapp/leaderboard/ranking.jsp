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
</head>
<body>
<h3>Ranking:</h3>
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
