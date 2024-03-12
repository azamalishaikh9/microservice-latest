package com.ali.swipeservice.utils;

import com.ali.swipeservice.exception.ServiceException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Azam
 */
public class JsonUtil {
    public static String toString(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(LocalDateTime.class, new JsonSerializer<>() {
                @Override
                public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                    jsonGenerator.writeString(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime));
                }

//                @Override
//                public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//                    jsonGenerator.writeString(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime));
//                }
            });
            mapper.registerModule(simpleModule);

            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e.toString());
        }
    }

    public static Object toObject(String json, Class obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            try {
                return mapper.readValue(json, obj);
            } catch (Exception ex) {
                return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, obj));
            }
        } catch (Exception e) {

            throw new ServiceException(e.toString());
        }
    }
}
