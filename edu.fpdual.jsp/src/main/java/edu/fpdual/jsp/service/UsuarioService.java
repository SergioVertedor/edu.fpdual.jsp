package edu.fpdual.jsp.service;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.dao.Usuario;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UsuarioService {
  private MySQLConnector connector;
  private UsuarioManager manager;

  public UsuarioService(MySQLConnector connector, UsuarioManager manager) {
    this.connector = connector;
    this.manager = manager;
  }

  /***
   * Metodo que busca en el servidor MySQL todos los usuarios.
   * @return Devuelve una lista que contiene todos los usuarios en el registro.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
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

  public int modificarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.modificarUsuario(con, usuario);
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

  public int eliminarUsuario(int id) throws SQLException, ClassNotFoundException {
    Connection con = null;
    int lineas = 0;
    try {
      con = connector.getMySQLConnection();
      lineas = manager.eliminarUsuario(con, id);
    } finally {
      if (con != null) {
        con.close();
      }
      return lineas;
    }
  }

  public Map<String, String> buscarUsuarioConPassword(String name) throws SQLException, ClassNotFoundException {
    Connection con = null;
    Map<String, String> mapa = new TreeMap<>();
    try {
      con = connector.getMySQLConnection();
      mapa = manager.buscarUsuarioConPassword(con, name);
    } finally {
      if (con != null) {
        con.close();
      }

    }
    return mapa;
  }

  public boolean buscarPorNombreExacto(String name) throws SQLException, ClassNotFoundException {
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
