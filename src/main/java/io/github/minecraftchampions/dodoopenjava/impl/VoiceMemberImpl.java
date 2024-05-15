package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.VoiceMember;
import lombok.NonNull;

import java.util.function.Function;

public class VoiceMemberImpl extends DodoUserImpl implements VoiceMember {
    protected VoiceMemberImpl(@NonNull String dodoSourceId, @NonNull String islandSourceId, @NonNull Bot bot) {
        super(dodoSourceId, islandSourceId, bot);
    }

    @NonNull
    public String getChannelId() {
        return getBot().getApi().V2.getChannelVoiceApi().getChannelVoiceMemberStatus(getIslandSourceId(), getDodoSourceId()).ifSuccess((Function<Result, String>) result ->
                result.getJSONObjectData().getJSONObject("data").getString("channelId")
        );
    }

    @Override
    public int getSpkStatus() {
        return getBot().getApi().V2.getChannelVoiceApi().getChannelVoiceMemberStatus(getIslandSourceId(), getDodoSourceId()).ifSuccess((Function<Result, Integer>) result ->
                result.getJSONObjectData().getJSONObject("data").getInt("spkStatus")
        );
    }

    @Override
    public int getMicStatus() {
        return getBot().getApi().V2.getChannelVoiceApi().getChannelVoiceMemberStatus(getIslandSourceId(), getDodoSourceId()).ifSuccess((Function<Result, Integer>) result ->
                result.getJSONObjectData().getJSONObject("data").getInt("micStatus")
        );
    }

    @Override
    public int getMicSortStatus() {
        return getBot().getApi().V2.getChannelVoiceApi().getChannelVoiceMemberStatus(getIslandSourceId(), getDodoSourceId()).ifSuccess((Function<Result, Integer>) result ->
                result.getJSONObjectData().getJSONObject("data").getInt("micSortStatus")
        );
    }
}
