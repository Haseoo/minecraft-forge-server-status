package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceInfo {
    @JsonProperty("res")
    private String name;
    @JsonProperty("version")
    private String version;
    @JsonProperty("required")
    private Boolean required;
}
