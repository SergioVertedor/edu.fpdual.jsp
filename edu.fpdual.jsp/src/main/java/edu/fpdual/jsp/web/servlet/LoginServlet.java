package edu.fpdual.jsp.web.servlet;

import edu.fpdual.HackeaException;
import edu.fpdual.jsp.client.UsuarioClient;
import edu.fpdual.jsp.client.dto.UsuarioDto;
import java.io.IOException;
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

  /***
   * Método encargado de validar los datos introducidos y ejecutar la comprobación de login por cliente.
   * @param req Parámetros recibidos desde login.jsp
   * @param resp Parámetros a disposición para login.jsp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UsuarioClient client = new UsuarioClient();
    UsuarioDto usuario = (UsuarioDto) req.getSession().getAttribute("usuarioSesion");
    boolean esCorrecto = false;
    String usuarioIntroducido = req.getParameter("usuario");
    String passwordIntroducido = req.getParameter("contrasena");

    //Excepción personalizada
    try {
      esCorrecto =
          client.checkPassword(
              new UsuarioDto(null, usuarioIntroducido, null, passwordIntroducido, 0));
      if (!client.checkUsuarioPorNombre(usuarioIntroducido)) {
        throw new HackeaException("El usuario no existe.");
      }
    } catch (HackeaException e) {
      req.setAttribute("error", "El usuario no existe.");
      req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
    }
    //Excepción personalizada
    try {
      if (!esCorrecto) {
        throw new HackeaException("Usuario y contraseña no coinciden.");
      }
    } catch (HackeaException e) {
      req.setAttribute("error", "Usuario y contraseña no coinciden.");
      req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
    }

    if (usuarioIntroducido != null && passwordIntroducido != null) {
      usuario =
          UsuarioDto.builder().nombre(usuarioIntroducido).password(passwordIntroducido).build();
      req.getSession().setMaxInactiveInterval(1800);
      req.getSession().setAttribute("usuarioSesion", usuario);
      req.getRequestDispatcher("/ahorcado").forward(req, resp);
    } else {
      resp.sendRedirect("/login/login.jsp");
    }
  }
}
