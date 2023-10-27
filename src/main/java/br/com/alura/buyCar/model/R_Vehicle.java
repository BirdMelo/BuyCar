package br.com.alura.buyCar.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record R_Vehicle(
        @JsonAlias("Valor") String price,
        @JsonAlias("Marca") String brand,
        @JsonAlias("Modelo") String model,
        @JsonAlias("AnoModelo") int modelYear,
        @JsonAlias("Combustivel") String fuel
)
{ }
