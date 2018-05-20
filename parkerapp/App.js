import React, { Component } from 'react';
import { createDrawerNavigator, createStackNavigator } from 'react-navigation'

import MainAppScreen from './src/screens/MainAppScreen';
import AddParkingSpotScreen from "./src/screens/AddParkingSpotScreen";

const AddNewParkingSpot = createStackNavigator(
    {
        AddNewParkingSpot: {
            screen: AddParkingSpotScreen
        }
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
            screen: AddNewParkingSpot,
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