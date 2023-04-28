package edu.fpdual.jsp.persistence.manager;

import edu.fpdual.jsp.persistence.dao.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * City DTO Manager.
 *
 * <p>Contains all the queries used to consult and manipulate Cities data.
 *
 * @author jose.m.prieto.villar
 */
public class CityManager {

  public List<City> findAll(Connection con) {
    // Create general statement
    try (Statement stmt = con.createStatement()) {
      // Queries the DB
      ResultSet result = stmt.executeQuery("SELECT * FROM City");
      // Set before first registry before going through it.
      result.beforeFirst();

      // Initializes variables
      List<City> cities = new ArrayList<>();
      Map<Integer, String> countries = new HashMap();

      // Run through each result
      while (result.next()) {
        // Initializes a city per result
        cities.add(new City(result));
        // Groups the countried by city
        countries.put(result.getInt("ID"), result.getString("CountryCode"));
      }


      return cities;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public City findById(Connection con, Integer id) {
    // prepare SQL statement
    String sql =
        "select * " + "from city a, Country b " + "where a.id = ? " + "and a.CountryCode = b.Code";

    // Create general statement
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
      // Add Parameters
      stmt.setInt(1, id);
      // Queries the DB
      ResultSet result = stmt.executeQuery();
      // Set before first registry before going through it.
      result.beforeFirst();

      // Initialize variable
      City city = null;

      // Run through each result
      while (result.next()) {
        // Initializes a city per result
        city = new City(result);
      }

      return city;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<City> findAllByIds(Connection con, Set<Integer> ids) {
    // prepare SQL statement
    String sql =
        String.format(
            "select * "
                + "from city a, Country b "
                + "where a.CountryCode = b.Code "
                + "and a.id IN (%s)",
            ids.stream().map(data -> "\"" + data + "\"").collect(Collectors.joining(", ")));

    // Create general statement
    try (Statement stmt = con.createStatement()) {
      // Queries the DB
      ResultSet result = stmt.executeQuery(sql);
      // Set before first registry before going through it.
      result.beforeFirst();

      // Initialize variable
      List<City> cities = new ArrayList<>();

      // Run through each result
      while (result.next()) {
        // Initializes a city per result
        City city = new City(result);
        cities.add(city);
      }

      return cities;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public Set<City> findByCountryCode(Connection con, String countryCode) {
    // prepare SQL statement
    String sql =
        "select * "
            + "from city a, Country b "
            + "where  a.CountryCode = b.Code "
            + "and b.Code = ? ";

    // Create general statement
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
      // Add Parameters
      stmt.setString(1, countryCode);
      // Queries the DB
      ResultSet result = stmt.executeQuery();
      // Set before first registry before going through it.
      result.beforeFirst();

      // Initialize variable
      Set<City> cities = new HashSet<>();

      // Run through each result
      while (result.next()) {
        // Initializes a city per result
        City city = new City(result);
        cities.add(city);
      }

      return cities;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<City> filterByName(Connection con, String name) {
    try (PreparedStatement stmt = con.prepareStatement("SELECT ? FROM City")) {
      stmt.setString(1, "%" + name + "%");
      // Queries the DB
      ResultSet result = stmt.executeQuery();
      // Set before first registry before going through it.
      result.beforeFirst();

      // Initializes variables
      List<City> cities = new ArrayList<>();
      Map<Integer, String> countries = new HashMap();
      // Run through each result
      while (result.next()) {
        // Initializes a city per result
        cities.add(new City(result));
        // Groups the countried by city
        countries.put(result.getInt("ID"), result.getString("CountryCode"));
      }

      return cities;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void updateCity(Connection con, City city) {
    try {
      PreparedStatement sentencia =
          con.prepareStatement(
              "UPDATE City SET Name = ?, CountryCode = ?, District = ?, Population = ?");
      sentencia.setString(1, city.getName());
      sentencia.setInt(2, city.getCountryCode());
      sentencia.setString(3, city.getDistrict());
      sentencia.setBigDecimal(4, city.getPopulation());
      int lineas = sentencia.executeUpdate();
      if (lineas == 1) {
        System.out.println(lineas + " registro ha sido modificado con éxito.");
      } else {
        System.out.println(lineas + " registros han sido modificados.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void insertCity(Connection con, City city) {
    try {
      PreparedStatement sentencia =
          con.prepareStatement(
              "INSERT INTO City (Name, CountryCode, District, Population) values (?, ?, ?, ?)");
      sentencia.setString(1, city.getName());
      sentencia.setInt(2, city.getCountryCode());
      sentencia.setString(2, city.getDistrict());
      sentencia.setBigDecimal(2, city.getPopulation());
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void deleteCity(Connection con, int id) {
    try {
      PreparedStatement sentencia = con.prepareStatement("DELETE FROM City WHERE ID = ?");
      sentencia.setInt(1, id);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
