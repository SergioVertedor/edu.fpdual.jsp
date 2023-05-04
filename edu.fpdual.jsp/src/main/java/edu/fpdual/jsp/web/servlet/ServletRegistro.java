package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import edu.fpdual.jsp.service.UsuarioService;
import edu.fpdual.jsp.web.dto.Usuario;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "ServletRegistro",
    urlPatterns = {"/servlet-registro"})
public class ServletRegistro extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @SneakyThrows
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UsuarioService userSrv = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioSesion");
    if (usuario != null) {
      homePage(resp, usuario);
    } else {
      String usuarioIntroducido = req.getParameter("nombre");
      String correoIntroducido = req.getParameter("correo");
      String passwordIntroducido = req.getParameter("password");
      System.out.println(usuarioIntroducido);
      if (userSrv.searchForExactName(usuarioIntroducido)) {
        System.out.println(userSrv.searchForExactName(usuarioIntroducido));
        PrintWriter writer = resp.getWriter();
        writer.println("<p>El usuario ya existe.</p>");
      } else if (passwordIntroducido.length() != 8) {
        PrintWriter writer = resp.getWriter();
        writer.println("<p>La contraseña debe contener 8 carácteres.</p>");
      } else {
        userSrv.insertUsuario(
            new edu.fpdual.jsp.persistence.dao.Usuario(
                usuarioIntroducido, correoIntroducido, passwordIntroducido));
      }
    }
  }
  private void homePage(HttpServletResponse resp, Usuario usuario) throws IOException {
    resp.sendRedirect("/jsp/");
  }
}
