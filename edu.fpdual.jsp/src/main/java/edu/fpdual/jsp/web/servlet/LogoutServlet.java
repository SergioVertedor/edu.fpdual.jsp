package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.client.dto.UsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet {
    @WebServlet(
            name = "LogoutServlet",
            urlPatterns = {"/logout"})
    public class ServletVolverLogin extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            UsuarioDto usuario = (UsuarioDto) req.getSession().getAttribute("usuarioSesion");
            if (usuario != null) {
                usuario = null;
                resp.sendRedirect("/");
            } else {
                resp.sendRedirect("/");
            }
        }
    }
}
