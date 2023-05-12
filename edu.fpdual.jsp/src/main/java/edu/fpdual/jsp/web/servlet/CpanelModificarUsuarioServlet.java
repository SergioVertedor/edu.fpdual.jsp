package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.dao.Usuario;
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
    Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioSesion");
    if (usuario != null) {
      homePage(resp, usuario);
    } else {
      String identificador = req.getParameter("identificador");
      System.out.println(identificador);
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
          req.getRequestDispatcher("/controlpanel/cpanel.jsp").forward(req, resp);
        } else {
          userSrv.modificarUsuario(
              new Usuario(
                  identificador, usuarioIntroducido, correoIntroducido, passwordIntroducido));
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
  private void homePage(HttpServletResponse resp, Usuario usuario) throws IOException {
    resp.sendRedirect("/");
  }
}
