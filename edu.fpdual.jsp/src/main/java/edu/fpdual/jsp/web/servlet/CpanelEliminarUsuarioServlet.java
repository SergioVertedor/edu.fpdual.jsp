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
    UsuarioClient client = new UsuarioClient();
    UsuarioDto usuario = (UsuarioDto) req.getSession().getAttribute("usuarioSesion");
    if (!usuario.getNombre().equalsIgnoreCase("admin")) {
      homePage(resp, usuario);
    } else {
      int idUsuario = Integer.parseInt(req.getParameter("id"));
      if (client.getUsuario(idUsuario) == null) {
        req.setAttribute("notificacion", "El usuario introducido no existe.");
        req.getRequestDispatcher("/controlpanel/cpanel.jsp").forward(req, resp);
      } else {
        if (client.borrarUsuario(idUsuario) == 1) {
          req.setAttribute("notificacion", "Usuario eliminado correctamente.");
          req.getRequestDispatcher("/controlpanel/cpanel.jsp").forward(req, resp);
        }
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
  private void homePage(HttpServletResponse resp, UsuarioDto usuario) throws IOException {
    resp.sendRedirect("/");
  }
}
