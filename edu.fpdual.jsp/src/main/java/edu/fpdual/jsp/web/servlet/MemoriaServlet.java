package edu.fpdual.jsp.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/memorias")
public class MemoriaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/comun/memoria.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pregunta = request.getParameter("answer");
        int operacion = 32 * 1024 - (5 * 500);
        String mensaje;

        if (pregunta.equals(String.valueOf(operacion))) {
            mensaje = "¡Respuesta correcta! Al pendrive le queda " + operacion + " MB de memoria.";
        } else {
            mensaje = "Respuesta incorrecta. Inténtalo de nuevo.";
        }

        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/comun/memoria.jsp").forward(request, response);
    }
}