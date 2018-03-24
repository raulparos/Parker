package com.parker.converter;

import com.parker.converter.populator.Populator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<SOURCE, TARGET> implements Converter<SOURCE, TARGET> {
    protected List<Populator<SOURCE, TARGET>> populators;

    public AbstractConverter() {
        this.populators = new ArrayList<>();
    }

    @Override
    public void convert(SOURCE source, TARGET target) {
        for (Populator<SOURCE, TARGET> populator : populators) {
            populator.populate(source, target);
        }
    }
}
