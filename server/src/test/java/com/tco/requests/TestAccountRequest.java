package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAccountRequest {

    private AccountRequest req;

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new AccountRequest("User");
    }

    @Test
    @DisplayName("mheavner: Initial values")
    public void testType() {
        assertEquals("User", req.getUsername());
        assertEquals("password", req.getPassword());
        assertEquals("email@domain.com", req.getEmail());
    }

}