package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.client.dto.UsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
    name = "LogoutServlet",
    urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

  /***
   * Método encargado de pasar la sesión a null y redireccionar a la raíz.
   * @param req Parámetros recibidos desde petición.
   * @param resp Parámetros a disposición de /
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      req.setAttribute("usuarioSesion", null);
      req.getRequestDispatcher("/").forward(req, resp);
      resp.sendRedirect("/");

  }
}
