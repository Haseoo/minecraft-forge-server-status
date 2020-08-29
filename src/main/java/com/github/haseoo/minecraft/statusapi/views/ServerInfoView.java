package com.github.haseoo.minecraft.statusapi.views;

import com.github.haseoo.minecraft.statusapi.models.PingResponse;
import com.github.haseoo.minecraft.statusapi.models.Player;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class ServerInfoView {
    String name;
    String description;
    String version;
    Integer onlinePlayersCount;
    Integer maxPlayers;
    List<String> onlinePlayers;
    String icon;
    List<ModInfoView> mods;

    public static ServerInfoView from(String serverName, PingResponse pingResponse) {
        return new ServerInfoView(serverName,
                pingResponse.getDescription().getDescriptionText(),
                pingResponse.getVersionInfo().getVersion(),
                pingResponse.getPlayersInfo().getOnline(),
                pingResponse.getPlayersInfo().getMax(),
                pingResponse.getPlayersInfo().getPlayers().stream()
                        .map(Player::getNickname)
                        .collect(Collectors.toList()),
                pingResponse.getFavicon(),
                pingResponse.getForgeData().getMods().stream()
                        .map(ModInfoView::form)
                        .collect(Collectors.toList())
        );
    }
}
