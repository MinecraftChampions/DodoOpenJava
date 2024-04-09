package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Slf4j
@ToString
@AllArgsConstructor
public class IslandImpl implements Island {
    private @NonNull String islandId;
    private @NonNull Bot bot;

    @Override
    public String getIslandName() {
        return null;
    }

    @Override
    public String getCoverUrl() {
        return null;
    }

    @Override
    public int getMemberCount() {
        return 0;
    }

    @Override
    public int getOnlineMemberCount() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getDefaultChannelId() {
        return null;
    }

    @Override
    public String getSystemChannelId() {
        return null;
    }

    @Override
    public String getOwnerDodoId() {
        return null;
    }

    @Override
    public List<JSONObject> getIslandLevelRankList() {
        return null;
    }

    @Override
    public List<JSONObject> getIslandMuteList() {
        return null;
    }

    @Override
    public List<JSONObject> getIslandBanList() {
        return null;
    }

    @Override
    public List<User> getMemberList() {
        return null;
    }

    @Override
    public User getUser(String dodoId) {
        return null;
    }

    @Override
    public List<Channel> getChannelList() {
        return null;
    }

    @Override
    public Channel getChannel(String channelId) {
        return null;
    }

    @Override
    public List<Role> getRoleList() {
        Result result = bot.getApi().V2.getRoleApi().getRoleList(getIslandId());
        if (result.isFailure()) {
            log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return new ArrayList<>();
        }
        List<JSONObject> list = result.getJSONObjectData().getJSONArray("data").toList().stream().map((o) -> {
            if (o instanceof Map<?, ?> map) {
                return new JSONObject(map);
            }
            return new JSONObject();
        }).toList();
        return list.stream().map(jsonObject -> new RoleImpl(jsonObject.getString("roleId"), getIslandId(), bot)).collect(Collectors.toList());
    }

    @Override
    public Role getRole(String roleId) {
        Result result = bot.getApi().V2.getRoleApi().getRoleList(getIslandId());
        if (result.isFailure()) {
            log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return null;
        }
        JSONArray array = result.getJSONObjectData().getJSONArray("data");
        String str = array.toString();
        if (str.contains("\"roleId\":\"" + roleId + "\",")) {
            return new RoleImpl(roleId, getIslandId(), bot);
        }
        return null;
    }

    @Override
    public Channel createChannel(String channelName, int channelType) {
        return null;
    }

    @Override
    public Channel createChannel(int channelType) {
        return null;
    }
}
