import React from 'react';

export function getParkingSpotsInRadius(latitude, longitude) {
    fetch('http://192.168.100.6:8080/parking-spot/get-in-radius?latitude=' + latitude + '&longitude=' + longitude, {
        method: 'GET',
        headers: {
            Accept: 'application/json',
        },
    }).then((response) => response.json())
        .then((responseData) => {
            console.log(responseData.data);
            return responseData.data;
        })
    .catch((error) => {
        console.error(error);
    });
}