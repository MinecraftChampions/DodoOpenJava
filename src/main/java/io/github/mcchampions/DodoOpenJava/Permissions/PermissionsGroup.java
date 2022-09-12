package io.github.mcchampions.DodoOpenJava.Permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限组
 */
public class PermissionsGroup {
    public static List<PermissionsGroup> groups = new ArrayList<>();

    public List<String> perms = new ArrayList<>();

    public Boolean isDefault;

    public String name;

    /**
     * 构造函数
     * @param perms 权限
     * @param isDefault 是否默认
     * @param name 名字
     */
    public PermissionsGroup(List<String> perms,Boolean isDefault,String name) {
        this.perms = perms;
        this.isDefault = isDefault;
        this.name = name;
    }

    /**
     * 构造函数
     * @param perms 权限
     * @param name 名字
     */
    public PermissionsGroup(List<String> perms,String name) {
        this.perms = perms;
        this.isDefault = false;
        this.name = name;
    }

    /**
     * 构造函数
     * @param isDefault 是否默认
     * @param name 名字
     */
    public PermissionsGroup(Boolean isDefault,String name) {
        this.perms = new ArrayList<>();
        this.isDefault = isDefault;
        this.name = name;
    }

    /**
     * 构造函数
     * @param name 名字
     */
    public PermissionsGroup(String name) {
        this.perms = new ArrayList<>();
        this.isDefault = false;
        this.name = name;
    }

    public PermissionsGroup() {
    }

    /**
     * 获取所有权限组
     * @return 权限组集合
     */
    public static List<PermissionsGroup> getGroups() {
        return groups;
    }

    /**
     * 判断权限组是否为默认权限组
     * @param group 权限组
     * @return true 是，false 不是
     */
    public static Boolean isDefault(PermissionsGroup group) {
        return group.isDefault;
    }
    
    /**
     * 获取权限组名字
     * @param group 权限组
     * @return 名字
     */
    public static String getName(PermissionsGroup group) {
        return group.name;
    }

    /**
     * 获取权限组权限
     * @param group 权限组
     * @return 权限集合
     */
    public static List<String> getPerms(PermissionsGroup group) {
        return group.perms;
    }

    /**
     * 编辑权限组名字
     * @param group 权限组
     * @return 名字
     */
    public static PermissionsGroup editName(String name,PermissionsGroup group) {
        group.name = name;
        return group;
    }

    /**
     * 编辑权限组名字
     */
    public void editName(String name) {
        this.name = name;
    }

    /**
     * 判断权限组是否为默认权限组
     * @return true就是，false就不是
     */
    public Boolean isDefault() {
        return this.isDefault;
    }

    /**
     * 获取权限组名字
     * @return 名字
     */
    public String getName() {
        return this.name;
    }

    /**
     * 增加一个权限组
     * @param group 权限组
     */
    public static PermissionsGroup addGroup(PermissionsGroup group) {
        groups.add(group);
        return group;
    }

    /**
     * 增加一堆权限组
     * @param groups 权限组
     */
    public static void addGroups(List<PermissionsGroup> groups) {
        PermissionsGroup.groups.addAll(groups);
    }

    /**
     * 移除一个权限组
     * @param group 权限组
     */
    public static PermissionsGroup removeGroup(PermissionsGroup group) {
        groups.remove(group);
        return group;
    }

    /**
     * 增加一堆权限组
     * @param groups 权限组
     */
    public static void removeGroups(List<PermissionsGroup> groups) {
        PermissionsGroup.groups.removeAll(groups);
    }

    /**
     * 设置权限组
     * @param groups 权限组
     */
    public static void setGroups(List<PermissionsGroup> groups) {
        PermissionsGroup.groups = groups;
    }

    /**
     * 增加权限
     * @param perm 权限
     */
    public void addPerm(String perm) {
        this.perms.add(perm);
    }

    /**
     * 增加权限
     * @param perms 权限
     */
    public void addPerms(List<String> perms) {
        this.perms.addAll(perms);
    }

