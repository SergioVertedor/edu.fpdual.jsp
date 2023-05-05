package edu.fpdual.jsp.service;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.dao.Usuario;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
  private MySQLConnector connector;
  private UsuarioManager manager;

  public UsuarioService(MySQLConnector connector, UsuarioManager manager) {
    this.connector = connector;
    this.manager = manager;
  }

  public List<Usuario> buscarATodos() throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.buscarTodos(con);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  public Usuario buscarPorId(int id) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.buscarPorId(con, id);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  public List<Usuario> filtrarPorNombre(String name) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.filtrarPorNombre(con, name);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  public void insertarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      manager.insertarUsuario(con, usuario);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  public void eliminarUsuario(int id) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      manager.eliminarUsuario(con, id);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }


  public boolean buscarPorNombre(String name) throws SQLException, ClassNotFoundException {
    Connection con = null;
    boolean result = false;
    try {
      con = connector.getMySQLConnection();
      result = manager.buscarPorNombreExacto(con, name);
    } finally {
      if (con != null) {
        con.close();
      }
    }
    return result;
  }
}
