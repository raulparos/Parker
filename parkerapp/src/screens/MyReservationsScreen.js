import React, { Component } from 'react';
import { ScrollView, View, ActivityIndicator, Text, TouchableOpacity } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';
import { FormLabel, Divider } from 'react-native-elements';

import styles from '../styles/MyReservationScreenStyle';
import getServerUrl from "../util/ServerUrl";

export default class MyReservationsScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            reservations: null,
            dataSource: null,
        };
    }

    componentDidMount() {
        this.getUserReservationsCall();
    }

    static navigationOptions = {
        drawerLabel: 'My reservations',
        drawerIcon: () => {
            return (
                <MaterialIcon
                    name="book"
                    size={30}
                    style={{color: '#04BEA6'}}
                >
                </MaterialIcon>
            );
        }
    };

    getUserReservationsCall = () => {
        fetch(getServerUrl() + '/reservation/get-for-user', {
            method: 'GET',
            headers: {
                Accept: 'application/json',
            },
        }).then((response) => response.json())
            .then((responseData) => {
                console.log(responseData.data);
                this.setState({ reservations: responseData.data });
            })
            .catch((error) => {
                console.error(error);
            });
    };

    cancelReservationCall = (id) => {
        fetch(getServerUrl() + '/reservation/' + id + '/delete', {
            method: 'DELETE',
            headers: {
                Accept: 'application/json',
            },
        }).then((response) => response.json())
            .then((responseData) => {
                if (responseData.successful === true) {
                    this.getUserReservationsCall();
                }
            })
            .catch((error) => {
                console.error(error);
            });
    };

    renderReservations = () => {
        if (this.state.reservations === null) {
            return (
                <ActivityIndicator size='large' color='#04BEA6'/>
            );
        }
        else if (this.state.reservations.length === 0){
            return (
                <FormLabel>You have no reservations. But you can make some right now :)</FormLabel>
            );
        }
        else {
            return (
                this.state.reservations.map((reservation, index) => {
                    return(
                        <View
                            key={reservation.id}
                        >
                            <View style={styles.reservation}>
                                <Text>{index + 1}.</Text>
                                <Text>{reservation.date.substring(0, 10)} | {reservation.startTime} - {reservation.endTime}</Text>
                                <TouchableOpacity
                                    style={styles.deleteButton}
                                    onPress={() => this.cancelReservationCall(reservation.id)}
                                >
                                    <Text style={styles.deleteButtonText}>CANCEL</Text>
                                    <MaterialIcon
                                        name="close"
                                        size={25}
                                        style={{color: '#ffffff'}}
                                    />
                                </TouchableOpacity>
                            </View>
                            <Divider style={{backgroundColor: '#cccccc', marginBottom: 10}}/>
                        </View>
                    );
                })
            );
        }
    };

    render() {
        return (
            <ScrollView style={styles.container}>
                <View style={styles.space} />
                <View style={styles.header}>
                    <MaterialIcon
                        name="menu"
                        size={28}
                        style={{color: '#04BEA6'}}
                        onPress={() => this.props.navigation.toggleDrawer()}
                    />
                    <Text style={styles.title}>My reservations</Text>
                    <View style={{width: 20}}></View>
                </View>

                <View style={styles.reservationsWrapper}>{this.renderReservations()}</View>
            </ScrollView>
        );
    }
}
