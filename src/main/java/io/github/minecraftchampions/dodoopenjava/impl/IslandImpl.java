package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.api.*;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;
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
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取群名字失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        return result.getData().getJSONObject("data").getString("islandName");
    }

    @Override
    public String getCoverUrl() {
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取群头像失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        return result.getData().getJSONObject("data").getString("coverUrl");
    }

    @Override
    public int getMemberCount() {
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        return result.getData().getJSONObject("data").getInt("memberCount");
    }

    @Override
    public int getOnlineMemberCount() {
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        return result.getData().getJSONObject("data").getInt("onlineMemberCount");
    }

    @Override
    public String getDescription() {
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        return result.getData().getJSONObject("data").getString("description");

    }

    @Override
    public String getDefaultChannelId() {
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取默认访问频道id失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        return result.getData().getJSONObject("data").getString("defaultChannelId");
    }

    @Override
    public String getSystemChannelId() {
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取系统消息频道id失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        return result.getData().getJSONObject("data").getString("systemChannelId");
    }

    @Override
    public String getOwnerDodoId() {
        Result result = getBot().getApi().getIslandApi().getIslandInfo(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取群主人id失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        return result.getData().getJSONObject("data").getString("ownerDodoSourceId");
    }

    @Override
    public List<User> getIslandLevelRankList() {
        Result result = bot.getApi().getIslandApi()
                .getIslandLevelRankList(getIslandSourceId());
        JSONArray array = result.getData().getJSONArray("data");
        List<User> userList = new ArrayList<>();
        for (Object o : array) {
            if (o instanceof JSONObject jsonObject) {
                userList.add(getBot().getApi().getMemberApi().getUser(islandSourceId,
                        jsonObject.getString("dodoSourceId")));
            }
        }
        return userList;
    }

    @Override
    public List<User> getIslandMuteList() {
        return CompletableFuture.supplyAsync(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            List<User> userList = new ArrayList<>();
            Longer maxId = new Longer(0);
            List<CompletableFuture<?>> completableFutures = new ArrayList<>();
            while (true) {
                Result result = bot.getApi().getIslandApi().getIslandMuteList(getIslandSourceId(),
                        100, maxId.getValue()).ifFailure(r -> {
                    log.error("获取群禁言信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getData());
                });
                if (result.isSuccess()) {
                    if (!(splice(result, userList, maxId, completableFutures, executorService))) {
                        break;
                    }
                } else {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
            executorService.shutdown();
            return userList;
        }).join();
    }

    @Override
    public List<User> getIslandBanList() {
        return CompletableFuture.supplyAsync(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            List<User> userList = new ArrayList<>();
            Longer maxId = new Longer(0);
            List<CompletableFuture<?>> completableFutures = new ArrayList<>();
            while (true) {
                Result result = bot.getApi().getIslandApi().getIslandBanList(getIslandSourceId(),
                        100, maxId.getValue()).ifFailure(r -> {
                    log.error("获取封禁信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getData());
                });
                if (result.isSuccess()) {
                    if (!(splice(result, userList, maxId, completableFutures, executorService))) {
                        break;
                    }
                } else {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
            executorService.shutdown();
            return userList;
        }).join();
    }

    @Override
    public List<User> getRoleMemberList(@NonNull String roleId) {
        return CompletableFuture.supplyAsync(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            List<User> userList = new ArrayList<>();
            Longer maxId = new Longer(0);
            List<CompletableFuture<?>> completableFutures = new ArrayList<>();
            while (true) {
                Result result = bot.getApi().getRoleApi().getMemberList(getIslandSourceId(), roleId,
                        100, maxId.getValue()).ifFailure(r -> {
                    log.error("获取身份组成员信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getData());
                });
                if (result.isSuccess()) {
                    if (!(splice(result, userList, maxId, completableFutures, executorService))) {
                        break;
                    }
                } else {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
            executorService.shutdown();
            return userList;
        }).join();
    }

    protected boolean splice(Result result, List<User> userList, Longer maxId,
                           List<CompletableFuture<?>> completableFutures, ExecutorService executorService) {
        JSONObject json = result.getData().getJSONObject("data");
        if (json.isEmpty()) {
            return false;
        }
        JSONArray array = json.getJSONArray("list");
        if (array.isEmpty()) {
            return false;
        }
        completableFutures.add(CompletableFuture.runAsync(() -> array.forEach(o -> {
            if (o instanceof JSONObject jsonObject) {
                userList.add(new DodoUserImpl(jsonObject.getString("dodoSourceId"), getIslandSourceId(), getBot()));
            }
        }), executorService));
        maxId.setValue(result.getData().getJSONObject("data")
                .getLong("maxId"));
        return maxId.getValue() > 0;
    }

    @Data
    @AllArgsConstructor
    protected static class Longer {
        private long value;
    }

    @Override
    public List<User> getMemberList() {
        return CompletableFuture.supplyAsync(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            List<User> userList = new ArrayList<>();
            Longer maxId = new Longer(0);
            List<CompletableFuture<?>> completableFutures = new ArrayList<>();
            while (true) {
                Result result = bot.getApi().getMemberApi().getMemberList(getIslandSourceId(),
                        100, maxId.getValue()).ifFailure(r -> {
                    log.error("获取成员信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getData());
                });
                if (result.isSuccess()) {
                    if (!(splice(result, userList, maxId, completableFutures, executorService))) {
                        break;
                    }
                } else {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();
            executorService.shutdown();
            return userList;
        }).join();
    }

    @Override
    public User getUser(@NonNull String dodoId) {
        Result result = bot.getApi().getMemberApi().getMemberInfo(getIslandSourceId(), dodoId);
        if (result.isSuccess()) {
            return new DodoUserImpl(dodoId, getIslandSourceId(), bot);
        }
        log.error("获取用户信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
        return null;
    }

    @Override
    public List<Channel> getChannelList() {
        Result result = bot.getApi().getChannelApi().getChannelList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取频道列表失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        List<JSONObject> list = result.getData().getJSONArray("data").toList().stream().map((o) -> {
            if (o instanceof Map<?, ?> map) {
                return new JSONObject(map);
            }
            return new JSONObject();
        }).toList();
        return list.stream().map(jsonObject -> new ChannelImpl(jsonObject.getString("channelId"), getIslandSourceId(), bot)).collect(Collectors.toList());
    }

    @Override
    public Channel getChannel(@NonNull String channelId) {
        Result result = bot.getApi().getChannelApi().getChannelList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        if (result.getData().toString().contains("\"channelId\":\"" + channelId + "\"")) {
            return new ChannelImpl(channelId, getBot());
        }
        return null;
    }

    @Override
    public List<Role> getRoleList() {
        Result result = bot.getApi().getRoleApi().getRoleList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取身份组列表失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        List<JSONObject> list = result.getData().getJSONArray("data").toList().stream().map((o) -> {
            if (o instanceof Map<?, ?> map) {
                return new JSONObject(map);
            }
            return new JSONObject();
        }).toList();
        return list.stream().map(jsonObject -> new RoleImpl(jsonObject.getString("roleId"), getIslandSourceId(), bot)).collect(Collectors.toList());
    }

    @Override
    public Role getRole(@NonNull String roleId) {
        Result result = bot.getApi().getRoleApi().getRoleList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取身份组信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        JSONArray array = result.getData().getJSONArray("data");
        String str = array.toString();
        if (str.contains("\"roleId\":\"" + roleId + "\",")) {
            return new RoleImpl(roleId, getIslandSourceId(), bot);
        }
        return null;
    }

    @Override
    public Channel createChannel(@NonNull String channelName, int channelType) {
        Result result = bot.getApi().getChannelApi().addChannel(getIslandSourceId(), channelName, channelType);
        if (result.isFailure()) {
            log.error("创建频道失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        return new ChannelImpl(result.getData().getString("channelId"), getIslandSourceId(), bot);
    }

    @Override
    public Channel createChannel(int channelType) {
        return createChannel(null, channelType);
    }

    @Override
    public Role createRole(String roleName, String roleColor, int position, Permission permission) {
        Result result = bot.getApi().getRoleApi().addRole(getIslandSourceId(), roleName, roleColor, position, permission);
        if (result.isFailure()) {
            log.error("创建身份组失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return null;
        }
        return new RoleImpl(result.getData().getString("roleId"), getIslandSourceId(), bot);
    }
}
