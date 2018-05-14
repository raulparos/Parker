import React from 'react';
import { Alert } from 'react-native';

function displayMessage(title, message) {
    //todo: Maybe find a better way to display errors/messages
    Alert.alert(title, message);
}

export default displayMessage;