package com.parker.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String licensePlate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ParkingSpot> parkingSpots;
}
