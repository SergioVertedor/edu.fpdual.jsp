package edu.fpdual.jsp.web.servlet;

import edu.example.aplicativoweb.servlet.dto.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
