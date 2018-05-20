import React, { Component } from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';

export default class Header extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <View style={styles.wrapper}>
                <View style={styles.space} />
                <View style={styles.container}>
                    <MaterialIcon
                        name="menu"
                        size={28}
                        style={{color: '#04BEA6'}}
                        onPress={() => this.props.navigation.toggleDrawer()}
                    />
                    <View style={styles.nameContainer}>
                        <Text
                            style={styles.appName}
                        >
                            Parker
                        </Text>
                        <Text
                            style={styles.parkingSpotsNo}
                        >
                            ( {this.props.parkingSpotsNo} spots near you )
                        </Text>
                    </View>
                    <MaterialIcon
                        name="my-location"
                        size={28}
                        style={{color: '#04BEA6'}}
                        onPress={() => this.props.getUserLocation()}
                    />
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    wrapper: {
        backgroundColor: '#ffffff'
    },
    container: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        paddingTop: 5,
        paddingLeft: 10,
        paddingRight: 10,
        paddingBottom: 5,
    },
    space: {
        backgroundColor: '#04BEA6',
        height: 24
    },
    appName: {
        fontSize: 24,
        fontWeight: 'bold',
        color: '#04BEA6'
    },
    parkingSpotsNo: {
        fontSize: 12,
    },
    nameContainer: {
        justifyContent: 'center',
        alignItems: 'center',
    },
});