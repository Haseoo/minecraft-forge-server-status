package com.github.haseoo.minecraft.statusapi.views;

import com.github.haseoo.minecraft.statusapi.models.PingResponse;
import com.github.haseoo.minecraft.statusapi.models.Player;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class ServerInfoView extends AbstractResponse {
    String serverName;
    String icon;
    String version;
    Integer onlinePlayersCount;
    Integer maxPlayers;
    List<String> onlinePlayers;
    List<ModInfoView> mods;


    public static ServerInfoView from(String serverName, PingResponse pingResponse) {
        return ServerInfoView.builder()
                .online(true)
                .description(pingResponse.getDescription().getDescriptionText())
                .serverName(serverName)
                .icon(pingResponse.getFavicon())
                .version(pingResponse.getVersionInfo().getVersion())
                .onlinePlayersCount(pingResponse.getPlayersInfo().getOnline())
                .maxPlayers(pingResponse.getPlayersInfo().getMax())
                .onlinePlayers(pingResponse.getPlayersInfo().getPlayers().stream()
                        .map(Player::getNickname)
                        .collect(Collectors.toList()))
                .mods(pingResponse.getForgeData().getMods().stream()
                        .map(ModInfoView::form)
                        .collect(Collectors.toList()))
                .build();


    }
}
