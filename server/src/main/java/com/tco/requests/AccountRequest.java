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
    private String userID;

    @Override
    public void buildResponse() {
        if (!email.isEmpty()) {
            login();
        } else {
            register();
        }
    }

    public AccountRequest() {
        this.username = "User";
        this.password = "password";
        this.email = "email@domain.com";
    }

    private void login() {

    }
}
