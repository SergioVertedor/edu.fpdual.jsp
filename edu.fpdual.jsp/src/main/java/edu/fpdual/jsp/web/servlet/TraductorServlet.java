package edu.fpdual.jsp.web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebServlet("/traductor-servlet")
public class TraductorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        List<String[]> palabras = (List<String[]>) session.getAttribute("palabras");
        if (palabras == null || palabras.isEmpty() || request.getParameter("reiniciar") != null) {
            palabras = inicializarPalabras();
            session.setAttribute("palabras", palabras);
        }

        String palabra = "";
        String traduccion = "";

        if (!palabras.isEmpty()) {
            String[] palabraAleatoria = palabras.get(0);
            palabra = palabraAleatoria[0];
            traduccion = palabraAleatoria[1];
        }

        String respuesta = request.getParameter("respuesta");
        boolean respuestaValida = false;

        if (respuesta != null && !respuesta.isEmpty()) {
            if (esValida(respuesta)) {
                respuesta = respuesta.toLowerCase();
                if (respuesta.equals(traduccion)) {
                    respuestaValida = true;
                    request.setAttribute("mensaje", "¡Respuesta correcta!");
                } else {
                    request.setAttribute("mensaje", "Respuesta incorrecta. La traducción correcta es: " + traduccion);
                }
            } else {
                request.setAttribute("mensaje", "¡Error! La respuesta solo puede contener letras.");
            }
        }

        request.setAttribute("palabra", palabra);
        request.getRequestDispatcher("/comun/traductor.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private List<String[]> inicializarPalabras() {
        List<String[]> palabras = new ArrayList<>();
        palabras.add(new String[]{"Servidor", "server"});
        palabras.add(new String[]{"Impresora", "printer"});
        palabras.add(new String[]{"Código", "code"});
        palabras.add(new String[]{"Proyecto", "project"});
        palabras.add(new String[]{"Consola", "console"});
        palabras.add(new String[]{"Clase", "class"});
        palabras.add(new String[]{"Archivo", "file"});
        Collections.shuffle(palabras);
        return palabras;
    }

    private boolean esValida(String respuesta) {
        return respuesta.matches("[a-zA-Z]+");
    }
}