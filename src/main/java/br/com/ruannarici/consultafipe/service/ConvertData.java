package br.com.ruannarici.consultafipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConvertData {

    private ObjectMapper objectMapper = new ObjectMapper();


    public <T> T toObject(String data, Class<T> classType) {
        try {
            return objectMapper.readValue(data, classType);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> T toListObject(String data, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(data, typeReference);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
