package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import com.tco.misc.SQLGuide;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(DeleteRequest.class);

    private String userID;
    private boolean deleteSuccess = false;

    @Override
    public void buildResponse() {
        sendDBQuery();
    }

    // Constructor for testing
    public DeleteRequest(String userID) {
        this.userID = userID;
    }
    
    public String getUserID() {
      return this.userID;
    }

    private void sendDBQuery() {
        try {
          this.deleteSuccess = deleteAccount();
          return;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private boolean deleteAccount() throws Exception {
        log.info("Attempting to delete account with ID " + this.userID);
        boolean success = SQLGuide.Database.deleteUser(this.userID);
        log.info("Successfully deleted account with ID " + this.userID);
        return success;
    }

    //VISIBLE FOR TESTING
    public boolean getDeleteSuccess() {
        return this.deleteSuccess;
    }
}
