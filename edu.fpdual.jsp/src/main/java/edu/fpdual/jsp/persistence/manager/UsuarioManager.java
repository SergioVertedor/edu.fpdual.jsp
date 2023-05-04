package edu.fpdual.jsp.persistence.manager;

import edu.fpdual.jsp.persistence.dao.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioManager {
  public List<Usuario> findAll(Connection con) {
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

  public Usuario findById(Connection con, Integer id) {

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

  public List<Usuario> filterByName(Connection con, String name) {
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

  public boolean searchForExactName(Connection con, String name) {
    boolean isListed = false;
    try (PreparedStatement stmt = con.prepareStatement("SELECT ? FROM usuario")) {
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

  public void insertUsuario(Connection con, Usuario usuario) {
    try {
      PreparedStatement sentencia =
          con.prepareStatement("INSERT INTO City (nombre, correo, password) values (?, ?, ?)");
      sentencia.setString(1, usuario.getNombre());
      sentencia.setString(2, usuario.getCorreo());
      sentencia.setString(2, usuario.getPassword());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteUsuario(Connection con, int id) {
    try {
      PreparedStatement sentencia = con.prepareStatement("DELETE FROM City WHERE ID = ?");
      sentencia.setInt(1, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
