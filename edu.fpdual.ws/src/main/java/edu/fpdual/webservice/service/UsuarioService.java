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
   * Llamada a manager para solicitar una lista de todos los usuarios.
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

  /***
   * Llamada a manager solicitando un objeto UsuarioDao a partir de un parámetro id.
   * @param id Parámetro que hace referencia a la id en base de datos del objeto solicitado.
   * @return Objeto UsuarioDao.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
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

  /***
   * Llamada a manager solicitando un objeto UsuarioDao con objetivo en cliente de recoger su id.
   * @param nombre Parámetro que hace referencia a nombre en base de datos del objeto solicitado.
   * @return Objeto UsuarioDao
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public UsuarioDao buscarIdPorNombre(String nombre) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.buscarIdPorNombre(con, nombre);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  /***
   * Llamada a manager para modificar un registro en base de datos a partir de un UsuarioDao.
   * @param usuario UsuarioDao proporcionado para modificar en base de datos a partir de la id.
   * @return int con el número de registros modificados (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
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

  /***
   * Llamada a manager para la inserción de un nuevo registro en BBDD a partir de un objeto UsuarioDao.
   * @param usuario Objeto UsuarioDao proporcionado para su inserción en BBDD.
   * @return int con el número de filas alteradas (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public int insertarUsuario(UsuarioDao usuario) throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.insertarUsuario(con, usuario);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  /***
   *  Llamada a manager para la actualización de los puntos actuales del usuario.
   * @param puntosObtenidos entero a añadir a los puntos actuales del usuario.
   * @param nombre String conteniendo el nombre del usuario al que añadir los puntos.
   * @return int con el número de filas alteradas (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public int updatePuntos(int puntosObtenidos, String nombre)
      throws SQLException, ClassNotFoundException {
    Connection con = null;
    try {
      con = connector.getMySQLConnection();
      return manager.updatePuntos(con, puntosObtenidos, nombre);
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  /***
   *  Llamada a manager para la eliminación de un registro de la tabla usuario.
   * @param id entero proporcionado como identificador del usuario a eliminar.
   * @return int con el número de filas alteradas (se espera 1 para que sea correcto).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
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

  /***
   *  Llamada a manager para la comprobación de la existencia de la relación entre el nombre
   *  y la contraseña proporcionados.
   * @param name Parámetro proporcionado para la comprobación.
   * @param password Parámetro proporcionado para la comprobación.
   * @return boolean con resultado true si existe la coincidencia.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public boolean buscarUsuarioConPassword(String name, String password)
      throws SQLException, ClassNotFoundException {
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

  /***
   * Llamada a manager para la comprobación de la existencia de un registro con el nombre
   * proporcionado.
   * @param name Parámetro proporcionado para la comprobación.
   * @return boolean que confirma la existencia si es true.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
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
