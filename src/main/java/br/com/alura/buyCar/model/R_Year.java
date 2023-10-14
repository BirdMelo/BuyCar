package br.com.alura.buyCar.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record R_Year(
        @JsonAlias("nome") String name,
        @JsonAlias("codigo") String code
)
{ }
