package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.permissions.UserManager;

/**
 * 权限依赖
 */
public class PermissionsHook {
    /**
     * 判断是否有权限
     *
     * @param permission 权限节点
     * @param dodoId     id
     * @return boolean
     */
    public static boolean hasPermission(String permission, String dodoId) {
        return UserManager.hasPerm(UserManager.getUser(dodoId), permission);
    }
}
