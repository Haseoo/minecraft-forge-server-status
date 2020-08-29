package com.github.haseoo.minecraft.statusapi.services;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.models.ModInfo;
import com.github.haseoo.minecraft.statusapi.models.PingResponse;
import com.github.haseoo.minecraft.statusapi.repositories.ServerStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.github.haseoo.minecraft.statusapi.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class ServerStatusServiceImpl implements ServerStatusService {
    private final ServerStatusRepository serverStatusRepository;

    @Override
    public PingResponse getServerStatus(boolean normalize) throws IOException, MinecraftEntityNotFound {
        PingResponse pingResponse = serverStatusRepository.fetchServerInfo();
        if (normalize) {
            fixModList(pingResponse.getForgeData().getMods());
        }
        return pingResponse;
    }

    private void fixModList(List<ModInfo> modsList) throws MinecraftEntityNotFound {
        ModInfo minecraft = getMinecraftEntity(modsList);
        modsList.remove(minecraft);
        replaceInvalidModVersions(modsList, minecraft);
    }

    private ModInfo getMinecraftEntity(List<ModInfo> modsList) throws MinecraftEntityNotFound {
        ModInfo minecraft = null;
        for (var mod : modsList) {
            if (mod.id.equals(MINECRAFT_ENTITY_ID)) {
                minecraft = mod;
            }
        }
        if (minecraft == null) {
            throw new MinecraftEntityNotFound();
        }
        return minecraft;
    }

    private void replaceInvalidModVersions(List<ModInfo> modsList, ModInfo minecraft) {
        for (var mod : modsList) {
            if (mod.version.equals(NO_MOD_VERSION_STRING_INDICATOR_1) ||
                    mod.version.equals(NO_MOD_VERSION_STRING_INDICATOR_2)) {
                mod.version = String.format(FIX_MOD_VERSION_FORMAT, minecraft.version);
            }
        }
    }
}
