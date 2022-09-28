package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.lang.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestSQLGuide {
  @Test
  @DisplayName("victor45: Testing SQL query construction for registering user")
  public void testRegisterUserQuery() {
    String username = "victor";
    String email = "victorberggren3@gmail.com";
    String password = "$uper$ecurePassw0rd";
    assertEquals("INSERT IGNORE INTO users (username, email, password) VALUES (" + username + ", " + email + ", " + password + ");", SQLGuide.Select.insertUser(username, email, password));
  }

  @Test
  @DisplayName("victor45: Testing SQL query construction for verifying user")
  public void testVerifyUserQuery() {
    String username = "victor";
    String password = "$uper$ecurePassw0rd";
    assertEquals("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';", SQLGuide.Select.selectUserByLogin(username, password));
  }

  @Test
  @DisplayName("victor45: Testing SQL query construction for selecting user by ID")
  public void testGetUserQuery() {
    String userId = "17";
    assertEquals("SELECT * FROM users WHERE user_id = '" + userId + "';", SQLGuide.Select.selectUserById(userId));
  }
}
