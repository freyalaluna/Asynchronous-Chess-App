package com.tco.misc;

import com.tco.misc.SQLGuide.Credential;
import com.tco.misc.SQLGuide.Database;
import com.tco.misc.SQLGuide.Select;

import java.util.NoSuchElementException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSQLGuide {

    private static MockedStatic<DriverManager> mockDriverManager;
    
    @BeforeAll
    public static void beforeAll() {
        mockDriverManager = Mockito.mockStatic(DriverManager.class);
    }

    // Tests for Credential class
    @Test
    @DisplayName("mheavner: Test credential getters")
    public void testCredential() {
        assertEquals("victor45", Credential.getUser());
        assertEquals("832519031", Credential.getPassword());
        assertEquals("jdbc:mariadb://faure.cs.colostate.edu/cs414_team17", Credential.getUrl());
    }

    // Tests for Select class
    @Test
    @DisplayName("mheavner: Test insertUser")
    public void testInsertUser() {
        String expected = "INSERT INTO users (username, email, pass) VALUES ('User', 'email@domain.com', 'password');";
        assertEquals(expected, Select.insertUser("User", "email@domain.com", "password"));
    }

    @Test
    @DisplayName("mheavner: Test selectUserByLogin")
    public void testSelectUserByLogin() {
        String expected = "SELECT * FROM users WHERE username = 'User' AND pass = 'password';";
        assertEquals(expected, Select.selectUserByLogin("User", "password"));
    }

    @Test
    @DisplayName("mheavner: Test selectUserById")
    public void testSelectUserById() {
        String expected = "SELECT * FROM users WHERE user_id = '1';";
        assertEquals(expected, Select.selectUserById("1"));
    }

    @Test
    @DisplayName("fturner: Test insertMatch")
    public void testInsertMatch() {
        String expected = "INSERT INTO ongoingMatch (playerTurn, gameStateFEN, whitePlayer, blackPlayer) VALUES ('0', 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR', '1', '2');";
        assertEquals(expected, Select.insertMatch("1", "2"));
    }

    @Test
    @DisplayName("fturner: Test updateMatchById")
    public void testUpdateMatchById() {
        String expected = "UPDATE ongoingMatch SET fenstring = 'fenstring', capturedPieces = '' WHERE match_id = '1';";
        assertEquals(expected, Select.updateMatchById("1", "fenstring", ""));
    }

    @Test
    @DisplayName("victor45: Test deleteUser")
    public void testDeleteUser() {
        String expected = "DELETE FROM users WHERE userID = '69';";
        assertEquals(expected, Select.deleteUser("69"));
    }

    // Tests for Database Class
    @Test
    @DisplayName("mheavner: Test registerUser w/ true")
    public void testRegisterUserSuccess() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenReturn(1);

        assertTrue(Database.registerUser("User", "email@domain.com", "password"));
    }

    @Test
    @DisplayName("mheavner: Test registerUser w/ false")
    public void testRegisterUserFail() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenReturn(0);

        assertFalse(Database.registerUser("User", "email@domain.com", "password"));
    }

    @Test
    @DisplayName("mheavner: Test registerUser w/ error")
    public void testRegisterUserError() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        SQLException sqlException = new SQLException();

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenThrow(sqlException);

        assertThrows(SQLException.class, () -> Database.registerUser("User", "email@domain.com", "password"));
    }

    @Test
    @DisplayName("mheavner: Test verifyUser w/ false")
    public void testVerifyUserFail() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.first()).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> Database.verifyUser("User", "password"));
    }

    @Test
    @DisplayName("mheavner: Test verifyUser w/ error")
    public void testVerifyUserError() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        SQLException sqlException = new SQLException();

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(Mockito.anyString())).thenThrow(sqlException);

        assertThrows(SQLException.class, () -> Database.verifyUser("User", "password"));
    }

    @Test
    @DisplayName("mheavner: Test getUserById w/ error")
    public void testGetUserByIdError() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        SQLException sqlException = new SQLException();

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(Mockito.anyString())).thenThrow(sqlException);

        assertThrows(SQLException.class, () -> Database.getUserById("1"));
    }

    @Test
    @DisplayName("fturner: Test createMatch w/ true")
    public void testCreateMatchTrue() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenReturn(1);

        assertTrue(Database.createMatch("1", "2"));
    }

    @Test
    @DisplayName("fturner: Test createMatch w/ false")
    public void testCreateMatchFalse() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenReturn(0);

        assertFalse(Database.createMatch("1", "2"));
    }

    @Test
    @DisplayName("fturner: Test createMatch w/ error")
    public void testCreateMatchError() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        SQLException sqlException = new SQLException();

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenThrow(sqlException);

        assertThrows(SQLException.class, () -> Database.createMatch("1", "2"));
    }

    @Test
    @DisplayName("fturner: Test updateMatchState")
    public void testUpdateMatchStateError() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        SQLException sqlException = new SQLException();

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(Mockito.anyString())).thenThrow(sqlException);

        assertThrows(SQLException.class, () -> Database.updateMatchState("1", "fenstring", "captured"));
    }

    @Test
    @DisplayName("victor45: Test deleteUser w/ true")
    public void testDeleteUserSuccess() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenReturn(1);

        assertTrue(Database.deleteUser("17"));
    }

    @Test
    @DisplayName("victor45: Test deleteUser w/ false")
    public void testDeleteUserError() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        SQLException sqlException = new SQLException();

        mockDriverManager.when(() -> DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.anyString())).thenThrow(sqlException);

        assertThrows(SQLException.class, () -> Database.deleteUser("17"));
    }
}
