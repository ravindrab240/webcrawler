import React, { Component } from 'react';
import Home from '../home/Home';
import MaterialTable from 'material-table';
import axios from 'axios';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import 'bootstrap/dist/css/bootstrap.css';
import './UrlFeeder.css';

export default class UrlFeeder extends Component {

    constructor(props) {
        super(props);
        this.state = {
            columns: [
                { title: 'Url Name', field: 'urlname', width: '90px' }
            ],
            data: [{}]
        };
        this.fetchData = this.fetchData.bind(this);
        this.saveData = this.saveData.bind(this);
        this.deleteData = this.deleteData.bind(this);
        this.fetchData(this);
    }

    fetchData(event) {
        axios.get('http://localhost:8989/webcrawlerservice/getUrlFeedData').then(function (response) {
            event.setState({
                data: response.data
            });
        }).catch(function (response) {
            console.log(response);
        });
    }

    saveData(newData) {
        axios.post('http://localhost:8989/webcrawlerservice/saveUrlFeedData', newData.urlname).then(function (response) {
            NotificationManager.success("Saved Url data Successfully!", '', 5000);
        }).catch(function (response) {
            console.log(response);
            NotificationManager.error("Error Occured while saving Url data!", '', 5000);
        });
    }

    deleteData(newData) {
        axios.post('http://localhost:8989/webcrawlerservice/deleteUrlFeedData', newData.urlname).then(function (response) {
            NotificationManager.success("Deleted Url data Successfully!", '', 5000);
        }).catch(function (response) {
            console.log(response);
            NotificationManager.error("Error Occured while deleting Url data!", '', 5000);
        });
    }
  
    render() {
        return (
            <div>
                <Home />
                <br />
                <div className="container">
                <MaterialTable 
                    title="Url Data Info"
                    columns={this.state.columns} data={this.state.data}
                    editable={{
                        onRowAdd: newData =>
                            new Promise(resolve => {
                                this.saveData(newData);
                                setTimeout(() => {
                                    resolve();
                                    this.setState(prevState => {
                                        const data = [...prevState.data];
                                        data.push(newData);
                                        return { ...prevState, data };
                                    });
                                }, 600);
                            }),
                        onRowDelete: oldData =>
                            new Promise(resolve => {
                                this.deleteData(oldData);
                                setTimeout(() => {
                                    resolve();
                                    this.setState(prevState => {
                                        const data = [...prevState.data];
                                        data.splice(data.indexOf(oldData), 1);
                                        return { ...prevState, data };
                                    });
                                }, 600);
                            }),
                    }}
                    />
                    </div>
                <NotificationContainer />
            </div>
        );
    }
        
}


