package edu.fpdual.jsp.web.servlet;

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
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean juegoReiniciado = Boolean.parseBoolean((String) session.getAttribute("juegoReiniciado"));
        String palabraAleatoria;
        String palabra = (String) session.getAttribute("palabra");
        String traduccion = (String) session.getAttribute("traduccion");

        if (juegoReiniciado) {
            palabraAleatoria = getRandomPalabra();
            traduccion = palabras.get(palabraAleatoria);
            session.setAttribute("juegoReiniciado", "false");
        } else {
            palabraAleatoria = palabra;
        }

        session.setAttribute("palabra", palabraAleatoria);
        session.setAttribute("traduccion", traduccion);
        session.setAttribute("respuestaValida", null);

        request.getRequestDispatcher("/comun/traductor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String respuesta = request.getParameter("respuesta");
        String traduccion = (String) session.getAttribute("traduccion");

        if (respuesta != null && !respuesta.isEmpty()) {
            if (respuesta.equalsIgnoreCase(traduccion)) {
                session.setAttribute("respuestaValida", true);
            } else {
                session.setAttribute("respuestaValida", false);
            }
        }

        if (request.getParameter("action") != null && request.getParameter("action").equals("reset")) {
            session.setAttribute("juegoReiniciado", "true");
        }

        response.sendRedirect(request.getContextPath() + "/traductor-servlet");
    }

    private String getRandomPalabra() {
        List<String> palabrasList = new ArrayList<String>(palabras.keySet());
        Collections.shuffle(palabrasList);
        return palabrasList.get(0);
    }

    private static final java.util.Map<String, String> palabras = new java.util.HashMap<String, String>() {
        private static final long serialVersionUID = 1L;

        {
            put("apple", "manzana");
            put("banana", "plátano");
            put("car", "coche");
            put("house", "casa");
            put("dog", "perro");
        }
    };
}