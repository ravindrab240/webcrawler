import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import UrlFeeder from './components/urlfeeder/UrlFeeder';
import AppFooter from './components/footer/AppFooter';
import * as serviceWorker from './serviceWorker';
import { Route, BrowserRouter as Router } from 'react-router-dom'
import Home from './components/home/Home';

const routing = (
    <Router>
        <div>
            <Route exact path="/" component={App} />
            <Route path="/home" component={Home} />
            <Route path="/urlfeeder" component={UrlFeeder} />
        </div>
        <AppFooter />
    </Router>

)

ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
