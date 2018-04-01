package com.parker.converter.impl;

import com.parker.converter.AbstractConverter;
import com.parker.converter.populator.Populator;
import com.parker.data.ParkingSpotData;
import com.parker.domain.model.ParkingSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class ParkingSpotConverter extends AbstractConverter<ParkingSpotData, ParkingSpot> {

    @Autowired
    private Populator<ParkingSpotData, ParkingSpot> parkingSpotPopulator;

    public ParkingSpotConverter() {
        super();
        super.populators = Collections.unmodifiableList(Arrays.asList(parkingSpotPopulator));
    }
}
