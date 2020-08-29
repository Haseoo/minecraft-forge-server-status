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
    private Integer max;
    @JsonProperty("online")
    private Integer online;
    @JsonProperty("sample")
    private List<Player> players = new ArrayList<>();
}
