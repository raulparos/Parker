package com.parker.converter.impl;

import com.parker.converter.AbstractConverter;
import com.parker.converter.populator.Populator;
import com.parker.data.ParkingSpotData;
import com.parker.domain.model.ParkingSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ParkingSpotDataConverter extends AbstractConverter<ParkingSpot, ParkingSpotData> {
    @Autowired
    private Populator<ParkingSpot, ParkingSpotData> parkingSpotDataPopulator;

    private List<Populator<ParkingSpot, ParkingSpotData>> populators =
            Collections.unmodifiableList(Arrays.asList(parkingSpotDataPopulator));

    public ParkingSpotDataConverter() {
        super();
        super.populators = populators;
    }
}
