import {createStore, applyMiddleware} from 'redux';
import storageSession from 'redux-persist/lib/storage/session'
import {persistStore, persistCombineReducers} from 'redux-persist'
import hardSet from 'redux-persist/lib/stateReconciler/hardSet'
import ScrumDataReducer from "./reducers/ScrumDataReducer"

const persistConfig = {
    key: 'root',
    storage: storageSession
}

const scrumReducers = {
    scrumData: ScrumDataReducer
}

const persistCombinedReducers = persistCombineReducers(persistConfig, scrumReducers)

const configureStore = () => {
    const store = createStore(persistCombinedReducers)
    const persistor = persistStore(store)
    return {store, persistor};
};

export default configureStore