import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Switch, withRouter} from "react-router-dom";
import {Provider} from "react-redux";
import configureStore from './store';
import {PersistGate} from "redux-persist/integration/react";
import ScrumHome from "./components/ScrumHome";
import CreateData from "./components/CreateData";
import ViewData from "./components/ViewData";
import "./css/bootstrap.min.css"

const {store, persistor} = configureStore()
ReactDOM.render(
<Provider store={store}>
        <PersistGate persistor={persistor}>
            <BrowserRouter>
                <Switch>
                    <Route path="/scrum/create/retrospective">
                        <CreateData />
                    </Route>
                    <Route path="/scrum/view/retrospective">
                        <ViewData />
                    </Route>
                    <Route path="/scrum">
                        <ScrumHome />
                    </Route>
                </Switch>
            </BrowserRouter>
        </PersistGate>
</Provider>,
document.getElementById('root'));