package br.com.alura.buyCar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collections;
import java.util.List;

public class DataConverter implements IDataConverter{
    private ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public <T> T getDatum(String json, Class<T> tClass) {
            try {
                return MAPPER.readValue(json,tClass);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
    }
    @Override
    public <T> List<T> getList(String json, Class<T> tClass) {
        CollectionType list = MAPPER.getTypeFactory()
                .constructCollectionType(List.class, tClass);

        try {
            return MAPPER.readValue(json,list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
