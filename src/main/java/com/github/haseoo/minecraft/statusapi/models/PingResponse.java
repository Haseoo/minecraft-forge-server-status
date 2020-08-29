package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PingResponse {
    @JsonProperty("description")
    public Description description;
    @JsonProperty("players")
    public PlayersInfo playersInfo;
    @JsonProperty("version")
    public Version version;
    @JsonProperty("favicon")
    public String favicon;
    @JsonProperty("forgeData")
    public ForgeData forgeData;

}
