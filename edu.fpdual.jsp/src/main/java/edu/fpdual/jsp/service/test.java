package edu.fpdual.jsp.service;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.dao.Usuario;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import lombok.SneakyThrows;

public class test {
  @SneakyThrows
  public static void main(String[] args) {
    UsuarioService userSrv = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    userSrv.insertarUsuario(new Usuario("test2", "12345678", "12345678"));
  }
}
