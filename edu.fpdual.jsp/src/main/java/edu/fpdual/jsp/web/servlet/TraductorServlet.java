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

    private static final String PALABRAS_ATTRIBUTE = "palabras";
    private static final String PUNTUACION_ATTRIBUTE = "puntuacion";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        List<String[]> palabras = (List<String[]>) session.getAttribute(PALABRAS_ATTRIBUTE);
        if (palabras == null || palabras.isEmpty() || request.getParameter("reiniciar") != null) {
            palabras = inicializarPalabras();
            session.setAttribute(PALABRAS_ATTRIBUTE, palabras);
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
        String mensaje = "";

        if (respuesta != null && !respuesta.isEmpty()) {
            if (esValida(respuesta)) {
                respuesta = respuesta.toLowerCase();
                if (respuesta.equals(traduccion)) {
                    respuestaValida = true;
                    mensaje = "¡Respuesta correcta!";
                    incrementarPuntuacion(session);
                } else {
                    mensaje = "Respuesta incorrecta. La traducción correcta es: " + traduccion;
                }
            } else {
                mensaje = "¡Error! La respuesta solo puede contener letras.";
            }
        }

        request.setAttribute("palabra", palabra);
        request.setAttribute("respuestaValida", respuestaValida);
        request.setAttribute("puntuacion", obtenerPuntuacion(session));
        request.setAttribute("mensaje", mensaje);
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
        palabras.add(new String[]{"Depurar", "debug"});
        Collections.shuffle(palabras);
        return palabras;
    }

    private boolean esValida(String respuesta) {
        return respuesta.matches("[a-zA-Z]+");
    }

    private void incrementarPuntuacion(HttpSession session) {
        Integer puntuacion = (Integer) session.getAttribute(PUNTUACION_ATTRIBUTE);
        if (puntuacion == null) {
            puntuacion = 0;
        }
        puntuacion += 50; // Sumar 50 puntos
        session.setAttribute(PUNTUACION_ATTRIBUTE, puntuacion);
    }

    private int obtenerPuntuacion(HttpSession session) {
        Integer puntuacion = (Integer) session.getAttribute(PUNTUACION_ATTRIBUTE);
        if (puntuacion == null) {
            puntuacion = 0;
        }
        return puntuacion;
    }
}