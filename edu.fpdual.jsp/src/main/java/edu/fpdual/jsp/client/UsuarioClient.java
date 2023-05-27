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

  public List<UsuarioDto> getUsuarios() {
    GenericType<List<UsuarioDto>> usuarios = new GenericType<List<UsuarioDto>>() {};
    return webTarget.path("user/get").request(MediaType.APPLICATION_JSON).get(usuarios);
  }

  public boolean checkUsuarioPorNombre(String nombre) {
    String respuesta =
        webTarget.path("user/check/" + nombre).request(MediaType.TEXT_PLAIN).get(String.class);
    boolean resultado = Boolean.parseBoolean(respuesta);
    return resultado;
  }

  public UsuarioDto getUsuario(int id) {

    return webTarget
        .path("user/get/" + id)
        .request(MediaType.APPLICATION_JSON)
        .get(UsuarioDto.class);
  }

  public UsuarioDto getUsuarioPorNombre(String nombre) {

    return webTarget
        .path("user/getid/" + nombre)
        .request(MediaType.APPLICATION_JSON)
        .get(UsuarioDto.class);
  }

  public int updatePuntos(int puntosObtenidos, String nombre) {
    String respuesta =
        webTarget
            .path("user/add/" + nombre + "/" + puntosObtenidos)
            .request(MediaType.TEXT_PLAIN)
            .get(String.class);
    return Integer.parseInt(respuesta);
  }

  public boolean checkPassword(UsuarioDto user) {

    String respuesta =
        webTarget
            .path("user/login")
            .request(MediaType.TEXT_PLAIN)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON), String.class);
    return Boolean.parseBoolean(respuesta);
  }

  public int registroUsuario(UsuarioDto user) {
    String respuesta =
        webTarget
            .path("user/registro")
            .request(MediaType.TEXT_PLAIN)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON), String.class);
    return Integer.parseInt(respuesta);
  }

  public int borrarUsuario(int id) {

    String respuesta =
        webTarget.path("user/eliminar/" + id).request(MediaType.TEXT_PLAIN).get(String.class);
    return Integer.parseInt(respuesta);
  }

  public int modificaUsuario(UsuarioDto user) {

    String respuesta =
        webTarget
            .path("user/modificar")
            .request(MediaType.TEXT_PLAIN)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON), String.class);
    return Integer.parseInt(respuesta);
  }
  /*
  public String ping() {

      return webTarget.path("notifications/ping")
              .request(MediaType.APPLICATION_JSON)
              .get(String.class);
  }

  public Notification getNotification(String id, String name) {

      return webTarget.path("notifications/get/"+id+"/name")
              .queryParam("name", name)
              .request(MediaType.APPLICATION_JSON)
              .get(Notification.class);
  }

  public Notification putNotification(String id, String name) {

      return webTarget.path("notifications/get/"+id+"/"+name)
              .request(MediaType.APPLICATION_JSON)
              .put(Entity.entity("",MediaType.APPLICATION_JSON), Notification.class);
  }

  public Notification postNotification(Notification notification) {

      return webTarget.path("notifications/post")
              .request(MediaType.APPLICATION_JSON)
              .post(Entity.entity(notification,MediaType.APPLICATION_JSON), Notification.class);
  }*/
}
