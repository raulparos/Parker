import React, { Component } from 'react';
import { ScrollView, View, Text, ActivityIndicator, TouchableOpacity } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';
import { Divider, FormLabel } from 'react-native-elements';

import styles from '../styles/MyParkingSpotsScreenStyle';
import getServerUrl from "../util/ServerUrl";

export default class MyParkingSpotsScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            parkingSpots: null,
        };
    }

    componentDidMount() {
        this.getUserParkingSpotsCall();
    }

    static navigationOptions = {
        drawerLabel: 'My parking spots',
        drawerIcon: () => {
            return (
                <MaterialIcon
                    name="local-parking"
                    size={30}
                    style={{color: '#04BEA6'}}
                >
                </MaterialIcon>
            );
        }
    };

    getUserParkingSpotsCall = () => {
        fetch(getServerUrl() + '/parking-spot/get-for-user', {
            method: 'GET',
            headers: {
                Accept: 'application/json',
            },
        }).then((response) => response.json())
            .then((responseData) => {
                console.log(responseData.data);
                this.setState({ parkingSpots: responseData.data });
            })
            .catch((error) => {
                console.error(error);
            });
    };

    deleteParkingSpot = (id) => {

    };

    renderParkingSpots = () => {
        if (this.state.parkingSpots === null) {
            return (
                <ActivityIndicator size='large' color='#04BEA6'/>
            );
        }
        else if (this.state.parkingSpots.length === 0){
            return (
                <FormLabel>You have no parking spots. But you can add one right now :)</FormLabel>
            );
        }
        else {
            return (
                this.state.parkingSpots.map((parkingSpot, index) => {
                    return(
                        <View
                            key={parkingSpot.id}
                        >
                            <View style={styles.parkingSpot}>
                                <Text>{index + 1}. </Text>
                                <Text>{parkingSpot.address}</Text>
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
                    <Text style={styles.title}>My parking spots</Text>
                    <View style={{width: 20}}></View>
                </View>

                <View style={styles.parkingSpotsWrapper}>{this.renderParkingSpots()}</View>
            </ScrollView>
        );
    }
}
