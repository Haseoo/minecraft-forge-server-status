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
    String icon;
    String version;
    Integer onlinePlayersCount;
    Integer maxPlayers;
    List<String> onlinePlayers;
    List<ModInfoView> mods;


    public static ServerInfoView from(PingResponse pingResponse) {
        var builder = ServerInfoView.builder()
                .online(true)
                .description(pingResponse.getDescription().getDescriptionText())
                .icon(pingResponse.getFavicon())
                .version(pingResponse.getVersionInfo().getVersion())
                .onlinePlayersCount(pingResponse.getPlayersInfo().getOnline())
                .maxPlayers(pingResponse.getPlayersInfo().getMax())
                .onlinePlayers(pingResponse.getPlayersInfo().getPlayers().stream()
                        .map(Player::getNickname)
                        .collect(Collectors.toList()));
        if (pingResponse.getForgeData() != null && pingResponse.getForgeData().getMods() != null) {
            builder.mods(pingResponse.getForgeData().getMods().stream()
                    .map(ModInfoView::form)
                    .collect(Collectors.toList()));
        }
        return builder.build();


    }
}
