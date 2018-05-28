import React, {StyleSheet} from 'react-native';

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

export default styles;