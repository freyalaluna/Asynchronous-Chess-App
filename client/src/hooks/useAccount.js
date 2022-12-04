import { useState } from 'react';
import { LOG } from '../utils/constants';
import { sendAPIRequest } from '../utils/restfulAPI';
import { getOriginalServerUrl } from '../utils/restfulAPI';

export function useAccount(){
    const [account, setAccount] = useState('Player');
    const [userID, setUserID] = useState(0);
    const [email, setEmail] = useState(null);
    

    const context = {account, setAccount,
                     userID, setUserID,
                     email, setEmail};

    const accountActions = {
        sendAccountRequest: async(username, password, email) => sendAccountRequest(username, password, email, context),
        sendDeleteRequest: async() => sendDeleteRequest(context)
    };

    return {account, setAccount, userID, setUserID, email, setEmail, accountActions};
}

async function sendAccountRequest(username, password, email = "", context) {
    var accountResponse = null;
    if(email.length == 0){
        accountResponse = await sendAPIRequest({requestType: "account", username: username, password: password}, getOriginalServerUrl());
    } else {
        accountResponse = await sendAPIRequest({requestType: "account", username: username, email: email, password: password}, getOriginalServerUrl());
    }

    if(accountResponse.userID != null && accountResponse.userID != -1){
        context.setUserID(accountResponse.userID);
        context.setEmail(accountResponse.email);
        context.setAccount(accountResponse.username);
        console.log("Account Request succeeded.");
        return accountResponse.userID;
    } else {
        LOG.error("Account Request failed.");
        return -1;
    }
}

async function sendDeleteRequest(context) {
    var deleteResponse = await sendAPIRequest({requestType: "delete", userID: ""+context.userID}, getOriginalServerUrl());

    if (deleteResponse != null) {
        LOG.info("Delete Request succeeded.")
        var success = deleteResponse.deleteSuccess;
        if (success) {
            context.setAccount('Player');
            context.setUserID(0);
            context.setEmail(null);
        }
        return success;
    } else {
        LOG.error("Delete Request failed.")
        return false;
    }
}
