import React, { Component } from 'react';
import './Login.css';
import NavHeader from '../header/NavHeader';
import AppFooter from '../footer/AppFooter';
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';
import { NotificationContainer, NotificationManager } from 'react-notifications';

export default class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            firstName: "",
            lastName: "",
            emailId: "",
            password: ""
        }

        this.handleInputChange = this.handleInputChange.bind(this);
        this.register = this.register.bind(this);
    }

    handleInputChange(event) {
        event.preventDefault();
        const target = event.target;
        let value = target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });
    }


    register(event) {
        event.preventDefault();
        let user = this.state;
        console.log(user);
        axios.post('http://localhost:8989/webcrawlerservice/usersignup', JSON.stringify(user)).then(function (response) {
            NotificationManager.success("User Registration Successful! Please Login.", '', 5000);
        }).catch(function (response) {
            NotificationManager.success("User Registration Failed!", '', 5000);
        });

    }

    render() {
        return (
            <div >
                <NavHeader />
                <div>
                    <div className="row">
                        <div className="col-6">
                            <img alt="" src="/WebCrawler.PNG" />
                        </div>
                        <div className="col-1"></div>
                        <div className="col-5">
                            <div className="container">
                                <form>
                                    <br />
                                    <h2>Register Now</h2>
                                    <br />
                                    <div className="form-group">
                                        <input type="input" className="form-control" name="firstName" placeholder="First Name"
                                            value={this.state.firstName} onChange={this.handleInputChange} />
                                    </div>
                                    <div className="form-group">
                                        <input type="input" className="form-control" name="lastName" placeholder="Last Name"
                                            value={this.state.lastName} onChange={this.handleInputChange} />
                                    </div>
                                    <div className="form-group">
                                        <input type="email" className="form-control" name="emailId" placeholder="Email Id"
                                            value={this.state.emailId} onChange={this.handleInputChange} />
                                    </div>
                                    <div className="form-group">
                                        <input type="password" className="form-control" name="password" placeholder="Password"
                                            value={this.state.password} onChange={this.handleInputChange} />
                                    </div>
                                    <div className="form-group">
                                        <button className="btn btn-primary" onClick={this.register}>Submit</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <NotificationContainer />
                <AppFooter />
            </div>
        );
    }

}
