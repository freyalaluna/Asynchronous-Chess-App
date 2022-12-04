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

public class TestDeleteRequest {

    private DeleteRequest req;
    private static MockedStatic<Database> mockDb;

    @BeforeAll
    public static void setup() {
        mockDb = Mockito.mockStatic(Database.class);
    }

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new DeleteRequest("0");
    }

    @AfterAll
    public static void cleanup() {
        mockDb.close();
    }

    @Test
    @DisplayName("victor45: Initial values")
    public void testType() {
        assertEquals("0", req.getUserID());
    }

    @Test
    @DisplayName("victor45: Test delete account buildResponse with mock connection")
    public void testDeleteBuildResponse() throws Exception {
        Method deleteMethod = DeleteRequest.class.getDeclaredMethod("deleteAccount");
        deleteMethod.setAccessible(true);
        mockDb.when(() -> Database.deleteUser(Mockito.anyString())).thenReturn(true);
        assertTrue((boolean) deleteMethod.invoke(req));
        assertEquals("0", req.getUserID());
    }

    @Test
    @DisplayName("victor45: Test delete account error for no existing account")
    public void testDeleteError() throws Exception {
        mockDb.when(() -> Database.deleteUser(Mockito.anyString()))
            .thenThrow(new Exception());
        Method registerMethod = DeleteRequest.class.getDeclaredMethod("deleteAccount");
        registerMethod.setAccessible(true);
        assertThrows(Exception.class, () -> registerMethod.invoke(req));
    }

}
