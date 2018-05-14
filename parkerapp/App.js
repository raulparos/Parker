import React from 'react';
import { createDrawerNavigator } from 'react-navigation'

import MainAppScreen from './src/screens/MainAppScreen';

const MainMenu = createDrawerNavigator(
    {
        MainApp: {
            path: '/',
            screen: MainAppScreen,
        }
    },
    {
        initialRouteName: 'MainApp',
        drawerPosition: 'left'
    }
);

export default MainMenu;