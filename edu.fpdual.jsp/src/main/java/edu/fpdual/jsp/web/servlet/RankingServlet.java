package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.client.UsuarioClient;
import edu.fpdual.jsp.client.dto.UsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(
    name = "RankingServlet",
    urlPatterns = {"/ranking"})
public class RankingServlet extends HttpServlet {

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
    if (usuario == null) {
      homePage(resp, usuario);
    } else {
      List<UsuarioDto> users = client.getUsuarios();
      Collections.sort(users, Collections.reverseOrder());
      req.setAttribute("lista", users);
      req.getRequestDispatcher("/leaderboard/ranking.jsp").forward(req, resp);
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
