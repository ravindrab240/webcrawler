import React, { Component } from 'react';
import { Nav, Navbar } from 'react-bootstrap';
import axios from 'axios';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { Route, BrowserRouter as Router } from 'react-router-dom'

import 'bootstrap/dist/css/bootstrap.css';
import 'react-notifications/lib/notifications.css';

import './NavHeader.css';
import Home from '../home/Home';


export default class NavHeader extends Component {



    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            showwarning: false,
            showmessage: ""
        }


        this.handleInputChange = this.handleInputChange.bind(this);
        this.login = this.login.bind(this);
    }

    handleInputChange(event) {
        event.preventDefault();
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    login(event) {
        event.preventDefault();
        const authentication = this.state.username +":"+window.btoa(this.state.password);
        console.log(authentication);
        axios.post('http://localhost:8989/webcrawlerservice/login', authentication).then(function (response) {
            if (response.data.status !== undefined) {
                sessionStorage.setItem("Authenticated", false);
                NotificationManager.warning(response.data.message, '', 5000);
            } else {
                sessionStorage.setItem("Authenticated", true);
                sessionStorage.setItem("userName", response.data.firstName);
                window.location.href = "/home";
            }
        }).catch(function (response) {
            console.log(response);
        });
    }

    render() {
        return (
            <div className="header">
                <NotificationContainer />
                <Navbar bg="dark-black" variant="dark">
                    <Navbar.Brand>
                        <img alt="" src="/earth.png" width="30" height="30" className="d-inline-block align-top" /> {'Web Crawler'}
                    </Navbar.Brand>
                    <Nav className="mr-auto"></Nav>
                    <Nav>
                        
                        <form className="form-inline">
                            <div className="form-group">
                                <input className="form-control form-control-sm" placeholder="Enter your EmailId" hover="hii" id="username" name="username" type="input"
                                    value={this.state.username} onChange={this.handleInputChange} />
                            </div>
                            <div className="form-group">
                                <input className="form-control form-control-sm" placeholder="Enter your Password" id="password" name="password" type="password"
                                    value={this.state.password} onChange={this.handleInputChange} />
                            </div>
                            <div className="form-group">
                                <button className="btn btn-outline-primary btn-sm" onClick={this.login}>Login</button>
                            </div>
                        </form>
                    </Nav>
                    <Router>
                        <Route path="/home" component={Home} />
                    </Router>
                </Navbar>
            </div>
        );
    }
}
