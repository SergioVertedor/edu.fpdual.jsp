package edu.fpdual.webservice.model.application.connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import lombok.Getter;
import lombok.Setter;

/**
 * Class responsible for creation of MySQL DB connection.
 *
 * @author jose.m.prieto.villar
 */
public class MySQLConnector {

  // Toma como atributo de la clase, las propiedades de config.properties, se crea el atributo para
  // contener la configuracion.
  @Setter @Getter Properties prop = new Properties();

  public MySQLConnector() {
    try {
      // Este constructor carga en el atributo prop, las configuraciones de config.properties
      prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates the connection object for a MySQL DDBB
   *
   * @return a {@link Connection}
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
    try {

      // Carga en ejecución el driver.
      Class.forName(prop.getProperty(MySQLConstants.DRIVER));

      // Creates the connection based on the obtained URL.
      return DriverManager.getConnection(getURL());

    } catch (/*ClassNotFoundException |*/ SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * Obtains the URL to connect to a MySQL DDBB.
   *
   * @return an URL
   */
  private String getURL() {
    // jdbc:mysql://localhost:3306/world?user=sa&password=latita&useSSL=false;
    String url =
        (prop.getProperty(MySQLConstants.URL_PREFIX))
            + (prop.getProperty(MySQLConstants.URL_HOST))
            + (":")
            + (prop.getProperty(MySQLConstants.URL_PORT))
            + ("/")
            + (prop.getProperty(MySQLConstants.URL_SCHEMA))
            + ("?user=")
            + (prop.getProperty(MySQLConstants.USER))
            + ("&password=")
            + (prop.getProperty(MySQLConstants.PASSWD))
            + ("&allowPublicKeyRetrieval=")
            + (prop.getProperty(MySQLConstants.ALLOW_PUBLIC_KEY_RETRIEVAL))
            + ("&useJDBCCompliantTimezoneShift=")
            + (prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT))
            + ("&useLegacyDatetimeCode=")
            + (prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE))
            + ("&serverTimezone=")
            + (prop.getProperty(MySQLConstants.SERVER_TIMEZONE))
            + ("&useSSL=")
            + (prop.getProperty(MySQLConstants.URL_SSL));
    return url;
  }

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    MySQLConnector connector = new MySQLConnector();
    Connection connection = connector.getMySQLConnection();
    System.out.println(connection.getCatalog());
  }
}
