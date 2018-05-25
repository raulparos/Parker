package com.parker.data.parkingspot;

import java.io.Serializable;
import java.util.List;

public class ParkingSpotData implements Serializable {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String address;
    private Long userId;
    private List<ParkingSpotActiveIntervalData> activeDaysIntervals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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
