package com.github.haseoo.minecraft.statusapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForgeData {
    @JsonProperty("channels")
    public List<ResourceInfo> resources = new ArrayList<>();
    @JsonProperty("mods")
    public List<ModInfo> mods = new ArrayList<>();
    @JsonProperty("fmlNetworkVersion")
    public String fmlNetworkVersion;

}