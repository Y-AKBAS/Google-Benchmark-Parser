package com.yakbas.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class Jackson {

    private static ObjectMapper mapper;
    private static ObjectWriter prettyWriter;

    private Jackson() {
    }

    private static ObjectMapper getInstance() {
        if (Objects.isNull(mapper)) {
            mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return mapper;
    }

    private static ObjectWriter getPrettyWriterInstance() {
        if (Objects.isNull(prettyWriter)) {
            prettyWriter = getInstance().writerWithDefaultPrettyPrinter();
        }
        return prettyWriter;
    }

    public static Optional<String> toJson(Object obj) {
        try {
            return Optional.ofNullable(getInstance().writeValueAsString(obj));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static void toJson(File file, Object obj) {
        try {
            getPrettyWriterInstance().writeValue(file, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> Optional<T> fromJson(String string, Class<T> clazz) {
        try {
            return Optional.ofNullable(getInstance().readValue(string, clazz));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> fromJson(File file, Class<T> clazz) {
        try {
            return Optional.ofNullable(getInstance().readValue(file, clazz));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
