package edu.fpdual.webservice.controller;

import edu.fpdual.webservice.model.application.connector.MySQLConnector;
import edu.fpdual.webservice.model.application.dao.UsuarioDao;
import edu.fpdual.webservice.model.application.manager.UsuarioManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import edu.fpdual.webservice.service.*;
import java.sql.SQLException;
import java.util.List;

@Path("/user")
public class UsuarioController {
  /***
   * Recoge una solicitud HTTP GET, la traslada al Service y produce una respuesta en formato JSON.
   * @return Devuelve en formato JSON una lista de UsuarioDao extraídos de BBDD.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @GET
  @Path("/get/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsuarios() throws SQLException, ClassNotFoundException {

    List<UsuarioDao> usuarios =
        new UsuarioService(new MySQLConnector(), new UsuarioManager()).buscarATodos();
    return Response.ok().entity(usuarios).build();
  }

  /***
   * Recoge una solicitud HTTP GET, la traslada al Service y produce una respuesta en formato JSON.
   * @param id entero que se recoge para localizar la id de usuario en BBDD.
   * @return Devuelve en formato JSON un UsuarioDao extraído de BBDD.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @GET
  @Path("/get/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsuario(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
    UsuarioDao usuario =
        new UsuarioService(new MySQLConnector(), new UsuarioManager()).buscarPorId(id);
    return Response.ok().entity(usuario).build();
  }

  /***
   * Recoge una solicitud HTTP GET, la traslada al Service y produce una respuesta en formato JSON.
   * @param nombre String que se recoge para localizar el nombre de usuario en BBDD.
   * @return Devuelve en formato JSON un UsuarioDao extraído de BBDD.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @GET
  @Path("/getid/{nombre}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsuario(@PathParam("nombre") String nombre) throws SQLException, ClassNotFoundException {
    UsuarioDao usuario =
            new UsuarioService(new MySQLConnector(), new UsuarioManager()).buscarIdPorNombre(nombre);
    return Response.ok().entity(usuario).build();
  }

  /***
   * Recoge una solicitud HTTP GET, la traslada al Service y produce una respuesta en texto plano.
   * @param nombre String que se recoge para localizar el nombre de usuario en BBDD.
   * @param puntosObtenidos Entero proporcionado para sumar a los puntos existentes.
   * @return int con el número de filas alteradas (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
    @GET
    @Path("/add/{nombre}/{puntos}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePuntos(@PathParam("nombre") String nombre, @PathParam("puntos") int puntosObtenidos) throws SQLException, ClassNotFoundException {

               int resultado = new UsuarioService(new MySQLConnector(), new UsuarioManager()).updatePuntos(puntosObtenidos, nombre);
        return Response.ok().entity(resultado).build();
    }

  /***
   * Recoge una solicitud HTTP GET, la traslada al Service y produce una respuesta en texto plano.
   * @param nombre String que se recoge para localizar el nombre de usuario en BBDD.
   * @return boolean True si existe el usuario.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @GET
  @Path("/check/{nombre}")
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

  /***
   * Recoge una solicitud HTTP POST que incluye un objeto en formato JSON, la traslada al Service y
   * produce una respuesta en texto plano.
   * @param user Objeto en formato JSON correspondiente a un UsuarioDao.
   * @return boolean true si el nombre y la contraseña del objeto, coinciden con
   * los registros de la BBDD.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
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

  /***
   * Recoge una solicitud HTTP POST que incluye un objeto en formato JSON, la traslada al Service y
   * produce una respuesta en texto plano.
   * @param user Objeto en formato JSON correspondiente a un UsuarioDao.
   * @return int con el número de filas alteradas (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @POST
  @Path("/registro")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response registroUsuario(UsuarioDao user) throws SQLException, ClassNotFoundException {
    int resultado = new UsuarioService(new MySQLConnector(), new UsuarioManager()).insertarUsuario(user);
    return Response.ok().entity(resultado).build();
  }

  /***
   * Recoge una solicitud HTTP GET y la traslada al Service.
   * @param id entero que indica la identificación del registro en BBDD a eliminar
   * @return int con el número de filas alteradas (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @GET
  @Path("/eliminar/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response borrarUsuario(@PathParam("id") int id)
      throws SQLException, ClassNotFoundException {
    int resultado =
        new UsuarioService(new MySQLConnector(), new UsuarioManager()).eliminarUsuario(id);
    return Response.ok().entity(resultado).build();
  }

  /***
   * Recoge una solicitud HTTP POST que incluye un objeto en formato JSON, la traslada al Service y
   * produce una respuesta en texto plano.
   * @param user Objeto UsuarioDao en formato JSON trasladado para actualizar en BBDD.
   * @return int con el número de filas alteradas (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @POST
  @Path("/modificar")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response modificaUsuario(UsuarioDao user) throws SQLException, ClassNotFoundException {
    int resultado = new UsuarioService(new MySQLConnector(), new UsuarioManager()).modificarUsuario(user);
    return Response.ok().entity(resultado).build();
  }
}
