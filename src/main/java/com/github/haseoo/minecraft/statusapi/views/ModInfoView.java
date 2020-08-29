package com.github.haseoo.minecraft.statusapi.views;

import com.github.haseoo.minecraft.statusapi.models.ModInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class ModInfoView {
    String id;
    String version;

    public static ModInfoView form(ModInfo modInfo) {
        return new ModInfoView(modInfo.getId(), modInfo.getVersion());
    }
}
