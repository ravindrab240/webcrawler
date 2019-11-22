import React, { Component } from 'react';
import Login from './components/login/Login';


import './App.css';
import 'bootstrap/dist/css/bootstrap.css';



export default class App extends Component {

    render() {
        return (
            <div >
                <Login />
            </div>
        );
    }
}
