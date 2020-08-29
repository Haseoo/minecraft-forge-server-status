package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PingResponse {
    @JsonProperty("description")
    private Description description;
    @JsonProperty("players")
    private PlayersInfo playersInfo;
    @JsonProperty("version")
    private VersionInfo versionInfo;
    @JsonProperty("favicon")
    private String favicon;
    @JsonProperty("forgeData")
    private ForgeData forgeData;
}
