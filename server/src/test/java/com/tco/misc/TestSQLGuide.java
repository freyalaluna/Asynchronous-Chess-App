package com.tco.misc;

import com.tco.misc.SQLGuide.Credential;
import com.tco.misc.SQLGuide.Database;
import com.tco.misc.SQLGuide.Select;

import java.lang.reflect.Type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;

public class TestSQLGuide {

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

    // Tests for Database Class
}
