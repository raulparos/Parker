import React, { Component } from 'react';
import { createDrawerNavigator, createStackNavigator } from 'react-navigation'

import MainAppScreen from './src/screens/MainAppScreen';
import AddParkingSpotScreen from "./src/screens/AddParkingSpotScreen";
import AddReservationScreen from "./src/screens/AddReservationScreen";

const AddParkingSpot = createStackNavigator(
    {
        AddNewParkingSpot: {
            screen: AddParkingSpotScreen
        },

    }
);

const AddReservation = createStackNavigator(
    {
        AddNewReservation: {
            screen: AddReservationScreen
        },
    }
);

const MainMenu = createDrawerNavigator(
    {
        MainApp: {
            path: '/',
            screen: MainAppScreen,
        },
    },
    {
        initialRouteName: 'MainApp',
        drawerPosition: 'left'
    }
);

const RootMenu = createStackNavigator(
    {
        Main: {
            screen: MainMenu,
        },
        AddParkingSpot: {
            screen: AddParkingSpot,
        },
        AddReservation: {
            screen: AddReservation,
        },
    },
    {
        headerMode: 'none',
    }
);

export default class App extends Component {
    render() {
        return <RootMenu />;
    }
}