    /**
     * 设置权限
     * @param perms 权限
     */
    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    /**
     * 移除权限
     * @param perms 权限
     */
    public void removePerms(List<String> perms) {
        this.perms.removeAll(perms);
    }

    /**
     * 移除权限
     * @param perm 权限
     */
    public void removePerm(String perm) {
        this.perms.remove(perm);
    }

    /**
     * 增加权限
     * @param perm 权限
     * @param group 权限组
     */
    public static PermissionsGroup addPerm(String perm, PermissionsGroup group) {
        group.perms.add(perm);
        return group;
    }

    /**
     * 增加权限
     * @param perms 权限
     * @param group 权限组
     */
    public static PermissionsGroup addPerms(List<String> perms, PermissionsGroup group) {
        group.perms.addAll(perms);
        return group;
    }

    /**
     * 设置权限
     * @param perms 权限
     * @param group 权限组
     */
    public static PermissionsGroup setPerms(List<String> perms, PermissionsGroup group) {
        group.perms = perms;
        return group;
    }

    /**
     * 移除权限
     * @param perms 权限
     * @param group 权限组
     */
    public static PermissionsGroup removePerms(List<String> perms, PermissionsGroup group) {
        group.perms.removeAll(perms);
        return group;
    }

    /**
     * 移除权限
     * @param perm 权限
     * @param group 权限组
     */
    public static PermissionsGroup removePerm(String perm, PermissionsGroup group) {
        group.perms.remove(perm);
        return group;
    }

    /**
     * 修改默认权限组
     * @param group 权限组
     * @return true代表成功，false代表失败
     */
    public static Boolean modifyDefaultGroup(PermissionsGroup group) {
        if (!groups.contains(group)) return false;
        if (isDefault(group)) return false;
        groups.get(groups.indexOf(getDefaultGroup())).isDefault = false;
        groups.get(groups.indexOf(group)).isDefault = true;
        return true;
    }

    /**
     * 获取默认权限组
     * @return 权限组
     */
    public static PermissionsGroup getDefaultGroup() {
        PermissionsGroup group = new PermissionsGroup();
        for (int i = 0; i < groups.size();i++) {
            if (isDefault(groups.get(i))) {
                group = groups.get(i);
                i = groups.size();
            }
        }
        return group;
    }

    /**
     * 获取权限组的权限
     * @param group 权限组
     * @return 权限
     */
    public static List<String> getGroupPerms(PermissionsGroup group) {
        return group.getGroupPerms();
    }

    /**
     * 获取权限组的权限
     * @return 权限
     */
    public List<String> getGroupPerms() {
        return this.perms;
    }

    /**
     * 权限组是否拥有权限
     * @param perm 权限
     * @return true代表有，false代表失败
     */
    public Boolean hasPerm(String perm) {
        boolean hasPerm = false;
        if (!this.perms.contains(perm)) {
            for (String s : this.perms) {
                if ((s.lastIndexOf("*") == s.length()-1)) {
                    if (s.equals("*")) {
                        hasPerm = true;
                    } else {
                        List<String> GroupPerm = new ArrayList<>(List.of(s.split("\\.")));
                        GroupPerm.remove("*");
                        List<String> Perm = new ArrayList<>(List.of(perm.split("\\.")));
                        for (int i = Perm.size(); GroupPerm.size() < i;i--) {
                            Perm.remove(i - 1);
                            if (Objects.equals(Perm.toString(), GroupPerm.toString())) {
                                hasPerm = true;
                            }
                        }
                    }
                }
            }
        } else {
            hasPerm = true;
        }
        return hasPerm;
    }

    /**
     * 权限组是否拥有权限
     * @param perm 权限
     * @param group 权限组
     * @return true代表有，false代表失败
     */
    public static Boolean hasPerm(String perm, PermissionsGroup group) {
        return group.hasPerm(perm);
    }
}
