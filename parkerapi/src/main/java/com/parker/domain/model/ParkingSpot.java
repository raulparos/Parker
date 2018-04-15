package com.parker.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "parkingSpot")
public class ParkingSpot {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Float latitude;

    @Column(nullable = false)
    private Float longitude;

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
