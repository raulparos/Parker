import React, {StyleSheet} from 'react-native';

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column'
    },
    title: {
        fontSize: 30,
        color: 'green'
    },
    map: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
    },
    addParkingSpotButton: {
        color: '#ffffff',
    },
    addParkingSpotContainer: {
        backgroundColor: '#04BEA6',
        position: 'absolute',
        bottom: 20,
        right: 20,
        padding: 5,
        borderRadius: 30,
        borderWidth: 1,
        borderColor: '#03a38e',
    },
    addNewParkingSpotLocationWrapper: {
        position: 'absolute',
        bottom: 10,
        right: 10,
        left: 10,
        zIndex: 9999,
        flexDirection: 'row',
        justifyContent: 'space-between',
    },
    setParkingSpotLocationContainer: {
        backgroundColor: '#04BEA6',
        borderRadius: 30,
        borderWidth: 1,
        borderColor: '#03a38e',
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 10,
        marginLeft: 60,
    },
    setParkingSpotLocationNext: {
        color: '#ffffff',
        padding: 5
    },
    cancelParkingSpotLocationCancelContainer: {
        backgroundColor: '#f44336',
        borderRadius: 30,
        borderWidth: 1,
        borderColor: '#d81c0f',
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 10,
        marginRight: 60,
    },
    cancelParkingSpotLocationCancel: {
        color: '#ffffff',
        padding: 5
    },
    parkingSpotDetailsWrapper: {
        position: 'absolute',
        zIndex: 9998,
        top: 95,
        left: 10,
        right: 10,
    },
    parkingSpotDetailsCard: {
        backgroundColor: '#ffffff',
        height: 300,
        margin: 5,
        padding: 15,
        borderRadius: 5,
    },
    parkingSPotDetailsCardTitle: {
        fontSize: 16,
        fontWeight: 'bold',
        color: '#04BEA6',
        textAlign: 'center',
        marginTop: 15,
        marginBottom: 5,
    },
    parkingSpotDetailsContainer: {
    },
    parkingSpotDetailsCardClose: {
        position: 'absolute',
        right: 5,
        top: 5,
        backgroundColor: '#f44336',
        color: '#ffffff',
        borderRadius: 5,
    },
    freeIntervals: {
        backgroundColor: '#ffffff',
        borderColor: '#04BEA6',
        borderWidth: 1,
        borderRadius: 15,
        padding: 5,
        marginLeft: 20,
        marginRight: 20,
        width: 150,
        marginTop: 10,
    },
    freeIntervalsText: {
        color: '#04BEA6',
        fontSize: 14,
        textAlign: 'center',
    }
});

export default styles;