package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.client.UsuarioClient;
import edu.fpdual.jsp.client.dto.UsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    UsuarioClient client = new UsuarioClient();
    UsuarioDto usuario = (UsuarioDto) req.getSession().getAttribute("usuarioSesion");
    if (!usuario.getNombre().equalsIgnoreCase("admin")) {
      homePage(resp, usuario);
    } else {
      String identificador = req.getParameter("identificador");
      String usuarioIntroducido = req.getParameter("nombreUsuario");
      String correoIntroducido = req.getParameter("correo");
      String passwordIntroducido = req.getParameter("password");
      if (passwordIntroducido.length() > 8 || passwordIntroducido.length() < 6) {
        req.setAttribute(
            "notificacion", "La contraseña debe contener entre 6 y 8 carácteres.");
        req.setAttribute("id", identificador);
        req.setAttribute("nombreUsuario", usuarioIntroducido);
        req.setAttribute("correo", correoIntroducido);
        req.setAttribute("password", passwordIntroducido);
        req.getRequestDispatcher("/controlpanel/modificar.jsp").forward(req, resp);
      } else {
        client.modificaUsuario(
            new UsuarioDto(
                identificador, usuarioIntroducido, correoIntroducido, passwordIntroducido, 0));
        req.setAttribute(
                "notificacion", "Usuario modificado.");
        req.getRequestDispatcher("/cpanel-listar-usuarios-servlet").forward(req, resp);

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
