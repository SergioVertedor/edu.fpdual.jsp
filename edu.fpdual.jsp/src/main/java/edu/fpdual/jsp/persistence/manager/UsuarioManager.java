package edu.fpdual.jsp.persistence.manager;

import edu.fpdual.jsp.persistence.dao.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
  public List<Usuario> buscarTodos(Connection con) {
    try (Statement stmt = con.createStatement()) {
      ResultSet result = stmt.executeQuery("SELECT * FROM usuario");
      result.beforeFirst();
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
      result.beforeFirst();
      Usuario usuario = null;
      while (result.next()) {
        usuario = new Usuario(result);
      }
      return usuario;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<Usuario> filtrarPorNombre(Connection con, String name) {
    try (PreparedStatement stmt = con.prepareStatement("SELECT ? FROM usuario")) {
      stmt.setString(1, "%" + name + "%");
      ResultSet result = stmt.executeQuery();
      result.beforeFirst();
      List<Usuario> usuarios = new ArrayList<>();
      while (result.next()) {
        usuarios.add(new Usuario(result));
      }
      return usuarios;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
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

  public void eliminarUsuario(Connection con, int id) {
    try {
      PreparedStatement sentencia = con.prepareStatement("DELETE FROM usuario WHERE ID = ?");
      sentencia.setInt(1, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
