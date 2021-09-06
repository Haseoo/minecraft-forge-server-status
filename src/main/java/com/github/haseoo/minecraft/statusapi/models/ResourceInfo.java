package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceInfo {
    @JsonProperty("res")
    private String name;
    @JsonProperty("version")
    private String version;
    @JsonProperty("required")
    private Boolean required;
}
