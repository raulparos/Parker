import React, { Component } from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';
import { MapView } from 'expo';

import Header from "../components/Header";
// import { getParkingSpotsInRadius } from "../service/ParkingSpotsService";

export default class MainAppScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            mapRegion: {
                latitude: 46.770439,
                longitude: 23.591423,
                latitudeDelta: 0.00900,
                longitudeDelta: 0.00700,
            },
            parkingSpotsNo: 0,
            parkingSpots: []
        };
        this.getParkingSpotsInRadius(46.770439, 23.591423);
    }

    getParkingSpotsInRadius = (latitude, longitude) => {
        fetch('http://192.168.100.6:8080/parking-spot/get-in-radius?latitude=' + latitude + '&longitude=' + longitude, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
            },
        }).then((response) => response.json())
            .then((responseData) => {
                console.log(responseData.data);
                this.setState({ parkingSpotsNo: responseData.data.length, parkingSpots: responseData.data });
            })
            .catch((error) => {
                console.error(error);
            });
    };

    onMapRegionChange = (region) => {
        this.setState({ mapRegion: region });
        console.log(region);
        this.getParkingSpotsInRadius(region.latitude, region.longitude);
    };

    static navigationOptions = {
        drawerLabel: 'Parker map',
        drawerIcon: () => {
            return (
                <MaterialIcon
                    name="menu"
                    size={30}
                    style={{color: '#04BEA6'}}
                >
                </MaterialIcon>
            );
        }
    };

    render() {
        return (
            <View style={styles.container}>
                <View style={styles.space} />
                <Header navigation={this.props.navigation} parkingSpotsNo={this.state.parkingSpotsNo}/>
                <MapView
                    style={{ flex: 1 }}
                    initialRegion={this.state.mapRegion}
                    onRegionChangeComplete={this.onMapRegionChange}
                >
                    {this.state.parkingSpots.map(parkingSpot => (
                        <MapView.Marker
                            key={parkingSpot.id}
                            coordinate={{latitude: parkingSpot.latitude, longitude: parkingSpot.longitude}}
                            title={'Parking spot'}
                            description={parkingSpot.address}
                        />
                    ))}
                </MapView>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column'
    },
    title: {
        fontSize: 30,
        color: 'green'
    },
    space: {
        backgroundColor: '#04BEA6',
        height: 24
    },
    map: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
    },
});