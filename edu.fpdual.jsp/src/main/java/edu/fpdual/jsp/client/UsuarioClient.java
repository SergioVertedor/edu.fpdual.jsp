package edu.fpdual.jsp.client;

import edu.fpdual.jsp.client.dto.UsuarioDto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

public class UsuarioClient {

  private final WebTarget webTarget;

  public UsuarioClient() {
    Client client = ClientBuilder.newClient();
    this.webTarget = client.target("http://localhost:8081/webapi/");
  }

  /**
   * Solicita al servicio una lista de todos los usuarios.
   *
   * @return Documento en formato JSON con todos los usuarios.
   */
  public List<UsuarioDto> getUsuarios() {
    GenericType<List<UsuarioDto>> usuarios = new GenericType<List<UsuarioDto>>() {};
    return webTarget.path("user/get").request(MediaType.APPLICATION_JSON).get(usuarios);
  }

  /**
   * Solicita al servicio que verifique la existencia del nombre de usuario en la BBDD.
   *
   * @param nombre
   * @return boolean que confirma o desmiente la existencia del usuario.
   */
  public boolean checkUsuarioPorNombre(String nombre) {
    String respuesta =
        webTarget.path("user/check/" + nombre).request(MediaType.TEXT_PLAIN).get(String.class);
    boolean resultado = Boolean.parseBoolean(respuesta);
    return resultado;
  }

  /**
   * Solicita al servicio un usuario proporcionando su id correspondiente.
   *
   * @param id
   * @return Documento en formato JSON conteniendo el usuario solicitado.
   */
  public UsuarioDto getUsuario(int id) {

    return webTarget
        .path("user/get/" + id)
        .request(MediaType.APPLICATION_JSON)
        .get(UsuarioDto.class);
  }

  /**
   * Solicita un usuario a partir del nombre con objetivo de extraer la id.
   *
   * @param nombre
   * @return Documento en formato JSON conteniendo el usuario solicitado.
   */
  public UsuarioDto getUsuarioPorNombre(String nombre) {

    return webTarget
        .path("user/getid/" + nombre)
        .request(MediaType.APPLICATION_JSON)
        .get(UsuarioDto.class);
  }

  /**
   * Solicita al servicio que añada los puntos indicados del usuario facilitado en BBDD.
   *
   * @param puntosObtenidos
   * @param nombre
   * @return int con el número de líneas modificadas (se espera 1).
   */
  public int updatePuntos(int puntosObtenidos, String nombre) {
    String respuesta =
        webTarget
            .path("user/add/" + nombre + "/" + puntosObtenidos)
            .request(MediaType.TEXT_PLAIN)
            .get(String.class);
    return Integer.parseInt(respuesta);
  }

  /**
   * Solicita a servicio la verificación de la contraseña, pasándole un UsuarioDto.
   *
   * @param user
   * @return boolean que será True si la verificación ha sido satisfactoria.
   */
  public boolean checkPassword(UsuarioDto user) {

    String respuesta =
        webTarget
            .path("user/login")
            .request(MediaType.TEXT_PLAIN)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON), String.class);
    return Boolean.parseBoolean(respuesta);
  }

  /**
   * Solicita a servicio el registro en BBDD del objeto UsuarioDto facilitado.
   *
   * @param user
   * @return int con el número de líneas modificadas (se espera 1).
   */
  public int registroUsuario(UsuarioDto user) {
    String respuesta =
        webTarget
            .path("user/registro")
            .request(MediaType.TEXT_PLAIN)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON), String.class);
    return Integer.parseInt(respuesta);
  }

  /**
   * Solicita a servicio la eliminación de una entrada en BBDD correspondiente a la id
   * proporcionada.
   *
   * @param id
   * @return int con el número de líneas modificadas (se espera 1).
   */
  public int borrarUsuario(int id) {

    String respuesta =
        webTarget.path("user/eliminar/" + id).request(MediaType.TEXT_PLAIN).get(String.class);
    return Integer.parseInt(respuesta);
  }

  /**
   * Se solicita a servicio la modificación de los valores de un usuario aportando un UsuarioDto.
   *
   * @param user
   * @return int con el número de líneas modificadas (se espera 1).
   */
  public int modificaUsuario(UsuarioDto user) {

    String respuesta =
        webTarget
            .path("user/modificar")
            .request(MediaType.TEXT_PLAIN)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON), String.class);
    return Integer.parseInt(respuesta);
  }
}
