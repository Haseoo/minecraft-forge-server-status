package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PlayersInfo {
    @JsonProperty("max")
    public Integer max;
    @JsonProperty("online")
    public Integer online;
    @JsonProperty("sample")
    public List<Player> players = new ArrayList<>();

}
