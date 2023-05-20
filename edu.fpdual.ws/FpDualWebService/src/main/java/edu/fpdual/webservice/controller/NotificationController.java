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

  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response checkPassword(UsuarioDao user)
      throws SQLException, ClassNotFoundException {
    boolean esCorrecto = false;
    if (new UsuarioService(new MySQLConnector(), new UsuarioManager())
        .buscarUsuarioConPassword(user.getNombre(), user.getPassword())) {
      esCorrecto = true;
    }
    return Response.ok().entity(esCorrecto).build();
  }

  @POST
  @Path("/registro")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response registroUsuario(UsuarioDao user) throws SQLException, ClassNotFoundException {
    new UsuarioService(new MySQLConnector(), new UsuarioManager()).insertarUsuario(user);
    return Response.ok().entity(0).build();
  }

  @GET
  @Path("/eliminar/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response borrarUsuario(@PathParam("id") int id)
      throws SQLException, ClassNotFoundException {
    int resultado =
        new UsuarioService(new MySQLConnector(), new UsuarioManager()).eliminarUsuario(id);
    return Response.ok().entity(resultado).build();
  }

  @POST
  @Path("/modificar")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response modificaUsuario(UsuarioDao user) throws SQLException, ClassNotFoundException {
    int resultado = new UsuarioService(new MySQLConnector(), new UsuarioManager()).modificarUsuario(user);
    return Response.ok().entity(resultado).build();
  }
}
