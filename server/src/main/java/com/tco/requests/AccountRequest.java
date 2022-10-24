package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import com.tco.misc.SQLGuide;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    private String username;
    private String password;
    private String email;
    private int userID;

    @Override
    public void buildResponse() {
        sendDBQuery();
    }

    public AccountRequest() {
        this.username = "User";
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
            String[] results;
            if (!email.isEmpty()) {
                results = SQLGuide.Database.verifyUser(this.username, this.password);
                this.userID = Integer.parseInt(results[0]);
                return true;
            } else {
                SQLGuide.Database.registerUser(this.username, this.email, this.password);
                return true;
            }
        } catch (Exception e) {
            this.userID = -1;
            return false;
        }
    }
}
