package com.parker.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private ParkingSpot parkingSpot;

    @Column(name = "availability_interval_start")
    private Date availabilityIntervalStart;

    @Column(name = "availability_interval_end")
    private Date availabilityIntervalEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAvailabilityIntervalStart() {
        return availabilityIntervalStart;
    }

    public void setAvailabilityIntervalStart(Date availabilityIntervalStart) {
        this.availabilityIntervalStart = availabilityIntervalStart;
    }

    public Date getAvailabilityIntervalEnd() {
        return availabilityIntervalEnd;
    }

    public void setAvailabilityIntervalEnd(Date availabilityIntervalEnd) {
        this.availabilityIntervalEnd = availabilityIntervalEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
