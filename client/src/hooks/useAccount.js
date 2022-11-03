import { useState } from 'react';
import { LOG } from '../utils/constants';
import { sendAPIRequest } from '../utils/restfulAPI';
import { getOriginalServerUrl } from '../utils/restfulAPI';

export function useAccount(){
    const [account, setAccount] = useState(null);
    const [userID, setUserID] = useState(0);
    const [email, setEmail] = useState(null);
    const [requestValidated, setRequestValidated] = useState(false)
    

    const context = {account, setAccount,
                     userID, setUserID,
                     email, setEmail,
                     requestValidated, setRequestValidated};

    const accountActions = {
        sendAccountRequest: async(username, password, email) => sendAccountRequest(username, password, email, context),
    };

    return {account, setAccount, userID, setUserID, email, setEmail, requestValidated, setRequestValidated, accountActions};
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
        context.setRequestValidated(true);
    } else {
        LOG.error("Account Request failed.");
        context.setRequestValidated(false);
    }
}