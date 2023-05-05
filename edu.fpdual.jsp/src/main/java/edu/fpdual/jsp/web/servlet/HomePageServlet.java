package edu.fpdual.jsp.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ahorcado")
public class HomePageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reiniciarJuego(request);
        request.getRequestDispatcher("/comun/homePage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String palabra = (String) request.getSession().getAttribute("palabra");
        char[] letrasAdivinadas = (char[]) request.getSession().getAttribute("letrasAdivinadas");
        int intentos = (int) request.getSession().getAttribute("intentos");

        String letra = request.getParameter("letra");
        if (letra != null && !letra.isEmpty()) {
            boolean acierto = false;
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra.charAt(0)) {
                    letrasAdivinadas[i] = letra.charAt(0);
                    acierto = true;
                }
            }

            if (!acierto) {
                intentos--;
            }

            if (Arrays.equals(letrasAdivinadas, palabra.toCharArray())) {
                request.getSession().setAttribute("resultado", "ganaste");
                request.getSession().setAttribute("palabraAdivinada", palabra);

            } else if (intentos == 0) {
                request.getSession().setAttribute("resultado", "perdiste");
                request.getSession().setAttribute("palabraAdivinada", palabra);

            }

            request.getSession().setAttribute("letrasAdivinadas", letrasAdivinadas);
            request.getSession().setAttribute("intentos", intentos);
            request.getRequestDispatcher("/comun/homePage.jsp").forward(request, response);
        } else if ("reiniciar".equals(request.getParameter("accion"))) {
            reiniciarJuego(request);
            request.getRequestDispatcher("/comun/homePage.jsp").forward(request, response);
        }
    }

    private void reiniciarJuego(HttpServletRequest request) {
        String palabra = (String) request.getSession().getAttribute("palabra");
        if (palabra == null) {
            String[] palabras = {"rosa", "banana", "perro", "gato", "elefante", "mariposa"};
            Random rand = new Random();
            palabra = palabras[rand.nextInt(palabras.length)];
        }
        char[] letrasAdivinadas = new char[palabra.length()];
        Arrays.fill(letrasAdivinadas, '-');
        request.getSession().setAttribute("palabra", palabra);
        request.getSession().setAttribute("letrasAdivinadas", letrasAdivinadas);
        request.getSession().setAttribute("intentos", 10);
        request.getSession().removeAttribute("resultado");
        request.getSession().removeAttribute("palabraAdivinada");
        request.getSession().removeAttribute("mostrarBotones");
    }
}