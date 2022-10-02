package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.lang.Exception;

public class SQLGuide {

  //add more tables as needed
  private final static String USERS_TABLE = "users";
  private final static String USERS_COLUMNS = " (username, email, password)";

  static class Database {
    static boolean registerUser(String user, String email, String encryptedPassword) throws Exception {
      String sql = Select.insertUser(user, email, encryptedPassword);
      // try-with-resources, auto closes db connection
      try (
        // connect to db
        Connection conn = DriverManager.getConnection(Credential.getUrl(), Credential.getUser(), Credential.getPassword());
        Statement query = conn.createStatement();
      ) {
        int rowCount = query.executeUpdate(sql);
        if (rowCount == 1) {
          return true;
        } else {
          return false;
        }
      } catch (Exception e) {
        throw e;
      }
    }

    static boolean verifyUser(String user, String encryptedPassword) throws Exception {
      String sql = Select.selectUserByLogin(user, encryptedPassword);
      // try-with-resources, auto closes db connection
      try (
        // connect to db
        Connection conn = DriverManager.getConnection(Credential.getUrl(), Credential.getUser(), Credential.getPassword());
        Statement query = conn.createStatement();
        ResultSet results = query.executeQuery(sql);
      ) {
        if (count(results) == 1) {
          return true;
        } else {
          return false;
        }
      } catch (Exception e) {
        throw e;
      }
    }

    static String[] getUserById(String userId) throws Exception {
      String sql = Select.selectUserById(userId);
      // try-with-resources, auto closes db connection
      try (
        // connect to db
        Connection conn = DriverManager.getConnection(Credential.getUrl(), Credential.getUser(), Credential.getPassword());
        Statement query = conn.createStatement();
        ResultSet results = query.executeQuery(sql);
      ) {
        return convertResultsToStringArray(results);
      } catch (Exception e) {
        throw e;
      }
    }
    
    //add other needed db queries here, e.g. update match-history

    //helper methods
    private static String[] convertResultsToStringArray(ResultSet results) throws Exception {
      int colCount = results.getMetaData().getColumnCount();
      String[] resultsArray = new String[colCount];
      if (results.next()) {
        for (int i = 1; i <= colCount; i++) {
          resultsArray[i-1] = results.getString(i);
        }
      }
      return resultsArray;
    }

    private static Integer count(ResultSet results) throws Exception {
      if (results.next()) {
        return results.getInt("count");
      }
      throw new Exception("No count results in found query.");
    }
  }

  static class Select {
    static String insertUser(String user, String email, String encryptedPassword) {
      return "INSERT IGNORE INTO "
        + USERS_TABLE
        + USERS_COLUMNS
        + " VALUES ("
        + user + ", "
        + email + ", "
        + encryptedPassword + ");";
    }

    static String selectUserByLogin(String user, String encryptedPassword) {
      return "SELECT * FROM "
        + USERS_TABLE
        + " WHERE username = '" + user + "'"
        + " AND password = '" + encryptedPassword + "';";
    }

    static String selectUserById(String userId) {
      return "SELECT * FROM "
        + USERS_TABLE
        + " WHERE user_id = '" + userId + "';";
    }
  }

  static class Credential {
    private final static String USER = "TODO: ADD DB USERNAME";
    private final static String PASSWORD = "TODO: ADD DB PASSWORD";
    private final static String URL = "TODO: ADD jdbc:mariadb:// DB URL";
    
    static String getUser() {
      return USER;
    }
    static String getPassword() {
      return PASSWORD;
    }
    static String getUrl() {
      return URL;
    }
  }
}
