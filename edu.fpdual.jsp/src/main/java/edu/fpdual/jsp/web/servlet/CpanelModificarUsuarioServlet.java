package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.dao.UsuarioDao;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import edu.fpdual.jsp.service.UsuarioService;
import edu.fpdual.jsp.web.dto.UsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
    name = "ModificarUsuarioServlet",
    urlPatterns = {"/cpanel-modificar-usuario-servlet"})
public class CpanelModificarUsuarioServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  /***
   * Método que modifica el usuario a través del formulario realizado por el usuario.
   * @param req Parametros recibidos desde cpanel.jsp
   * @param resp Parametros a disposición para cpanel.jsp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UsuarioService userSrv = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    UsuarioDto usuario = (UsuarioDto) req.getSession().getAttribute("usuarioSesion");
    if (!usuario.getUsuario().equalsIgnoreCase("admin")) {
      homePage(resp, usuario);
    } else {
      String identificador = req.getParameter("identificador");
      String usuarioIntroducido = req.getParameter("nombreUsuario");
      String correoIntroducido = req.getParameter("correo");
      String passwordIntroducido = req.getParameter("password");

      try {
        if (passwordIntroducido.length() > 8 || passwordIntroducido.length() < 6) {
          req.setAttribute(
              "notificacionUpdate", "La contraseña debe contener entre 6 y 8 carácteres.");
          req.setAttribute("id", identificador);
          req.setAttribute("nombreUsuario", usuarioIntroducido);
          req.setAttribute("correo", correoIntroducido);
          req.setAttribute("password", passwordIntroducido);
          req.getRequestDispatcher("/controlpanel/modificar.jsp").forward(req, resp);
        } else {
          userSrv.modificarUsuario(
              new UsuarioDao(
                  identificador, usuarioIntroducido, correoIntroducido, passwordIntroducido));
          req.getRequestDispatcher("/controlpanel/cpanel.jsp").forward(req, resp);
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
  private void homePage(HttpServletResponse resp, UsuarioDto usuario) throws IOException {
    resp.sendRedirect("/");
  }
}
