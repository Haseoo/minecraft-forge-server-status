package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceInfo {
    @JsonProperty("res")
    public String name;
    @JsonProperty("version")
    public String version;
    @JsonProperty("required")
    public Boolean required;

}
