import React, {StyleSheet} from 'react-native';

const styles = StyleSheet.create({
    space: {
        backgroundColor: '#04BEA6',
        height: 24
    },
    container: {
        backgroundColor: '#fff',
    },
    parkingSpotsWrapper: {
        margin: 10,
    },
    parkingSpot: {
        flexDirection: 'row',
        paddingTop: 5,
        paddingLeft: 10,
        paddingRight: 10,
        paddingBottom: 5,
        alignItems: 'center',
    },
    title: {
        fontSize: 18,
        fontWeight: 'bold',
        color: '#04BEA6',
        textAlign: 'center',
    },
    deleteButton: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        backgroundColor: '#f44336',
        padding: 5,
        paddingLeft: 10,
        paddingRight: 10,
        borderRadius: 15,
        margin:  10,
    },
    deleteButtonText: {
        color: '#ffffff',
        fontWeight: 'bold',
    },
    header: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        margin: 10,
    },
});

export default styles;