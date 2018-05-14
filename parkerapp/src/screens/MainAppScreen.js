import React, { Component } from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';
import { MapView } from 'expo';

import Header from "../components/Header";
import { getParkingSpotsInRadius } from "../service/ParkingSpotsService";

export default class MainAppScreen extends Component {
    constructor(props) {
        super(props);
        getParkingSpotsInRadius(46.770439, 23.591423);
    }

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
                <Header navigation={this.props.navigation}/>
                <MapView
                    style={{ flex: 1 }}
                    initialRegion={{
                        latitude: 46.770439,
                        longitude: 23.591423,
                        latitudeDelta: 0.0922,
                        longitudeDelta: 0.0421,
                    }}
                />
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