import React, { Component } from 'react';
import { createDrawerNavigator, createStackNavigator } from 'react-navigation'

import MainAppScreen from './src/screens/MainAppScreen';
import AddParkingSpotScreen from "./src/screens/AddParkingSpotScreen";
import AddReservationScreen from "./src/screens/AddReservationScreen";
import MyParkingSpotsScreen from "./src/screens/MyParkingSpotsScreen";
import MyReservationsScreen from "./src/screens/MyReservationsScreen";

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
        MyParkingSpots: {
            path: '/MyParkingSpots',
            screen: MyParkingSpotsScreen,
        },
        MyReservations: {
            path: '/MyReservations',
            screen: MyReservationsScreen,
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