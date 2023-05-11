package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import edu.fpdual.jsp.service.UsuarioService;
import edu.fpdual.jsp.web.dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet(
    name = "EliminarUsuarioServlet",
    urlPatterns = {"/cpanel-eliminar-usuario-servlet"})
public class CpanelEliminarUsuarioServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  /***
   * Método que elimina el usuario a través de la id introducida por el usuario.
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
      int idUsuario = Integer.parseInt(req.getParameter("id_usuario"));
      try {
        if (userSrv.buscarPorId(idUsuario) == null) {
          req.setAttribute("notificacion", "El usuario introducido no existe.");
          req.getRequestDispatcher("/controlpanel/cpanel.jsp").forward(req, resp);
        } else {
          if (userSrv.eliminarUsuario(idUsuario) == 1) {
            req.setAttribute("notificacion", "Usuario eliminado correctamente.");
            req.getRequestDispatcher("/controlpanel/cpanel.jsp").forward(req, resp);
          }
        }
      } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
      homePage(resp, usuario);
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
