import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import './AppFooter.css'

export default class AppFooter extends Component {

    render() {
        return (
            <div className="footer">
                <footer className="page-footer font-small blue">
                    <div className="footer-copyright text-center py-3">
                        <p text-align="center">All rights reserved @WebCrawler - 2019</p>
                    </div>
                </footer>
            </div>
        );
    }
}