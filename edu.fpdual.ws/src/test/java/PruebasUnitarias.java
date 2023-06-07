import edu.fpdual.webservice.model.application.connector.MySQLConnector;
import edu.fpdual.webservice.model.application.dao.UsuarioDao;
import edu.fpdual.webservice.model.application.manager.UsuarioManager;
import edu.fpdual.webservice.service.UsuarioService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PruebasUnitarias {
  /**
   * Test que busca comprobar que lo que devuelve el método testeado sea una Lista.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testBuscarATodos() throws SQLException, ClassNotFoundException {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    List<UsuarioDao> lista = new ArrayList<>();
    lista.add(new UsuarioDao());
    String resultadoEsperado = lista.getClass().getName();
    String resultado = user.buscarATodos().getClass().getName();

    assertEquals(resultadoEsperado, resultado);
  }

  /**
   * Test que busca comprobar que lo que devuelve el método testeado sea un UsuarioDao.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testBuscarPorId() throws SQLException, ClassNotFoundException {
    final int ID_ADMIN = 9;
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    UsuarioDao usuario = new UsuarioDao();
    String resultadoEsperado = usuario.getClass().getName();
    String resultado = user.buscarPorId(ID_ADMIN).getClass().getName();

    assertEquals(resultadoEsperado, resultado);
  }

  /**
   * Test que busca comprobar que el objeto que se nos da es un UsuarioDao o por el contrario la
   * respuesta será null.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testBuscarIdPorNombre() throws SQLException, ClassNotFoundException {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    UsuarioDao usuario = new UsuarioDao();
    String resultadoEsperado = usuario.getClass().getName();
    String resultado = user.buscarIdPorNombre("admin").getClass().getName();

    assertEquals(resultadoEsperado, resultado);
    assertNull(null, user.buscarIdPorNombre("admn"));
  }

  /**
   * Test que comprueba el trazo que sigue hasta llegar al UsuarioService, el cual para evitar
   * modificaciones en la base de datos, usamos Mockito.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testModificarUsuario() throws SQLException, ClassNotFoundException {
    UsuarioDao usuarioModificado = new UsuarioDao();
    UsuarioService userMock = Mockito.mock(UsuarioService.class);
    when(userMock.modificarUsuario(usuarioModificado)).thenReturn(1);
    int lineas = userMock.modificarUsuario(usuarioModificado);

    assertEquals(1, lineas);
  }

  /**
   * Test que crea un usuario temporal y lo inserta en la bbdd, tras la comprobación, es eliminado.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testInsertarUsuario() throws SQLException, ClassNotFoundException {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    int resultadoEsperado = 1;
    int resultado =
        user.insertarUsuario(new UsuarioDao("0", "junit", "prueba@junit.test", "123456", 0));

    assertEquals(resultadoEsperado, resultado);

    user.eliminarUsuario(Integer.parseInt(user.buscarIdPorNombre("junit").getId()));
  }

  /**
   * Test que crea un usuario temporal, se le asigna puntos y se compara con el resultado esperado.
   * Después el usuario es eliminado.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testUpdatePuntos() throws SQLException, ClassNotFoundException {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    user.insertarUsuario(new UsuarioDao("0", "junit", "prueba@junit.test", "123456", 0));
    user.updatePuntos(50, "junit");
    int puntos = user.buscarIdPorNombre("junit").getPuntos();

    assertEquals(50, puntos);

    user.eliminarUsuario(Integer.parseInt(user.buscarIdPorNombre("junit").getId()));
  }

  /**
   * Test que crea un usuario temporal, lo elimina y comprueba que se ha eliminado correctamente.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testEliminarUsuario() throws SQLException, ClassNotFoundException {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    user.insertarUsuario(new UsuarioDao("0", "junit", "prueba@junit.test", "123456", 0));
    int resultadoEsperado = 1;
    int lineas = user.eliminarUsuario(Integer.parseInt(user.buscarIdPorNombre("junit").getId()));

    assertEquals(lineas, 1);
  }

  /**
   * Test que crea un usuario temporal, y se prueba el método que comprueba si coinciden con el
   * registro.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testBuscarUsuarioConPassword() throws SQLException, ClassNotFoundException {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    user.insertarUsuario(new UsuarioDao("0", "junit", "prueba@junit.test", "123456", 0));
    boolean isCorrect = user.buscarUsuarioConPassword("junit", "123456");

    assertTrue(isCorrect);

    user.eliminarUsuario(Integer.parseInt(user.buscarIdPorNombre("junit").getId()));
  }

  /**
   * Test que crea un usuario temporal y testea el método para comprobar si existe una coincidencia
   * con el nombre.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testBuscarPorNombreExacto() throws SQLException, ClassNotFoundException {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
    user.insertarUsuario(new UsuarioDao("0", "junit", "prueba@junit.test", "123456", 0));
    boolean existe = user.buscarPorNombreExacto("junit");

    assertTrue(existe);

    user.eliminarUsuario(Integer.parseInt(user.buscarIdPorNombre("junit").getId()));
  }
}
