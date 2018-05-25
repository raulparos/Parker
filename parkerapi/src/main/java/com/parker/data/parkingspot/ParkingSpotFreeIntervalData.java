package com.parker.data.parkingspot;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalTime;

public class ParkingSpotFreeIntervalData implements Serializable {
    @JsonFormat(pattern = "hh:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "hh:mm")
    private LocalTime endTime;

    public ParkingSpotFreeIntervalData() {

    }

    public ParkingSpotFreeIntervalData(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
