package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.web.dto.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "ServletVolverLogin",
    urlPatterns = {"/servlet-volver-login"})
public class ServletVolverLogin extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioSesion");
    if (usuario != null) {
      resp.sendRedirect("/EjemploAplicativoWeb/servlet-login");
    } else {
      resp.sendRedirect("/EjemploAplicativoWeb/login/login.jsp");
    }
  }
}
