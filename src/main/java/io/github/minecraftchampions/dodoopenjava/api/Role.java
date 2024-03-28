package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;

/**
 * 身份组
 */
public interface Role {
    String getRoleId();

    Result create();

    Result delete();

    long getMemberCount();

    Result editRoleName(String name);

    Result editRoleColor(String color);

    Result editPosition(int position);

    Result editPermission(Permission permission);

    Result editPermission(String permission);
}
