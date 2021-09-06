package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class VersionInfo {
    @JsonProperty("name")
    private String version;
    @JsonProperty("protocol")
    private Integer protocol;
}
