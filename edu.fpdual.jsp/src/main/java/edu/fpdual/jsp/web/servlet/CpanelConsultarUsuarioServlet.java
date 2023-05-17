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
    name = "ConsultarUsuarioServlet",
    urlPatterns = {"/cpanel-consultar-usuario-servlet"})
public class CpanelConsultarUsuarioServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  /***
   * Método que consulta la existencia del usuario a través de la id introducida, y acto seguido
   * remite los parámetros del usuario a cpanel.jsp.
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
      int idUsuario = Integer.parseInt(req.getParameter("id"));
      try {
        if (userSrv.buscarPorId(idUsuario) == null) {
          req.setAttribute("notificacionConsulta", "El usuario introducido no existe.");
          req.getRequestDispatcher("/controlpanel/modificar.jsp").forward(req, resp);
        } else {
          UsuarioDao usuarioConsulta = userSrv.buscarPorId(idUsuario);
          req.setAttribute("id", usuarioConsulta.getId());
          req.setAttribute("nombreUsuario", usuarioConsulta.getNombre());
          req.setAttribute("correo", usuarioConsulta.getCorreo());
          req.setAttribute("password", usuarioConsulta.getPassword());
          req.getRequestDispatcher("/controlpanel/modificar.jsp").forward(req, resp);
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
