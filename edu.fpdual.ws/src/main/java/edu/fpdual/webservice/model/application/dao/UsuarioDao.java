package edu.fpdual.webservice.model.application.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDao implements Comparable<UsuarioDao> {
  private String id;
  private String nombre;
  private String correo;
  private String password;
  private int puntos;


  public UsuarioDao(ResultSet result) {
    try {
      this.id = result.getString("id");
      this.nombre = result.getString("nombre");
      this.correo = result.getString("correo");
      this.password = result.getString("password");
      this.puntos = result.getInt("puntos");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int compareTo(UsuarioDao o) {
    return this.nombre.compareTo(o.getNombre());
  }
}
