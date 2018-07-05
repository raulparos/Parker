import React, { Component } from 'react';
import { Text, View, Button, ScrollView, Picker } from 'react-native';
import { FormLabel, Card } from 'react-native-elements';
import MaterialIcon from 'react-native-vector-icons/MaterialIcons';
import DatePicker from 'react-native-datepicker';

import getServerUrl from '../util/ServerUrl';
import styles from '../styles/AddParkingSpotScreenStyle';

const mondayFridayDaysSpan = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday'];
const weekendDaysSpan = ['saturday', 'sunday'];

const daysMap = {
    "monday-friday": "MONDAY-FRIDAY",
    "weekend": "WEEKEND",
    "monday": "MONDAY",
    "tuesday": "TUESDAY",
    "wednesday": "WEDNESDAY",
    "thursday": "THURSDAY",
    "friday": "FRIDAY",
    "saturday": "SATURDAY",
    "sunday": "SUNDAY",
};

export default class AddParkingSpotScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pickerSelectedValue: 'monday-friday',
            fromTime: null,
            toTime: null,
            schedule: []
        };
    }

    static navigationOptions = {
        title: 'Add new parking spot',
        headerStyle: {
            backgroundColor: '#04BEA6',
        },
        headerTintColor: '#fff',
    };

    addScheduleEntryToSchedule = (entry, schedule) => {
        let index = this.getScheduleEntryIndexForKey(entry.key);
        if (index === -1) {
            schedule.push(entry);
        }
        else {
            schedule.splice(index, 1, entry);
        }
    };

    addScheduleEntry = () => {
        let schedule = this.state.schedule;
        let key = this.state.pickerSelectedValue;
        let label = daysMap[key];

        switch(key) {
            case 'monday-friday':
                for (var index = 0; index < mondayFridayDaysSpan.length; index++) {
                    let key = mondayFridayDaysSpan[index];
                    let label = daysMap[key];
                    let scheduleEntry = {
                        key: key,
                        label: label,
                        fromTime: this.state.fromTime,
                        toTime: this.state.toTime
                    };
                    this.addScheduleEntryToSchedule(scheduleEntry, schedule);
                }

                break;
            case 'weekend':
                for (var index = 0; index < weekendDaysSpan.length; index++) {
                    let key = weekendDaysSpan[index];
                    let label = daysMap[key];
                    let scheduleEntry = {
                        key: key,
                        label: label,
                        fromTime: this.state.fromTime,
                        toTime: this.state.toTime
                    };
                    this.addScheduleEntryToSchedule(scheduleEntry, schedule);
                }

                break;
            default:
                let scheduleEntry = {
                    key: key,
                    label: label,
                    fromTime: this.state.fromTime,
                    toTime: this.state.toTime
                };
                this.addScheduleEntryToSchedule(scheduleEntry, schedule);

                break;
        }

        this.setState({
            pickerSelectedValue: 'monday-friday',
            schedule: schedule,
        });
    };

    getScheduleEntryIndexForKey = (key) => {
        let schedule = this.state.schedule;
        for (var index = 0; index < schedule.length; index++) {
            if (schedule[index].key === key) {
                return index;
            }
        }

        return -1;
    };

    removeScheduleEntry = (key) => {
        let index = this.getScheduleEntryIndexForKey(key);
        let schedule = this.state.schedule;
        if (index > -1) {
            schedule.splice(index, 1);
        }

        this.setState({ schedule: schedule });
    };

    createScheduleData = (schedule) => {
        let scheduleData = [];
        for (var index = 0; index < schedule.length; index++) {
            console.log("To time: " + schedule[index].toTime);
            let scheduleEntryData = {
                dayOfWeek: daysMap[schedule[index].key],
                startTime: schedule[index].fromTime,
                endTime: schedule[index].toTime
            };
            scheduleData.push(scheduleEntryData);
        }

        return scheduleData;
    };

    addNewParkingSpot = () => {
        let address = this.props.navigation.state.params.address;
        let latitude = this.props.navigation.state.params.newParkingSpot.latitude;
        let longitude = this.props.navigation.state.params.newParkingSpot.longitude;

        let scheduleData = this.createScheduleData(this.state.schedule);

        let parkingSpot = {
            latitude: latitude,
            longitude: longitude,
            address: address,
            activeDaysIntervals: scheduleData,
            id: null,
            userId: null
        };

        console.log("new parking spot: ", JSON.stringify({parkingSpot}));
        this.addNewParkingSpotCall(parkingSpot);
    };

    addNewParkingSpotCall = (data) => {
        fetch(getServerUrl() + '/parking-spot/add', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: data.id,
                userId: data.userId,
                latitude: data.latitude,
                longitude: data.longitude,
                address: data.address,
                activeDaysIntervals: data.activeDaysIntervals
            }),
        }).then((response) => response.json())
            .then((responseData) => {
                console.log("Add parking spot response: ", responseData);
                if (responseData.successful === true) {
                    this.props.navigation.state.params.addParkingSpotCallback();
                    this.props.navigation.goBack(null);
                }
            })
            .catch((error) => {
                console.error(error);
            });
    };

    renderSchedule = () => {
        return (
            <View style={styles.scheduleContainer}>
                <Card title="ADDRESS">
                    <Text style={styles.address}>{this.props.navigation.state.params.address}</Text>
                </Card>
                <FormLabel>We got the address from the map marker you set up, if you think it's not correct, please go back and move the marker</FormLabel>
                <FormLabel>
                    You can set the parking spot schedule per periods: Monday-Friday and Weekend or per each day a different schedule.
                    You can set the schedule for Monday-Friday, add it and then set the
                </FormLabel>

                <Card title="CURRENT SCHEDULE FOR PARKING SPOT">
                    {
                        this.state.schedule.map((scheduleEntry) => {
                            return (
                                <View key={scheduleEntry.key} style={styles.scheduleEntry}>
                                    <FormLabel>{scheduleEntry.label} | from: {scheduleEntry.fromTime} to: {scheduleEntry.toTime}</FormLabel>
                                    <MaterialIcon
                                        name="remove-circle"
                                        size={30}
                                        onPress={() => this.removeScheduleEntry(scheduleEntry.key)}
                                        style={{color: '#f44336'}}
                                    />
                                </View>
                            );
                        })
                    }
                </Card>

                <Card title="ADD SCHEDULE ENTRY">
                    <Picker
                        selectedValue={this.state.pickerSelectedValue}
                        onValueChange={(itemValue, itemIndex) => this.setState({ pickerSelectedValue: itemValue })}
                    >
                        <Picker.Item label="Monday-Friday" value="monday-friday" />
                        <Picker.Item label="Weekend" value="weekend" />
                        <Picker.Item label="Monday" value="monday" />
                        <Picker.Item label="Tuesday" value="tuesday" />
                        <Picker.Item label="Wednesday" value="wednesday" />
                        <Picker.Item label="Thursday" value="thursday" />
                        <Picker.Item label="Friday" value="friday" />
                        <Picker.Item label="Saturday" value="saturday" />
                        <Picker.Item label="Sunday" value="sunday" />
                    </Picker>
                    <DatePicker
                        style={{width: 200, marginLeft: 20, marginTop: 10}}
                        date={this.state.fromTime}
                        mode="time"
                        placeholder="Select start time"
                        confirmBtnText="Confirm"
                        cancelBtnText="Cancel"
                        customStyles={{
                            dateIcon: {
                                position: 'absolute',
                                left: 0,
                                top: 4,
                                marginLeft: 0
                            },
                            dateInput: {
                                marginLeft: 36
                            }
                        }}
                        onDateChange={(date) => {this.setState({fromTime: date})}}
                    />
                    <DatePicker
                        style={{width: 200, marginLeft: 20, marginTop: 10}}
                        date={this.state.toTime}
                        mode="time"
                        placeholder="Select end time"
                        confirmBtnText="Confirm"
                        cancelBtnText="Cancel"
                        customStyles={{
                            dateIcon: {
                                position: 'absolute',
                                left: 0,
                                top: 4,
                                marginLeft: 0
                            },
                            dateInput: {
                                marginLeft: 36
                            }
                        }}
                        onDateChange={(date) => {this.setState({toTime: date})}}
                    />
                    <MaterialIcon
                        name="add-box"
                        size={40}
                        onPress={() => this.addScheduleEntry()}
                        style={styles.addScheduleEntry}
                    />
                </Card>
            </View>
        );
    };

    render() {
        return (
            <ScrollView style={styles.container}>
                <View>{this.renderSchedule()}</View>
                <View style={styles.buttonsContainer}>
                    <Button
                        style={styles.cancelAddParkingSpotButton}
                        color='#f44336'
                        title='GO BACK'
                        onPress={() => this.props.navigation.goBack(null)}
                    />
                    <Button
                        style={styles.addNewParkingSpotButton}
                        color='#04BEA6'
                        title='ADD PARKING SPOT'
                        onPress={() => this.addNewParkingSpot()}
                    />
                </View>
            </ScrollView>
        );
    }
}