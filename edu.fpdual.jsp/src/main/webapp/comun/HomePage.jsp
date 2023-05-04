<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ahorcado</title>
     <style>
            body {
                background-color: turquoise;
                font-family: Arial, sans-serif;
                          text-align: center;
                      }


            h1 {
                color: #9900CC;
                margin-top: 50px;
            }
            p {
                font-size: 18px;
                margin-top: 20px;
            }
            p1 {
                     font-size: 28px;
                  margin-top: 20px;
                        }
            label {
                font-weight: bold;
                font-size: 16px;
            }
            input[type=text], input[type=submit] {
                padding: 8px;
                font-size: 16px;
                border-radius: 5px;
                border: none;
            }
            input[type=submit] {
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }
            input[type=submit]:hover {
                background-color: #FF66FF;
            }
        </style>
</head>
<body>
<%
    boolean juegoTerminado = false;
    boolean victoria = false;
    String mensaje = "";
    char[] letrasAdivinadas = (char[]) request.getSession().getAttribute("letrasAdivinadas");

if (letrasAdivinadas == null || request.getParameter("reiniciar") != null) { // si no se ha inicializado el juego o se presionó el botón "Reiniciar", se genera una palabra aleatoria
    String[] palabras = { "rosa", "banana", "perro", "gato", "elefante", "mariposa" };
    Random rand = new Random();
    String palabra = palabras[rand.nextInt(palabras.length)];
    letrasAdivinadas = new char[palabra.length()];
    Arrays.fill(letrasAdivinadas, '-');
    request.getSession().setAttribute("palabra", palabra);
    request.getSession().setAttribute("letrasAdivinadas", letrasAdivinadas);
    request.getSession().setAttribute("intentos", 10);
} else {
    String letra = request.getParameter("letra");
    if (letra != null && letra.length() == 1) {
        letra = letra.toLowerCase(); // convertir la letra ingresada en minúscula
        String palabra = (String) request.getSession().getAttribute("palabra");
        int intentos = (int) request.getSession().getAttribute("intentos");

     if (!palabra.contains(letra)) {
         intentos--;
     } else {
         boolean letraRepetida = false;
         for (int i = 0; i < letrasAdivinadas.length; i++) {
             if (letrasAdivinadas[i] == letra.charAt(0)) {
                 letraRepetida = true;
                 break;
             }
         }
         if (!letraRepetida) {
             for (int i = 0; i < palabra.length(); i++) {
                 if (palabra.charAt(i) == letra.charAt(0)) {
                     letrasAdivinadas[i] = letra.charAt(0);
                 }
             }
         }
     }

        request.getSession().setAttribute("letrasAdivinadas", letrasAdivinadas);

        if (!new String(letrasAdivinadas).contains("-")) {
            juegoTerminado = true;
            victoria = true;
        } else if (intentos == 0) {
            juegoTerminado = true;
        }

        request.getSession().setAttribute("intentos", intentos);
    }
}

if (juegoTerminado) {
    String palabra = (String) request.getSession().getAttribute("palabra");
    mensaje = victoria ? "¡Ganaste! La palabra era " + palabra + "." : "Perdiste. La palabra era " + palabra + ".";



%>
<p><%= mensaje %></p>
<form method="post" action="ahorcado">
<p>
<input type="submit" name="reiniciar" value="Reiniciar">
</p>
</form>
<%
} else {
%>
<h1>Adivina la palabra</h1>
<p1>Palabra a adivinar: <%= new String(letrasAdivinadas) %></p1>
<p>Te quedan <%= request.getSession().getAttribute("intentos") %> intentos.</p>
<form method="post" action="ahorcado">
<p>
<label for="letra">Ingresa una letra: </label>
<input type="text" name="letra" maxlength="1">
                <input type="submit" value="Enviar">
            </p>
 <% if (session.getAttribute("mostrarBotones") != null && (boolean) session.getAttribute("mostrarBotones")) { %>
            <form action="${pageContext.request.contextPath}/ahorcado" method="GET">
                <input type="submit" name="reiniciar" value="Reiniciar juego">

        </form>
            </form>
        <% } %>

<%
    }
%>
<img src="./src/main/webapp/images/adivina-2.png"></img>
</body>
</html>