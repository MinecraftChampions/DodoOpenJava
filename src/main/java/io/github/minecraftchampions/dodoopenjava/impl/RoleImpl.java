package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Island;
import io.github.minecraftchampions.dodoopenjava.api.Role;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份组实现
 */
@Getter
@Slf4j
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RoleImpl implements Role {
    @NonNull
    private String roleId;

    @NonNull
    private String islandSourceId;

    @NonNull
    private Bot bot;

    @Override
    public Result delete() {
        return getBot().getApi().getRoleApi().deleteRole(getIslandSourceId(), getRoleId());
    }

    @Override
    public long getMemberCount() {
        Result result = bot.getApi().getRoleApi().getRoleList(getIslandSourceId());
        if (result.isFailure()) {
            log.error("获取成员信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getData());
            return 0;
        }
        JSONArray array = result.getData().getJSONArray("data");
        String str = array.toString();
        Pattern pattern = Pattern.compile("\"roleId\":\"" + getRoleId() + "\"[^m]+memberCount\":([0-9]+),");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            return 0;
        }
        return Long.parseLong(matcher.group(1));
    }

    @Override
    public Result editRoleName(@NonNull String name) {
        return getBot().getApi().getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , name, null, (Integer) null, (String) null);
    }

    @Override
    public Result editRoleColor(@NonNull String color) {
        return getBot().getApi().getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, color, (Integer) null, (String) null);
    }

    @Override
    public Result editPosition(int position) {
        return getBot().getApi().getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, null, position, (String) null);
    }

    @Override
    public Result editPermission(@NonNull Permission permission) {
        return getBot().getApi().getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, null, (Integer) null, permission);
    }

    @Override
    public Result editPermission(@NonNull String permission) {
        return getBot().getApi().getRoleApi().editRole(getIslandSourceId(), getRoleId()
                , null, null, (Integer) null, permission);
    }

    @Override
    public Result removeMember(@NonNull String dodoId) {
        return getBot().getApi().getRoleApi().removeRoleMember(getIslandSourceId(), dodoId, getRoleId());
    }

    @Override
    public Result addMember(@NonNull String dodoId) {
        return getBot().getApi().getRoleApi().addRoleMember(getIslandSourceId(), dodoId, getRoleId());
    }

    @Override
    public List<User> getMemberList() {
        return getIsland().getRoleMemberList(getRoleId());
    }

    @Override
    public Island getIsland() {
        return new IslandImpl(getIslandSourceId(), bot);
    }
}
