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
        console.log("Account Request succeeded.");
        console.log(accountResponse.userID);
        return accountResponse.userID;
    } else {
        LOG.error("Account Request failed.");
        return -1;
    }
}