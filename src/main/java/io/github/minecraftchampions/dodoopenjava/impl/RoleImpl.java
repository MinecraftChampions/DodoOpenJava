package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Role;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 身份组实现
 */
@Getter
@Slf4j
@AllArgsConstructor
public class RoleImpl implements Role {
    @NonNull
    private String roleId;

    @NonNull
    private String islandSourceId;

    @NonNull
    private Bot bot;

    @Override
    public Result delete() {
        return getBot().getApi().V2.getRoleApi().deleteRole(getIslandSourceId(), getRoleId());
    }

    @Override
    public long getMemberCount() {
        return 0;
    }

    @Override
    public Result editRoleName(String name) {
        return getBot().getApi().V2.getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , name, null, (Integer) null, (String) null);
    }

    @Override
    public Result editRoleColor(String color) {
        return getBot().getApi().V2.getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, color, (Integer) null, (String) null);
    }

    @Override
    public Result editPosition(int position) {
        return getBot().getApi().V2.getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, null, position, (String) null);
    }

    @Override
    public Result editPermission(Permission permission) {
        return getBot().getApi().V2.getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, null, (Integer) null, permission);
    }

    @Override
    public Result editPermission(String permission) {
        return getBot().getApi().V2.getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, null, (Integer) null, permission);
    }

    @Override
    public Result removeMember(String dodoId) {
        return getBot().getApi().V2.getRoleApi().removeRoleMember(getIslandSourceId(), dodoId, getRoleId());
    }

    @Override
    public Result addMember(String dodoId) {
        return getBot().getApi().V2.getRoleApi().addRoleMember(getIslandSourceId(), dodoId, getRoleId());
    }

    @Override
    public List<User> getMemberList() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<User> userList = new ArrayList<>();
        int maxId = 0;
        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        while (true) {
            Result result = bot.getApi().V2.getRoleApi().getMemberList(getIslandSourceId(), getRoleId(),
                    100, maxId).ifFailure(r -> {
                log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", r.getMessage(), r.getStatusCode(), r.getJSONObjectData());
            });
            if (result.isSuccess()) {
                JSONArray array = result.getJSONObjectData().getJSONObject("data")
                        .getJSONArray("list");
                completableFutures.add(CompletableFuture.runAsync(() -> array.forEach(o -> {
                    if (o instanceof JSONObject jsonObject) {
                        userList.add(new DodoUserImpl(jsonObject.getString("dodoSourceId"), getIslandSourceId(), getBot()));
                    }
                }), executorService));
                maxId = result.getJSONObjectData().getJSONObject("data")
                        .getInt("maxId");
                if (maxId == -1) {
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
}
