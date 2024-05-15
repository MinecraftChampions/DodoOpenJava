package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Channel;
import io.github.minecraftchampions.dodoopenjava.api.ChannelType;
import io.github.minecraftchampions.dodoopenjava.api.VoiceMember;
import lombok.NonNull;

public class VoiceChannel extends ChannelImpl {
    private VoiceChannel(@NonNull String channelId, @NonNull String islandSourceId, @NonNull Bot bot) {
        super(channelId, islandSourceId, bot);
    }

    public static VoiceChannel of(@NonNull Channel channel) {
        if (channel.getChannelType() != ChannelType.VOICE) {
            throw new RuntimeException("错误的频道类型");
        }
        return new VoiceChannel(channel.getChannelId(), channel.getIslandSourceId(), channel.getBot());
    }

    public VoiceMember getMember(@NonNull String dodoSourceId) {
        return null;
    }
}
