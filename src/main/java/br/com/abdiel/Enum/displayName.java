package br.com.abdiel.Enum;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum displayName {

    @JsonProperty("FIREFOX") FIREFOX("Firefox"),
    @JsonProperty("CHROME") CHROME("Google Chorme"),
    @JsonProperty("EXPLORER") EXPLORER("Internet Explorer"),
    @JsonProperty("HEADLESS") HEADLESS("Google Chorme");


    private final String value;

    displayName(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }

    public static displayName fromName(String nome) {
        return Arrays.stream(displayName.values())
                .filter(displayName -> displayName.getName().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nome do navegador invalido."));
    }
}
