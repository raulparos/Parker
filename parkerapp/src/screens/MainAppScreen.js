import React, { Component } from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';
import {Location, MapView, Permissions} from 'expo';
import { createStackNavigator } from 'react-navigation';

import Header from '../components/Header';
import displayMessage from '../util/DisplayMessage';
import geoCodeLatLng from '../util/Geocoder';
import getServerUrl from '../util/ServerUrl';
import AddParkingSpotScreen from "../screens/AddParkingSpotScreen";

const AddParkingSpot = createStackNavigator(
    {
        AddParkingSpot: { screen: AddParkingSpotScreen },
    }
);

export default class MainAppScreen extends Component {
    static navigationOptions = {
        drawerLabel: 'Parker map',
        drawerIcon: () => {
            return (
                <MaterialIcon
                    name="map"
                    size={30}
                    style={{color: '#04BEA6'}}
                >
                </MaterialIcon>
            );
        }
    };

    constructor(props) {
        super(props);
        this.state = {
            mapRegion: {
                latitude: 46.770439,
                longitude: 23.591423,
                latitudeDelta: 0.00700,
                longitudeDelta: 0.00300,
            },
            parkingSpotsNo: 0,
            parkingSpots: [],
            newParkingSpot: [],
            geoCodedAddress: "Geocode service could not determine address",
        };
        this.getParkingSpotsInRadiusCall(46.770439, 23.591423);
    }

    componentDidMount() {
        this.getUserLocation();
    }

    getLocationAsync = async () => {
        let { status } = await Permissions.askAsync(Permissions.LOCATION);
        if (status !== 'granted') {
            console.log("LOCATION ERROR!");
        }

        console.log("Getting user location async...");
        return await Location.getCurrentPositionAsync({});
    };

    getUserLocation = () => {
        this.getLocationAsync()
            .then((location) => {
                console.log("Got user location async...");
                this.setState({ mapRegion: { latitude: location.coords.latitude, longitude: location.coords.longitude,
                        latitudeDelta: 0.00700, longitudeDelta: 0.00300, } });
                this.mapView.animateToRegion(this.state.mapRegion, 10);
            })
            .catch((error) => {
                displayMessage("Location error", error.message + "\nPlease enable location and click the location icon again");
            });
    };

