package edu.fpdual.jsp.service;

import edu.fpdual.jsp.persistence.connector.MySQLConnector;
import edu.fpdual.jsp.persistence.manager.UsuarioManager;
import edu.fpdual.jsp.web.dto.Usuario;
import lombok.SneakyThrows;

public class test {
  @SneakyThrows
  public static void main(String[] args) {
    UsuarioService userSrv = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    System.out.println(userSrv.searchForExactName("asdf"));
  }
}
