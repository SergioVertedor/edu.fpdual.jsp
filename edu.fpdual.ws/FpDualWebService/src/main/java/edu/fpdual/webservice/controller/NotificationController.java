package edu.fpdual.webservice.controller;

import edu.fpdual.webservice.api.dto.Notification;
import edu.fpdual.webservice.model.application.connector.MySQLConnector;
import edu.fpdual.webservice.model.application.dao.UsuarioDao;
import edu.fpdual.webservice.model.application.manager.UsuarioManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import edu.fpdual.webservice.service.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/notifications")
public class NotificationController {
  @GET
  @Path("/usuarios/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsuarios() throws SQLException, ClassNotFoundException {

    List<UsuarioDao> usuarios =
        new UsuarioService(new MySQLConnector(), new UsuarioManager()).buscarATodos();
    return Response.ok().entity(usuarios).build();
  }

  @GET
  @Path("/usuarios/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsuario(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
    UsuarioDao usuario =
        new UsuarioService(new MySQLConnector(), new UsuarioManager()).buscarPorId(id);
    return Response.ok().entity(usuario).build();
  }

  @GET
  @Path("/usuarios/check/{nombre}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response getUsuarioPorNombre(@PathParam("nombre") String nombre)
      throws SQLException, ClassNotFoundException {
    boolean resultado = false;
    if (new UsuarioService(new MySQLConnector(), new UsuarioManager())
        .buscarPorNombreExacto(nombre)) {
      resultado = true;
    } else {
      resultado = false;
    }
    return Response.ok().entity(resultado).build();
  }

  @GET
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response checkPassword(@QueryParam("nombre") String nombre, @QueryParam("password") String password)
      throws SQLException, ClassNotFoundException {

    boolean esCorrecto = false;
    if (new UsuarioService(new MySQLConnector(), new UsuarioManager())
        .buscarUsuarioConPassword(nombre, password)) {
      esCorrecto = true;
    }
    return Response.ok().entity(esCorrecto).build();
  }

  /*@GET
  @Path("/ping")
  public Response ping() {
      return Response.ok().entity("Service online").build();
  }

  @GET
  @Path("/get/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotifications() {
      List<Notification> notifications = new ArrayList<>();
      notifications.add(new Notification(5, "john", "test notification"));
      return Response.ok().entity(notifications).build();
  }

  @GET
  @Path("/get/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") int id) {
      return Response.ok().entity(new Notification(id, "john", "test notification")).build();
  }

  @PUT
  @Path("/get/{id}/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") int id, @PathParam("name") String name) {
      return Response.ok().entity(new Notification(id, name, "test notification")).build();
  }

  @GET
  @Path("/get/{id}/name")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotificationWithParameters(@PathParam("id") int id, @QueryParam("name") String name) {
      if (name == null || name.trim().isEmpty()) {
          return Response.status(400).entity("Name not present in the request").build();
      } else {
          return Response.ok().entity(new Notification(id, name, "test notification")).build();
      }
  }

  @GET
  @Path("/getXML/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public Response getNotificationXML(@PathParam("id") int id) {
      return Response.ok().entity(new Notification(id, "john", "test notification")).build();
  }

  @POST
  @Path("/post/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response postNotification(Notification notification) {
      return Response.status(201).entity(notification).build();
  }*/
}
