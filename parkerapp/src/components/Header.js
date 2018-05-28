import React, { Component } from 'react';
import { Text, View } from 'react-native';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';

import styles from '../styles/HeaderStyle';

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

