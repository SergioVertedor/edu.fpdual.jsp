package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.web.dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "ServletLogin",
    urlPatterns = {"/servlet-login"})
public class ServletLogin extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioSesion");
    if (usuario != null) {
      homePage(resp, usuario);
    } else {
      String usuarioConfigurado = getServletContext().getInitParameter("usuario");
      String passwordConfigurado = getServletContext().getInitParameter("password");
      String usuarioIntroducido = req.getParameter("usuario");
      String passwordIntroducido = req.getParameter("contrasena");
      if ((usuarioIntroducido != null && usuarioIntroducido.equals(usuarioConfigurado))
          && (passwordIntroducido != null && passwordIntroducido.equals(passwordConfigurado))) {
        usuario =
            Usuario.builder().nombre(usuarioIntroducido).password(passwordIntroducido).build();

        req.getSession().setAttribute("usuarioSesion", usuario);
        homePage(resp, usuario);
      } else {
        resp.sendRedirect("/login/login.jsp");
      }
    }
  }

  private void homePage(HttpServletResponse resp, Usuario usuario) throws IOException {
    resp.sendRedirect("/ahorcado");
  }
}
