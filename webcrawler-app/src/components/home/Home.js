import React, { Component } from 'react';
import { Nav, Navbar } from 'react-bootstrap';

import './Home.css';
import 'bootstrap/dist/css/bootstrap.css';




export default class Home extends Component {


    render() {
        return (
            <div className="header">
                <Navbar bg="dark" variant="dark">
                    <Navbar.Brand href="/home">
                        <img alt="" src="/earth.png" width="30" height="30" className="d-inline-block align-top" /> {'Web Crawler'}
                    </Navbar.Brand>
                    <Nav className="mr-auto">
                            <Nav.Link href="/urlfeeder"> Url Feeder </Nav.Link>
                    </Nav>
                    <Nav>
                        <form className="form-inline">
                            <div className="form-group">
                                {'Welcome '+sessionStorage.userName}
                            </div>
                        </form>
                    </Nav>
                </Navbar>
            </div>
        );
    }
}