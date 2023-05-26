package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.client.UsuarioClient;
import edu.fpdual.jsp.client.dto.UsuarioDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "ServletLogin",
    urlPatterns = {"/servlet-login"})
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UsuarioClient client = new UsuarioClient();
    UsuarioDto usuario = (UsuarioDto) req.getSession().getAttribute("usuarioSesion");
    boolean esCorrecto = false;
    if (usuario != null) {
      req.getSession().setAttribute("usuarioSesion", usuario);
      req.getRequestDispatcher("/index.jsp").forward(req, resp);
    } else {
      String usuarioIntroducido = req.getParameter("usuario");
      String passwordIntroducido = req.getParameter("contrasena");
      esCorrecto =
          client.checkPassword(new UsuarioDto(null, usuarioIntroducido, null, passwordIntroducido, 0));
      if (!client.checkUsuarioPorNombre(usuarioIntroducido)) {
        req.setAttribute("error", "El usuario no existe.");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
      } else if (!esCorrecto) {
        req.setAttribute("error", "Usuario y contraseña no coinciden.");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
      } else {
        if (usuarioIntroducido != null && passwordIntroducido != null) {
          usuario =
              UsuarioDto.builder()
                  .nombre(usuarioIntroducido)
                  .password(passwordIntroducido)
                  .build();
          req.getSession().setMaxInactiveInterval(1800);
          req.getSession().setAttribute("usuarioSesion", usuario);
          req.getRequestDispatcher("/ahorcado").forward(req, resp);
        } else {
          resp.sendRedirect("/index.jsp");
        }
      }
    }
  }
}
