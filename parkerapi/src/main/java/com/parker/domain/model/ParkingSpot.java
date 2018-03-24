package com.parker.domain.model;

import javax.persistence.*;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Float latitude;

    @Column(nullable = false)
    private Float longitude;

    @ManyToOne
    private User user;


}
