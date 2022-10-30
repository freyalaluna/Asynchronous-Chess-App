package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import com.tco.misc.SQLGuide;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(AccountRequest.class);

    private String username;
    private String password;
    private String email;
    private int userID;

    @Override
    public void buildResponse() {
        sendDBQuery();
    }

    // Constructor for testing
    public AccountRequest(String user) {
        this.username = user;
        this.password = "password";
        this.email = "email@domain.com";
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public int getUserID() {
        return this.userID;
    }

    private boolean sendDBQuery() {
        try {
            if (this.email == null) {
                login();
                return true;
            } else {
                register();
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            this.userID = -1;
            return false;
        }
    }

    private void login() throws Exception {
        log.info("Attempting to login with username "+this.username);
        String[] results = SQLGuide.Database.verifyUser(this.username, this.password);
        this.userID = Integer.parseInt(results[0]);
        log.info("Successfully logged in with username "+this.username);
    }

    private void register() throws Exception {
        log.info("Attempting to register account with email "+this.email);
        SQLGuide.Database.registerUser(this.username, this.email, this.password);
        log.info("Successfully registered account with email "+this.email);
    }
}
