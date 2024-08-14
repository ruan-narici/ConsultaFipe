package br.com.ruannarici.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataModelContainer(
        @JsonAlias("modelos") List<DataModel> modelos
) {
}
