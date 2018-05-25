package com.parker.data.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;
import java.util.Date;

public class ReservationData {
    private Long id;
    private Long parkingSpotId;
    private Date date;

    @JsonFormat(pattern = "hh:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "hh:mm")
    private LocalTime endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(Long parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
