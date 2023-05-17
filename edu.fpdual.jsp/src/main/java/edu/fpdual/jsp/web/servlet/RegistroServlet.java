package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.dao.UsuarioDao;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import edu.fpdual.jsp.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
    name = "RegistroServlet",
    urlPatterns = {"/registro-servlet"})
public class RegistroServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  /***
   * Método para recogida de parámetros, verificación y registro posterior en base de datos.
   * @param req Parametros recibidos desde registro.jsp
   * @param resp Parametros a disposición para registro.jsp
   * @throws ServletException
   * @throws IOException
   */

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UsuarioService userSrv = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    UsuarioDao usuario = (UsuarioDao) req.getSession().getAttribute("usuarioSesion");
    if (usuario != null) {
      homePage(resp, usuario);
    } else {
      String usuarioIntroducido = req.getParameter("nombre");
      String correoIntroducido = req.getParameter("correo");
      String passwordIntroducido = req.getParameter("password");
      String passwordIntroducidoCheck = req.getParameter("confirm_password");
      req.setAttribute("error", "");
      try {
        if (userSrv.buscarPorNombreExacto(usuarioIntroducido)) {
          req.setAttribute("error", "El usuario ya existe.");
          req.getRequestDispatcher("/registro/registro.jsp").forward(req, resp);
        } else if (passwordIntroducido.length() > 8 || passwordIntroducido.length() < 6) {
          req.setAttribute(
              "error", "La contraseña debe contener entre 6 y 8 carácteres.");
          req.getRequestDispatcher("/registro/registro.jsp").forward(req, resp);
        } else if (!passwordIntroducido.equals(passwordIntroducidoCheck)) {
          req.setAttribute("error", "Las contraseñas no coinciden.");
          req.getRequestDispatcher("/registro/registro.jsp").forward(req, resp);

        } else {
          userSrv.insertarUsuario(
              new UsuarioDao(
                  usuarioIntroducido, correoIntroducido, passwordIntroducido));
          homePage(resp, usuario);
        }
      } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /***
   * Método para redireccionar a la página principal.
   * @param resp Parámetros a disposición para la homepage.
   * @param usuario Incluye la sesión del usuario.
   * @throws IOException
   */
  private void homePage(HttpServletResponse resp, UsuarioDao usuario) throws IOException {
    resp.sendRedirect("/");
  }
}
