package com.parker.util.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class DateObjectMapper extends ObjectMapper {

    public DateObjectMapper() {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}