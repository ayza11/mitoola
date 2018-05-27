package com.milaboratory.mitoola.util.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.TimeZone;


public class DefaultJsonMapper extends ObjectMapper {

    public DefaultJsonMapper() {
        setTimeZone(TimeZone.getDefault());
        registerModule(new Jdk8Module());
        registerModule(new JavaTimeModule());

        configure(SerializationFeature.INDENT_OUTPUT, false);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}

