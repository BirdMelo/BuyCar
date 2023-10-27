package br.com.alura.buyCar.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record R_Year(
        @JsonAlias("codigo") String release,
        @JsonAlias("nome") String name
)
{ }
