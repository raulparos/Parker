import React, { Component } from 'react';
import { ScrollView, View, Text, ActivityIndicator } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';
import {Location, MapView, Permissions} from 'expo';
import { FormLabel, Divider } from 'react-native-elements';

import Header from '../components/Header';
import displayMessage from '../util/DisplayMessage';
import geoCodeLatLng from '../util/Geocoder';
import getServerUrl from '../util/ServerUrl';
import styles from '../styles/MainAppScreenStyle';

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
            markerDetailsId: null,
            dayFreeIntervals: null,
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

    getParkingSpotFreeIntervalsCall = (id, date) => {
        fetch(getServerUrl() + '/parking-spot/' + id + '/get-free-intervals' + '?date=' + date, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
            },
        }).then((response) => response.json())
            .then((responseData) => {
                console.log(responseData.data);
                this.setState({ dayFreeIntervals: responseData.data });
            })
            .catch((error) => {
                console.error(error);
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
                console.log("Free intervals response received");
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

    getParkingSpotById = (id) => {
        let idLong = parseInt(id);
        let parkingSpots = this.state.parkingSpots;
        for (var index = 0; index < parkingSpots.length; index++) {
            if (parkingSpots[index].id === idLong) {
                return parkingSpots[index];
            }
        }

        return null;
    };

    renderParkingSpotFreeIntervals = () => {
        if (this.state.dayFreeIntervals == null) {
            return(
                <ActivityIndicator size='large' color='#04BEA6'/>
            );
        }
        else {
            if (this.state.dayFreeIntervals.length === 0) {
                return (
                    <View>
                        <FormLabel>No free intervals</FormLabel>
                    </View>
                );
            }
            else {
                return (
                    <View>
                        {
                            this.state.dayFreeIntervals.map((freeInterval) => {
                                return (
                                    <View key={freeInterval.startTime} style={styles.freeIntervals}>
                                        <Text style={styles.freeIntervalsText}>{freeInterval.startTime} - {freeInterval.endTime}</Text>
                                    </View>
                                );
                            })
                        }
                    </View>
                );
            }
        }
    };

    renderParkingSpotDetailsComponent = () => {
        if (this.state.markerDetailsId == null) {
            return null;
        }
        else {
            let parkingSpot = this.getParkingSpotById(this.state.markerDetailsId);
            if (parkingSpot == null) {
                this.setState({ markerDetailsId: null });
                return null;
            }

            if (this.state.dayFreeIntervals == null) {
                this.getParkingSpotFreeIntervalsCall(this.state.markerDetailsId, '2018-05-27');
            }
            return(
                <View style={styles.parkingSpotDetailsCard}>
                    <MaterialIcon
                        name='close'
                        size={30}
                        style={styles.parkingSpotDetailsCardClose}
                        onPress={() => this.setState({ markerDetailsId: null, dayFreeIntervals: null })}
                    />
                    <Text style={styles.parkingSPotDetailsCardTitle}>PARKING SPOT DETAILS</Text>
                    <Divider style={{backgroundColor: '#cccccc', marginBottom: 10}}/>
                    <ScrollView style={styles.parkingSpotDetailsContainer}>
                        <FormLabel>Address</FormLabel>
                        <Divider style={{backgroundColor: '#cccccc', marginLeft: 20, marginRight: 20}}/>
                        <FormLabel>{parkingSpot.address}</FormLabel>
                        <FormLabel>Schedule</FormLabel>
                        <Divider style={{backgroundColor: '#cccccc', marginLeft: 20, marginRight: 20}}/>
                        {
                            parkingSpot.activeDaysIntervals.map((activeInterval) => {
                                return (
                                    <View key={activeInterval.dayOfWeek}>
                                        <FormLabel>{activeInterval.dayOfWeek} | from: {activeInterval.startTime} to: {activeInterval.endTime}</FormLabel>
                                    </View>
                                );
                            })
                        }

                        <FormLabel>Today free intervals</FormLabel>
                        <Divider style={{backgroundColor: '#cccccc', marginLeft: 20, marginRight: 20}}/>
                        <View>{this.renderParkingSpotFreeIntervals()}</View>
                    </ScrollView>
                </View>
            );
        }
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

    showMarkerInfo = (event) => {
        let idString = event.nativeEvent.id;
        if (idString != null) {
            let tokens = idString.split(":");
            let id = tokens[1];
            this.setState({ markerDetailsId: id });

            console.log("Parking spot marker with id: " + id + " fired");
        }
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
                    onMarkerPress={(e) => this.showMarkerInfo(e)}
                >
                    {this.state.parkingSpots.map(parkingSpot => (
                        <MapView.Marker
                            key={parkingSpot.id}
                            identifier={"ID:" + parkingSpot.id}
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
                <View style={styles.parkingSpotDetailsWrapper}>{this.renderParkingSpotDetailsComponent()}</View>
            </View>
        );
    }
}
