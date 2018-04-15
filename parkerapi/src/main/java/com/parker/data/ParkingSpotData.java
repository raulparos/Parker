package com.parker.data;

import java.util.List;

public class ParkingSpotData {
    private Long id;
    private Float latitude;
    private Float longitude;
    private String address;
    private Long userId;
    private List<ParkingSpotActiveIntervalData> activeDaysIntervals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ParkingSpotActiveIntervalData> getActiveDaysIntervals() {
        return activeDaysIntervals;
    }

    public void setActiveDaysIntervals(List<ParkingSpotActiveIntervalData> activeDaysIntervals) {
        this.activeDaysIntervals = activeDaysIntervals;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
