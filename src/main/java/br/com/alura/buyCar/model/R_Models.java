package br.com.alura.buyCar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record R_Models(List<R_Data> models) {
}
