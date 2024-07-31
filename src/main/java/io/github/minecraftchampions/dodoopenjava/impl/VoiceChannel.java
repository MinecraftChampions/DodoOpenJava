package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.debug.Result;
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
        return new VoiceMemberImpl(dodoSourceId, this.getIslandSourceId(), this.getBot());
    }

    public Result moveMemberTo(@NonNull VoiceMember voiceMember, @NonNull VoiceChannel targetChannel) {
        return getBot().getApi().getChannelVoiceApi().moveChannelVoiceMember(getIslandSourceId(), voiceMember.getDodoSourceId(), targetChannel.getChannelId());
    }

    public Result editMemberStatus(@NonNull VoiceMember voiceMember,int type) {
        return getBot().getApi().getChannelVoiceApi().editChannelVoiceMember(type, voiceMember.getDodoSourceId(), getChannelId());
    }

    public Result onMic(@NonNull VoiceMember voiceMember) {
        return editMemberStatus(voiceMember,1);
    }

    public Result underMic(@NonNull VoiceMember voiceMember) {
        return editMemberStatus(voiceMember,0);
    }

    public Result closeMic(@NonNull VoiceMember voiceMember) {
        return editMemberStatus(voiceMember,2);
    }

    public Result removeMember(@NonNull VoiceMember voiceMember) {
        return editMemberStatus(voiceMember,3);
    }

}
