package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String nickname;
}
