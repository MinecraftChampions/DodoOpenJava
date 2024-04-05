package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;

import java.util.List;

/**
 * 身份组
 */
public interface Role {
    /**
     * 获取身份组id
     *
     * @return id
     */
    String getRoleId();

    /**
     * 获取群id
     *
     * @return id
     */
    String getIslandSourceId();

    /**
     * 删除
     */
    Result delete();

    /**
     * 获取成员数量
     *
     * @return 数量
     */
    long getMemberCount();

    /**
     * 编辑名字
     *
     * @param name name
     */
    Result editRoleName(String name);

    /**
     * 编辑身份组颜色
     *
     * @param color 颜色
     */
    Result editRoleColor(String color);

    /**
     * 编辑排序优先度
     *
     * @param position position
     */
    Result editPosition(int position);

    /**
     * 编辑权限
     *
     * @param permission permission
     */
    Result editPermission(Permission permission);

    /**
     * 编辑权限
     *
     * @param permission permission
     */
    Result editPermission(String permission);

    /**
     * 移除成员
     *
     * @param dodoId id
     */
    Result removeMember(String dodoId);

    /**
     * 新增成员
     *
     * @param dodoId id
     */
    Result addMember(String dodoId);

    /**
     * 获取成员列表
     *
     * @return list
     */
    List<User> getMemberList();
}
