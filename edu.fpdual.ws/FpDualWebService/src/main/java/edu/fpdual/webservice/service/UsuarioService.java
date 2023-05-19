package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.application.connector.MySQLConnector;
import edu.fpdual.webservice.model.application.dao.UsuarioDao;
import edu.fpdual.webservice.model.application.manager.UsuarioManager;
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

  /***
   * Metodo que busca en el servidor MySQL todos los usuarios.
   * @return Devuelve una lista que contiene todos los usuarios en el registro.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public List<UsuarioDao> buscarATodos() throws SQLException, ClassNotFoundException {
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

  public UsuarioDao buscarPorId(int id) throws SQLException, ClassNotFoundException {
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

  public int modificarUsuario(UsuarioDao usuario) throws SQLException, ClassNotFoundException {
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

  public void insertarUsuario(UsuarioDao usuario) throws SQLException, ClassNotFoundException {
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

  public boolean buscarUsuarioConPassword(String name, String password) throws SQLException, ClassNotFoundException {
    Connection con = null;
    boolean esCorrecto = false;
    try {
      con = connector.getMySQLConnection();
      esCorrecto = manager.buscarUsuarioConPassword(con, name, password);
    } finally {
      if (con != null) {
        con.close();
      }

    }
    return esCorrecto;
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
