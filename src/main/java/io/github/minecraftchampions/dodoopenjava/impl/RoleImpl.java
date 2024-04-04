package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Role;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * 身份组实现
 */
@Getter
@AllArgsConstructor
public class RoleImpl implements Role {
    @NonNull
    private String roleId;
    @Override
    public Result delete() {
        return null;
    }

    @Override
    public long getMemberCount() {
        return 0;
    }

    @Override
    public Result editRoleName(String name) {
        return null;
    }

    @Override
    public Result editRoleColor(String color) {
        return null;
    }

    @Override
    public Result editPosition(int position) {
        return null;
    }

    @Override
    public Result editPermission(Permission permission) {
        return editPermission(permission.toHexString());
    }

    @Override
    public Result editPermission(String permission) {
        return null;
    }

    @Override
    public Result removeMember(String dodoId) {
        return null;
    }

    @Override
    public Result addMember(String dodoId) {
        return null;
    }

    @Override
    public List<User> getMemberList() {
        return null;
    }
}
