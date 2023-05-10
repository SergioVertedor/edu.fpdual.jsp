package edu.fpdual.jsp.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    name = "MiServlet",
    urlPatterns = {"/mi-servlet"})
public class MiServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    writer.println("<html>");
    writer.println("<body>");
    writer.println("<h2>Mi Servlet! =D</h2>");
    writer.println("</body>");
    writer.println("</html>");
  }
}
