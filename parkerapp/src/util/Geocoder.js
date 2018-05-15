import React from 'react';

export default geocodeLatLng = (latitude, longitude, callback) => {
    console.log("Geocoding... lat: " + latitude + ", lng: " + longitude);
    fetch('https://maps.googleapis.com/maps/api/geocode/json?address=' + latitude + ',' + longitude + '&key=AIzaSyCBEiuVgIIkgGto_PMV_xLHHrTK4XLnBe8')
        .then((response) => response.json())
        .then((responseJson) => {
            if (responseJson.results.length !== 0) {
                console.log("Address response: ", responseJson.results[0].formatted_address);
                callback(responseJson.results[0].formatted_address);
            }
            else {
                callback("Geocode service could not determine location");
            }
        })
        .catch((error) => {
            callback("Geocode service could not determine location");
        })
}