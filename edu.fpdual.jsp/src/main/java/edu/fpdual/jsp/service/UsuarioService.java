package edu.fpdual.jsp.service;
import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.dao.Usuario;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
public class UsuarioService {
  private MySQLConnector connector;
  private UsuarioManager manager;
  public UsuarioService(MySQLConnector connector, UsuarioManager manager) {
    this.connector = connector;
    this.manager = manager;
  }
  public List<Usuario> findAll() throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.findAll(con);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }
  public Usuario findById(int id) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.findById(con, id);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }
  public List<Usuario> filterByName(String name) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.filterByName(con, name);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }
  public void insertUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      manager.insertUsuario(con, usuario);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }
	public void deleteUsuario(int id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = connector.getMySQLConnection();
			manager.deleteUsuario(con, id);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
  public boolean searchForExactName(String name) throws SQLException, ClassNotFoundException {
    Connection con = null;
    boolean result = false;
    try {
      con = connector.getMySQLConnection();
      result = manager.searchForExactName(con, name);
    } finally {
      if (con != null) {
        con.close();
      }
    }
    return result;
  }
}
