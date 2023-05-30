package edu.fpdual.jsp.web.servlet;

import com.itextpdf.text.DocumentException;
import edu.fpdual.email.sender.Sender;
import edu.fpdual.jsp.client.dto.UsuarioDto;
import edu.fpdual.itextpdf.PdfItext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URISyntaxException;

@WebServlet(
    name = "SendMail",
    urlPatterns = {"/send"})
public class sendMailServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  /***
   * Método que consulta la existencia del usuario a través de la id introducida, y acto seguido
   * remite los parámetros del usuario a cpanel.jsp.
   * @param req Parámetros recibidos desde cpanel.jsp
   * @param resp Parámetros a disposición para cpanel.jsp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UsuarioDto usuario = (UsuarioDto) req.getSession().getAttribute("usuarioSesion");
    String emailTo = "hackealapalabra@gmail.com";
    String nombrePdf = "C:/mensajeRecibido.pdf";
    String nombre = req.getParameter("nombre");
    String emailFrom = req.getParameter("email");
    String opinion = req.getParameter("opinion");
    try {

      new PdfItext().createPDF(nombrePdf, nombre, emailFrom, opinion);
      new Sender()
          .send(
              emailFrom,
              emailTo,
              "Mensaje recibido.",
              "Ha recibido un mensaje desde contact",
              nombrePdf);
      req.setAttribute("notificacion", "Mensaje enviado con éxito. Muchas gracias.");
      req.getRequestDispatcher("/contact/form.jsp").forward(req, resp);
    } catch (DocumentException | URISyntaxException e) {
      resp.sendRedirect("/error.jsp");
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
