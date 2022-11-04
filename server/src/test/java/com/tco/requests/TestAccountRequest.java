package com.tco.requests;

import com.tco.misc.SQLGuide.Database;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAccountRequest {

    private AccountRequest req;
    private static MockedStatic<Database> mockDb;

    private String[] loginResult = {"0", "User", "password", "email@domain.com"};

    @BeforeAll
    public static void setup() {
        mockDb = Mockito.mockStatic(Database.class);
    }

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new AccountRequest("User");
    }

    @AfterAll
    public static void cleanup() {
        mockDb.close();
    }

    @Test
    @DisplayName("mheavner: Initial values")
    public void testType() {
        assertEquals("User", req.getUsername());
        assertEquals("password", req.getPassword());
    }

    @Test
    @DisplayName("mheavner: Test registration buildResponse with mock connection")
    public void testRegisterBuildResponse() throws Exception {
        req = new AccountRequest("User", "email@domain.com");
        Method registerMethod = AccountRequest.class.getDeclaredMethod("register");
        registerMethod.setAccessible(true);
        mockDb.when(() -> Database.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        assertTrue((boolean) registerMethod.invoke(req));
        assertEquals("email@domain.com", req.getEmail());
    }

    @Test
    @DisplayName("mheavner: Test login buildResponse with mock connection")
    public void testLoginBuildResponse() throws Exception {
        mockDb.when(() -> Database.verifyUser(Mockito.anyString(), Mockito.anyString())).thenReturn(loginResult);
        req.buildResponse();
        assertEquals(0, req.getUserID());
    }

    @Test
    @DisplayName("mheavner: Test registration conflict with existing account")
    public void testRegistrationConflict() throws Exception {
        mockDb.when(() -> Database.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenThrow(new Exception());
        req = new AccountRequest("User", "email@domain.com");
        Method registerMethod = AccountRequest.class.getDeclaredMethod("register");
        registerMethod.setAccessible(true);
        assertThrows(Exception.class, () -> registerMethod.invoke(req));
    }

}