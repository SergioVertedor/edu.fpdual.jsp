package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import edu.fpdual.jsp.service.UsuarioService;
import edu.fpdual.jsp.web.dto.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
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
    UsuarioService userSrv = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioSesion");
    Map<String, String> mapa;
    if (usuario != null) {
      homePage(resp, usuario);
    } else {
      String usuarioIntroducido = req.getParameter("usuario");
      String passwordIntroducido = req.getParameter("contrasena");
      try {
        mapa = userSrv.buscarUsuarioConPassword(usuarioIntroducido);
      } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
      try {
        if (!userSrv.buscarPorNombreExacto(usuarioIntroducido)) {
          req.setAttribute("error", "El usuario no existe.");
          req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
          if (usuarioIntroducido != null
              && passwordIntroducido != null
              && mapa.get(usuarioIntroducido).equals(passwordIntroducido)) {
            usuario =
                Usuario.builder().nombre(usuarioIntroducido).password(passwordIntroducido).build();
            req.getSession().setAttribute("usuarioSesion", usuario);
            homePage(resp, usuario);
          } else {
            resp.sendRedirect("/index.jsp");
          }
        }
      } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void homePage(HttpServletResponse resp, Usuario usuario) throws IOException {
    resp.sendRedirect("/ahorcado");
  }
}
