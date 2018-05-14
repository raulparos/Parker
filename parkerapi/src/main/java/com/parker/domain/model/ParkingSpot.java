package com.parker.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "parkingSpot")
public class ParkingSpot {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String activeDaysIntervals;

    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActiveDaysIntervals() {
        return activeDaysIntervals;
    }

    public void setActiveDaysIntervals(String activeDaysIntervals) {
        this.activeDaysIntervals = activeDaysIntervals;
    }
}
