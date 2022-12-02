package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.lang.Exception;
import java.util.NoSuchElementException;

public class SQLGuide {

  //add more tables as needed
  private final static String USERS_TABLE = "users";
  private final static String USERS_COLUMNS = " (username, email, pass)";
  private final static String MATCH_STATE = "ongoingMatch";
  private final static String MATCH_STATE_COLUMNS = " (playerTurn, gameStateFEN, whitePlayer, blackPlayer)";

  public static class Database {
    public static boolean registerUser(String user, String email, String encryptedPassword) throws Exception {
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

    public static String[] verifyUser(String user, String encryptedPassword) throws Exception {
      String sql = Select.selectUserByLogin(user, encryptedPassword);
      // try-with-resources, auto closes db connection
      try (
        // connect to db
        Connection conn = DriverManager.getConnection(Credential.getUrl(), Credential.getUser(), Credential.getPassword());
        Statement query = conn.createStatement();
        ResultSet results = query.executeQuery(sql);
      ) {

        if (results.first()) {
          return convertResultsToStringArray(results);
        } else {
          throw new NoSuchElementException("No match found in database");
        }
      } catch (Exception e) {
        throw e;
      }
    }

    public static String[] getUserById(String userId) throws Exception {
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
    public static boolean createMatch(int whitePlayer, int blackPlayer) throws Exception {
      String sql = Select.insertMatch(whitePlayer, blackPlayer);

      try (
        Connection conn = DriverManager.getConnection(Credential.getUrl(), Credential.getUser(), Credential.getPassword());
        Statement query = conn.createStatement();
      ) {
        int rowCount = query.executeUpdate(sql);
        if (rowCount == 1) {
          return true;
        } else {
          return false;
        }
      } catch (Exception e){
        throw e;
      }
    }

    public static String[] updateMatchState(int matchId, String fenstring, String captured) throws Exception {
      String sql = Select.updateMatchById(matchId, fenstring, captured);

      try (
        Connection conn = DriverManager.getConnection(Credential.getUrl(), Credential.getUser(), Credential.getPassword());
        Statement query = conn.createStatement();
        ResultSet results = query.executeQuery(sql);
      ) {
        return convertResultsToStringArray(results);
      } catch (Exception e) {
        throw e;
      }
    }

    //helper methods
    private static String[] convertResultsToStringArray(ResultSet results) throws Exception {
      int colCount = results.getMetaData().getColumnCount();
      String[] resultsArray = new String[colCount];
      if (results.first()) {
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
      return "INSERT INTO "
        + USERS_TABLE
        + USERS_COLUMNS
        + " VALUES ('"
        + user + "', '"
        + email + "', '"
        + encryptedPassword + "');";
    }

    static String selectUserByLogin(String user, String encryptedPassword) {
      return "SELECT * FROM "
        + USERS_TABLE
        + " WHERE username = '" + user + "'"
        + " AND pass = '" + encryptedPassword + "';";
    }

    static String selectUserById(String userId) {
      return "SELECT * FROM "
        + USERS_TABLE
        + " WHERE user_id = '" + userId + "';";
    }

    static String insertMatch(int whitePlayer, int blackPlayer){
      return "INSERT INTO"
      + MATCH_STATE 
      + MATCH_STATE_COLUMNS
      + "VALUES ('"
      + "'0', '"
      + "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR', '"
      + whitePlayer + "', '"
      + blackPlayer + "');";
    }

    static String updateMatchById(int matchId, String fenstring, String captured) {
      return "UPDATE"
        + MATCH_STATE
        + "SET fenstring = '" + fenstring + "', "
        + "capturedPieces = '" + captured + "' "
        + "WHERE match_id = " + matchId + ";";
    }
  }

  static class Credential {
    private final static String USER = "victor45";
    private final static String PASSWORD = "832519031";
    private final static String URL = "jdbc:mariadb://faure.cs.colostate.edu/cs414_team17";
    
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
