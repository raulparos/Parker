import React, { Component } from 'react';
import { ScrollView, View, Button } from 'react-native';
import { Card, FormLabel } from 'react-native-elements';
import DatePicker from 'react-native-datepicker';

import styles from '../styles/AddReservationScreenStyle';
import getServerUrl from "../util/ServerUrl";

export default class AddReservationScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            startTime: this.props.navigation.state.params.filter.startTime,
            endTime: this.props.navigation.state.params.filter.endTime,
        };
    }

    static navigationOptions = {
        title: 'Add new reservation',
        headerStyle: {
            backgroundColor: '#04BEA6',
        },
        headerTintColor: '#fff',
    };

    addNewReservationCall = (data) => {
        fetch(getServerUrl() + '/reservation/add', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                parkingSpotId: data.parkingSpotId,
                date: data.date,
                startTime: data.startTime,
                endTime: data.endTime,
            }),
        }).then((response) => response.json())
            .then((responseData) => {
                console.log("Add parking spot response: ", responseData);
                if (responseData.successful === true) {
                    this.props.navigation.state.params.addReservationCallback();
                    this.props.navigation.goBack(null);
                }
            })
            .catch((error) => {
                console.error(error);
            });
    };

    addNewReservation = () => {
        let reservation = {
            parkingSpotId: this.props.navigation.state.params.parkingSpotId,
            date: this.props.navigation.state.params.filter.date,
            startTime: this.state.startTime,
            endTime: this.state.endTime,
        };

        console.log(reservation);

        this.addNewReservationCall(reservation);
    };

    render() {
        return (
            <ScrollView style={styles.container}>
                <Card title='RESERVATION DETAILS'>
                    <FormLabel>Date: {this.props.navigation.state.params.filter.date}</FormLabel>
                    <FormLabel>Interval: {this.props.navigation.state.params.freeInterval.startTime} - {this.props.navigation.state.params.freeInterval.endTime}</FormLabel>
                    <DatePicker
                        style={{width: 200, marginLeft: 20, marginTop: 10}}
                        date={this.state.startTime}
                        mode="time"
                        placeholder="Select start time"
                        confirmBtnText="Confirm"
                        cancelBtnText="Cancel"
                        customStyles={{
                            dateIcon: {
                                position: 'absolute',
                                left: 0,
                                top: 4,
                                marginLeft: 0
                            },
                            dateInput: {
                                marginLeft: 36
                            }
                        }}
                        onDateChange={(date) => {this.setState({ startTime: date })}}
                    />
                    <DatePicker
                        style={{width: 200, marginLeft: 20, marginTop: 10}}
                        date={this.state.endTime}
                        mode="time"
                        placeholder="Select end time"
                        confirmBtnText="Confirm"
                        cancelBtnText="Cancel"
                        customStyles={{
                            dateIcon: {
                                position: 'absolute',
                                left: 0,
                                top: 4,
                                marginLeft: 0
                            },
                            dateInput: {
                                marginLeft: 36
                            }
                        }}
                        onDateChange={(date) => {this.setState({ endTime: date })}}
                    />
                </Card>
                <View style={styles.buttonsContainer}>
                    <Button
                        style={styles.cancelAddParkingSpotButton}
                        color='#f44336'
                        title='GO BACK'
                        onPress={() => this.props.navigation.goBack(null)}
                    />
                    <Button
                        style={styles.addNewParkingSpotButton}
                        color='#04BEA6'
                        title='ADD PARKING SPOT'
                        onPress={() => this.addNewReservation()}
                    />
                </View>
            </ScrollView>
        );
    }
}
