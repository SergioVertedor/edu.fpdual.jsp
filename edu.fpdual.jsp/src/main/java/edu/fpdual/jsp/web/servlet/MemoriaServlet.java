package edu.fpdual.jsp.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/memorias")
public class MemoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userAnswer = request.getParameter("answer");
        int remainingSpace = 32 * 1024 - (5 * 500);
        String message;

        if (userAnswer.equals(String.valueOf(remainingSpace))) {
            message = "¡Respuesta correcta! Al pendrive le queda " + remainingSpace + "MB de memoria.";
        } else {
            message = "Respuesta incorrecta. Inténtalo de nuevo.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/comun/memoria.jsp").forward(request, response);
    }
}
