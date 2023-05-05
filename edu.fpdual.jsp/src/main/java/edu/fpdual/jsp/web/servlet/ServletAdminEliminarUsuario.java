package edu.fpdual.jsp.web.servlet;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import edu.fpdual.jsp.service.UsuarioService;
import edu.fpdual.jsp.web.dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

public class ServletAdminEliminarUsuario {
  @WebServlet(
      name = "ServletRegistro",
      urlPatterns = {"/servlet-admin-eliminar-usuario"})
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
        int idUsuario = Integer.parseInt(req.getParameter("id_usuario"));
        userSrv.eliminarUsuario(idUsuario);
        homePage(resp, usuario);
      }
    }
  }

  private void errorPage(HttpServletResponse resp, Usuario usuario) throws IOException {
    PrintWriter writer = resp.getWriter();
    writer.println("<!DOCTYPE html>");
    writer.println("<html lang='es'>");
    writer.println("<head>");
    writer.println("<meta charset='UTF-8'/>");
    writer.println("<title>Registro de Usuarios</title>");
    writer.println("</head>");
    writer.println("<body>");
    writer.println("<h1>Registro de Usuarios</h1>");
    writer.println("<form action='/servlet-registro' method='POST'>");
    writer.println("<label for='nombre'>Usuario:</label>");
    writer.println("<input type='text' id='nombre' name='nombre' required/><br/><br/>");
    writer.println("<label for='correo'>Email:</label>");
    writer.println("<input type='email' id='correo' name='correo' required/><br/><br/>");
    writer.println("<label for='password'>Contraseña:</label>");
    writer.println("<input type='password' id='password' name='password' required/><br/><br/>");
    writer.println("<label for='confirm_password'>Confirmar Contraseña:</label>");
    writer.println(
        "<input type='password' id='confirm_password' name='confirm_password' required/><br/><br/>");
    writer.println("<input type='submit' value='Registrarse'/>");
    writer.println("</form>");
    writer.println("</body>");
    writer.println("</html>");
  }

  private void homePage(HttpServletResponse resp, Usuario usuario) throws IOException {
    resp.sendRedirect("/");
  }
}
