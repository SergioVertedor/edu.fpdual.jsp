package edu.fpdual.webservice.model.application.manager;

import edu.fpdual.webservice.model.application.dao.UsuarioDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
  /***
   * Busca todos los usuarios al recibir la solicitud, los devuelve en una lista.
   * @param con Conexión con MySQL
   * @return Devuelve una lista con todos los usuarios y sus campos.
   */
  public List<UsuarioDao> buscarTodos(Connection con) {
    try (Statement stmt = con.createStatement()) {
      ResultSet result = stmt.executeQuery("SELECT * FROM usuario");
      List<UsuarioDao> usuarios = new ArrayList<>();
      while (result.next()) {
        usuarios.add(new UsuarioDao(result));
      }
      return usuarios;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /***
   * Busca al usuario que corresponda con la ID proporcionada y lo devuelve como objeto.
   * @param con Conexión con MySQL
   * @param id Identificador proporcionado por el cliente.
   * @return Devuelve un objeto del tipo UsuarioDao.
   */
  public UsuarioDao buscarPorId(Connection con, Integer id) {

    try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario where id = ?")) {
      stmt.setInt(1, id);
      ResultSet result = stmt.executeQuery();
      UsuarioDao usuario = null;
      if (result.next()) {
        usuario = new UsuarioDao(result);
      }
      return usuario;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public UsuarioDao buscarIdPorNombre(Connection con, String nombre) {

    try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario where nombre = ?")) {
      stmt.setString(1, nombre);
      ResultSet result = stmt.executeQuery();
      UsuarioDao usuario = null;
      if (result.next()) {
        usuario = new UsuarioDao(result);
      }
      return usuario;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }


  public boolean buscarPorNombreExacto(Connection con, String name) {
    boolean isListed = false;
    try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario where nombre = ?")) {
      stmt.setString(1, name);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        isListed = true;
      } else {
        isListed = false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return isListed;
  }

  public boolean buscarUsuarioConPassword(Connection con, String name, String password) {
    boolean esCorrecto = false;
    try (PreparedStatement stmt =
        con.prepareStatement("SELECT * FROM usuario where nombre = ? and password = ?")) {
      stmt.setString(1, name);
      stmt.setString(2, password);
      ResultSet result = stmt.executeQuery();
      int lineas = 0;
      while (result.next()) {
        lineas++;
      }
      if (lineas == 1) {
        esCorrecto = true;
      } else {
        esCorrecto = false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return esCorrecto;
  }

  public int insertarUsuario(Connection con, UsuarioDao usuario) {
    int lineas = 0;
    try {
      PreparedStatement sentencia =
          con.prepareStatement("INSERT INTO usuario (nombre, correo, password) values (?, ?, ?)");
      sentencia.setString(1, usuario.getNombre());
      sentencia.setString(2, usuario.getCorreo());
      sentencia.setString(3, usuario.getPassword());
      lineas = sentencia.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lineas;
  }

  public int updatePuntos(Connection con, int puntosObtenidos, String nombre) {
    int lineas = 0;
    try {
      PreparedStatement sentencia =
          con.prepareStatement("UPDATE usuario SET puntos = puntos + ? WHERE nombre = ?");
      sentencia.setInt(1, puntosObtenidos);
      sentencia.setString(2, nombre);
      lineas = sentencia.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return lineas;
  }

  public int eliminarUsuario(Connection con, int id) {
    int lineas = 0;
    try {
      PreparedStatement sentencia = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
      sentencia.setInt(1, id);
      lineas = sentencia.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lineas;
  }

  public int modificarUsuario(Connection con, UsuarioDao usuario) {
    int lineas = 0;
    try {
      PreparedStatement sentencia =
          con.prepareStatement(
              "UPDATE usuario SET nombre = ?, correo =  ?, password = ? WHERE id = ?");
      sentencia.setString(1, usuario.getNombre());
      sentencia.setString(2, usuario.getCorreo());
      sentencia.setString(3, usuario.getPassword());
      sentencia.setString(4, usuario.getId());
      lineas = sentencia.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lineas;
  }
}
