package com.parker.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class ParkingSpotActiveIntervalData implements Serializable {
    private DayOfWeek dayOfWeek;
    @JsonFormat(pattern = "hh:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "hh:mm")
    private LocalTime endTime;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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
