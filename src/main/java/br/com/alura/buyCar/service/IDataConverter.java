package br.com.alura.buyCar.service;

import java.util.List;

public interface IDataConverter {
    <T> T getDatum(String json, Class<T> tClass);
    <T> List<T>getList(String json, Class<T> tClass);
}