    getParkingSpotsInRadiusCall = (latitude, longitude) => {
        fetch(getServerUrl() + '/parking-spot/get-in-radius?latitude=' + latitude + '&longitude=' + longitude, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
            },
        }).then((response) => response.json())
            .then((responseData) => {
                // console.log(responseData.data);
                this.setState({ parkingSpotsNo: responseData.data.length, parkingSpots: responseData.data });
            })
            .catch((error) => {
                console.error(error);
            });
    };

    onMapRegionChange = (region) => {
        this.setState({ mapRegion: region });
        if (this.state.newParkingSpot.length === 1) {
            this.setState({ newParkingSpot: [{ latitude: region.latitude, longitude: region.longitude }] });
            console.log("Parking spot latitude: ", this.state.newParkingSpot[0].latitude);
            geoCodeLatLng(region.latitude, region.longitude, this.geoCodeLatLngCallback);
        }
        this.getParkingSpotsInRadiusCall(region.latitude, region.longitude);
    };

    geoCodeLatLngCallback = (address) => {
        this.setState({ geoCodedAddress: address });
    };

    addNewParkingSpotMarker = () => {
        this.setState({ newParkingSpot: [{ latitude: this.state.mapRegion.latitude, longitude: this.state.mapRegion.longitude }] });
        geoCodeLatLng(this.state.mapRegion.latitude, this.state.mapRegion.longitude, this.geoCodeLatLngCallback);
    };

    cancelAddNewParkingSpot = () => {
        this.setState({ newParkingSpot: [] });
        this.setState({ geoCodedAddress: null });
    };

    renderAddParkingSpotComponent = () => {
        if (this.state.newParkingSpot.length === 0) {
            return (
                <View style={styles.addParkingSpotContainer}>
                    <MaterialIcon
                        name="add-location"
                        size={40}
                        style={styles.addParkingSpotButton}
                        onPress={() => this.addNewParkingSpotMarker()}
                    />
                </View>
            );
        }
        else if (this.state.newParkingSpot.length === 1){
            return (
                <View style={styles.addNewParkingSpotLocationWrapper}>
                    <View style={styles.cancelParkingSpotLocationCancelContainer}>
                        <MaterialIcon
                            name="close"
                            size={36}
                            onPress={() => this.cancelAddNewParkingSpot()}
                            style={styles.cancelParkingSpotLocationCancel}
                        />
                    </View>
                    <View style={styles.setParkingSpotLocationContainer}>
                        <MaterialIcon
                            name="navigate-next"
                            size={40}
                            onPress={() => this.showAddParkingSpotScreen()}
                            style={styles.setParkingSpotLocationNext}
                        />
                    </View>
                </View>
            );
        }
    };

    showAddParkingSpotScreen = () => {
        console.log("Showing add parking spot screen");
        this.props.navigation.navigate('AddNewParkingSpot', { newParkingSpot: this.state.newParkingSpot[0], address: this.state.geoCodedAddress, addParkingSpotCallback: this.addParkingSpotCallback });
    };

    addParkingSpotCallback = () => {
        console.log("Callback came back from add parking spot");
        this.cancelAddNewParkingSpot();
    };

    render() {
        return (
            <View style={styles.container}>
                <Header navigation={this.props.navigation} parkingSpotsNo={this.state.parkingSpotsNo} getUserLocation={this.getUserLocation}/>
                <MapView
                    style={{ flex: 1 }}
                    initialRegion={this.state.mapRegion}
                    onRegionChangeComplete={this.onMapRegionChange}
                    showUserLocation={true}
                    ref={ref => { this.mapView=ref; }}
                >
                    {this.state.parkingSpots.map(parkingSpot => (
                        <MapView.Marker
                            key={parkingSpot.id}
                            coordinate={{latitude: parkingSpot.latitude, longitude: parkingSpot.longitude}}
                            image={require('../assets/parking_spot_marker2.png')}
                        />
                    ))}
                    {this.state.newParkingSpot.map(newParkingSpot => (
                        <MapView.Marker
                            draggable={true}
                            key={1}
                            title={"Your parking spot"}
                            description={this.state.geoCodedAddress}
                            coordinate={{ latitude: newParkingSpot.latitude, longitude: newParkingSpot.longitude }}
                            image={require('../assets/parking_spot_marker.png')}
                        />
                    ))}
                </MapView>
                <View>{this.renderAddParkingSpotComponent()}</View>
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
    map: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
    },
    addParkingSpotButton: {
        color: '#ffffff',
    },
    addParkingSpotContainer: {
        backgroundColor: '#04BEA6',
        position: 'absolute',
        bottom: 20,
        right: 20,
        padding: 5,
        borderRadius: 30,
        borderWidth: 1,
        borderColor: '#03a38e',
    },
    addNewParkingSpotLocationWrapper: {
        position: 'absolute',
        bottom: 10,
        right: 10,
        left: 10,
        zIndex: 9999,
        flexDirection: 'row',
        justifyContent: 'space-between',
    },
    setParkingSpotLocationContainer: {
        backgroundColor: '#04BEA6',
        borderRadius: 30,
        borderWidth: 1,
        borderColor: '#03a38e',
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 10,
        marginLeft: 60,
    },
    setParkingSpotLocationNext: {
        color: '#ffffff',
        padding: 5
    },
    cancelParkingSpotLocationCancelContainer: {
        backgroundColor: '#f44336',
        borderRadius: 30,
        borderWidth: 1,
        borderColor: '#d81c0f',
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 10,
        marginRight: 60,
    },
    cancelParkingSpotLocationCancel: {
        color: '#ffffff',
        padding: 5
    }
});