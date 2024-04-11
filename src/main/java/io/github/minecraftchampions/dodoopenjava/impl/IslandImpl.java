package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Getter
@Slf4j
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class IslandImpl implements Island {
    private @NonNull String islandSourceId;
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
    public List<User> getIslandMuteList() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<User> userList = new ArrayList<>();
        Int maxId = new Int(0);
        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        while (true) {
            Result result = bot.getApi().V2.getIslandApi().getIslandMuteList(getIslandSourceId(),
                    100, maxId.getValue()).ifFailure(r -> {
                log.error("获取成员信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getJSONObjectData());
            });
            if (result.isSuccess()) {
                if (!(splice(result,userList,maxId,completableFutures,executorService))) {
                    break;
                }
            } else {
                break;
            }
        }
        CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
        executorService.shutdown();
        return userList;
    }

    @Override
    public List<User> getIslandBanList() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<User> userList = new ArrayList<>();
        Int maxId = new Int(0);
        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        while (true) {
            Result result = bot.getApi().V2.getIslandApi().getIslandBanList(getIslandSourceId(),
                    100, maxId.getValue()).ifFailure(r -> {
                log.error("获取成员信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getJSONObjectData());
            });
            if (result.isSuccess()) {
                if (!(splice(result,userList,maxId,completableFutures,executorService))) {
                    break;
                }
            } else {
                break;
            }
        }
        CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
        executorService.shutdown();
        return userList;
    }

    @Override
    public List<User> getRoleMemberList(@NonNull String roleId) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<User> userList = new ArrayList<>();
        Int maxId = new Int(0);
        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        while (true) {
            Result result = bot.getApi().V2.getRoleApi().getMemberList(getIslandSourceId(), roleId,
                    100, maxId.getValue()).ifFailure(r -> {
                log.error("获取成员信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getJSONObjectData());
            });
            if (result.isSuccess()) {
                if (!(splice(result,userList,maxId,completableFutures,executorService))) {
                    break;
                }
            } else {
                break;
            }
        }
        CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
        executorService.shutdown();
        return userList;
    }

    private boolean splice(Result result, List<User> userList, Int maxId,
                           List<CompletableFuture<?>> completableFutures,ExecutorService executorService) {
        JSONArray array = result.getJSONObjectData().getJSONObject("data")
                .getJSONArray("list");
        completableFutures.add(CompletableFuture.runAsync(() -> array.forEach(o -> {
            if (o instanceof JSONObject jsonObject) {
                userList.add(new DodoUserImpl(jsonObject.getString("dodoSourceId"), getIslandSourceId(), getBot()));
            }
        }), executorService));
        maxId.setValue(result.getJSONObjectData().getJSONObject("data")
                .getInt("maxId"));
        return maxId.getValue() != -1;
    }

    @Data
    @RequiredArgsConstructor
    private static class Int {
        @NonNull
        private int value;
    }

    @Override
    public List<User> getMemberList() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<User> userList = new ArrayList<>();
        Int maxId = new Int(0);
        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        while (true) {
            Result result = bot.getApi().V2.getMemberApi().getMemberList(getIslandSourceId(),
                    100, maxId.getValue()).ifFailure(r -> {
                log.error("获取成员信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getJSONObjectData());
            });
            if (result.isSuccess()) {
                if (!(splice(result,userList,maxId,completableFutures,executorService))) {
                    break;
                }
            } else {
                break;
            }
        }
        CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
        executorService.shutdown();
        return userList;
    }

    @Override
    public User getUser(@NonNull String dodoId) {
        Result result = bot.getApi().V2.getMemberApi().getMemberInfo(getIslandSourceId(), dodoId);
        if (result.isSuccess()) {
            return new DodoUserImpl(dodoId, getIslandSourceId(), bot);
        }
        log.error("获取用户信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
        return null;
    }

    @Override
    public List<Channel> getChannelList() {
        Result result = bot.getApi().V2.getChannelApi().getChannelList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return null;
        }
        List<JSONObject> list = result.getJSONObjectData().getJSONArray("data").toList().stream().map((o) -> {
            if (o instanceof Map<?, ?> map) {
                return new JSONObject(map);
            }
            return new JSONObject();
        }).toList();
        return list.stream().map(jsonObject -> new ChannelImpl(jsonObject.getString("channelId"), getIslandSourceId(), bot)).collect(Collectors.toList());
    }

    @Override
    public Channel getChannel(@NonNull String channelId) {
        Result result = bot.getApi().V2.getChannelApi().getChannelList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return null;
        }
        if (result.getJSONObjectData().toString().contains("\"channelId\": \"" + channelId + "\"")) {
            return new ChannelImpl(channelId, getBot());
        }
        return null;
    }

    @Override
    public List<Role> getRoleList() {
        Result result = bot.getApi().V2.getRoleApi().getRoleList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取身份组信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return new ArrayList<>();
        }
        List<JSONObject> list = result.getJSONObjectData().getJSONArray("data").toList().stream().map((o) -> {
            if (o instanceof Map<?, ?> map) {
                return new JSONObject(map);
            }
            return new JSONObject();
        }).toList();
        return list.stream().map(jsonObject -> new RoleImpl(jsonObject.getString("roleId"), getIslandSourceId(), bot)).collect(Collectors.toList());
    }

    @Override
    public Role getRole(@NonNull String roleId) {
        Result result = bot.getApi().V2.getRoleApi().getRoleList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取身份组信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return null;
        }
        JSONArray array = result.getJSONObjectData().getJSONArray("data");
        String str = array.toString();
        if (str.contains("\"roleId\":\"" + roleId + "\",")) {
            return new RoleImpl(roleId, getIslandSourceId(), bot);
        }
        return null;
    }

    @Override
    public Channel createChannel(@NonNull String channelName, int channelType) {
        return null;
    }

    @Override
    public Channel createChannel(int channelType) {
        return null;
    }
}
