package br.com.ruannarici.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Brand(
        @JsonAlias("codigo") String codigo,
        @JsonAlias("nome") String nome
) {
}
