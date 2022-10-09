import React, { useState } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { SnackbarProvider, useSnackbar } from 'notistack';
import Page from './Page';

export default function App() {
    /*const [token, useToken] = useState();

    if(!token){
        return <Login setToken={setToken} />
    } */
    return (
        <SnackbarProvider maxSnack={3} preventDuplicate>
            <HookCaller />
            {/*<BrowserRouter>
                <Switch>
                    <Route path="/Page">
                        <Page />
                    </Route>
                </Switch>
            </BrowserRouter> */}
        </SnackbarProvider>
    );
}

export const HookCaller = () => {
    const { enqueueSnackbar } = useSnackbar();

    function showMessage(message, variant = "info") {
        enqueueSnackbar(message, { variant: variant })
    }

    return <Page showMessage={showMessage} />;
};
