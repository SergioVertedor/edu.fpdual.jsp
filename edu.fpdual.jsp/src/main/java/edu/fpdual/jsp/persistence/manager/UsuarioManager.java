package edu.fpdual.jsp.persistence.manager;

import edu.fpdual.jsp.persistence.dao.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UsuarioManager {
  public List<Usuario> buscarTodos(Connection con) {
    try (Statement stmt = con.createStatement()) {
      ResultSet result = stmt.executeQuery("SELECT * FROM usuario");
      List<Usuario> usuarios = new ArrayList<>();
      while (result.next()) {
        usuarios.add(new Usuario(result));
      }
      return usuarios;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public Usuario buscarPorId(Connection con, Integer id) {

    try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario where id = ?")) {
      stmt.setInt(1, id);
      ResultSet result = stmt.executeQuery();
      Usuario usuario = null;
      if (result.next()) {
        usuario = new Usuario(result);
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

  public Map<String, String> buscarUsuarioConPassword(Connection con, String name) {
    Map<String, String> map = new TreeMap<>();
    try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario")) {
      ResultSet result = stmt.executeQuery();
      while (result.next()) {
        map.put(result.getString(2), result.getString(4));
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
    return map;
  }

  public void insertarUsuario(Connection con, Usuario usuario) {
    try {
      PreparedStatement sentencia =
          con.prepareStatement("INSERT INTO usuario (nombre, correo, password) values (?, ?, ?)");
      sentencia.setString(1, usuario.getNombre());
      sentencia.setString(2, usuario.getCorreo());
      sentencia.setString(3, usuario.getPassword());
      sentencia.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
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

  public int modificarUsuario(Connection con, Usuario usuario) {
    int lineas = 0;
    try {
      PreparedStatement sentencia =
              con.prepareStatement("UPDATE usuario SET nombre = ?, correo =  ?, password = ? WHERE id = ?");
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
