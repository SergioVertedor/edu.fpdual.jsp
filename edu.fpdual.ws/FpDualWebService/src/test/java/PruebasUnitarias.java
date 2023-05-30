import edu.fpdual.webservice.model.application.connector.MySQLConnector;
import edu.fpdual.webservice.model.application.dao.UsuarioDao;
import edu.fpdual.webservice.model.application.manager.UsuarioManager;
import edu.fpdual.webservice.service.UsuarioService;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
   * Test que busca comprobar que el objeto que se nos da es un UsuarioDao o por el contrario
   * la respuesta será null.
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

  @Test
  public void testModificarUsuario() throws SQLException, ClassNotFoundException {
    UsuarioService userMock = Mockito.mock(UsuarioService.class);
    when(userMock.modificarUsuario(new UsuarioDao())).thenReturn(1);
  }

  @Test
  public void testInsertarUsuario() {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
  }

  @Test
  public void testUpdatePuntos() {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
  }

  @Test
  public void testEliminarUsuario() {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
  }

  @Test
  public void testBuscarUsuarioConPassword() {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
  }

  @Test
  public void testBuscarPorNombreExacto() {
    UsuarioService user = new UsuarioService(new MySQLConnector(), new UsuarioManager());
  }
}
