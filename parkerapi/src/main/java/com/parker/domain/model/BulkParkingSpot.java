package com.parker.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bulkParkingSpot")
public class BulkParkingSpot extends ParkingSpot {
    @Column(nullable = false)
    private Integer numberOfSpots;

    public Integer getNumberOfSpots() {
        return numberOfSpots;
    }

    public void setNumberOfSpots(Integer numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }
}
