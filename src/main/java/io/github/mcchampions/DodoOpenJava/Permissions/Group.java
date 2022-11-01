package io.github.mcchampions.DodoOpenJava.Permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限组
 * @author qscbm187531
 */
public class Group {
    public static List<Group> groups = new ArrayList<>();

    public List<String> perms = new ArrayList<>();

    public Boolean isDefault;

    public String name;

    /**
     * 构造函数
     * @param perms 权限
     * @param isDefault 是否默认
     * @param name 名字
     */
    public Group(List<String> perms, Boolean isDefault, String name) {
        this.perms = perms;
        this.isDefault = isDefault;
        this.name = name;
    }

    /**
     * 构造函数
     * @param perms 权限
     * @param name 名字
     */
    public Group(List<String> perms, String name) {
        this.perms = perms;
        this.isDefault = false;
        this.name = name;
    }

    /**
     * 构造函数
     * @param isDefault 是否默认
     * @param name 名字
     */
    public Group(Boolean isDefault, String name) {
        this.perms = new ArrayList<>();
        this.isDefault = isDefault;
        this.name = name;
    }

    /**
     * 构造函数
     * @param name 名字
     */
    public Group(String name) {
        this.perms = new ArrayList<>();
        this.isDefault = false;
        this.name = name;
    }

    public Group() {
    }

    /**
     * 获取所有权限组
     * @return 权限组集合
     */
    public static List<Group> getGroups() {
        return groups;
    }

    /**
     * 判断权限组是否为默认权限组
     * @param group 权限组
     * @return true 是，false 不是
     */
    public static Boolean isDefault(Group group) {
        return group.isDefault;
    }
    
    /**
     * 获取权限组名字
     * @param group 权限组
     * @return 名字
     */
    public static String getName(Group group) {
        return group.name;
    }

    /**
     * 获取权限组权限
     * @param group 权限组
     * @return 权限集合
     */
    public static List<String> getPerms(Group group) {
        return group.perms;
    }

    /**
     * 编辑权限组名字
     * @param group 权限组
     * @return 名字
     */
    public static Group editName(String name, Group group) {
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
    public static Group addGroup(Group group) {
        groups.add(group);
        return group;
    }

    /**
     * 增加一堆权限组
     * @param groups 权限组
     */
    public static void addGroups(List<Group> groups) {
        Group.groups.addAll(groups);
    }

    /**
     * 移除一个权限组
     * @param group 权限组
     */
    public static Group removeGroup(Group group) {
        groups.remove(group);
        return group;
    }

    /**
     * 增加一堆权限组
     * @param groups 权限组
     */
    public static void removeGroups(List<Group> groups) {
        Group.groups.removeAll(groups);
    }

    /**
     * 设置权限组
     * @param groups 权限组
     */
    public static void setGroups(List<Group> groups) {
        Group.groups = groups;
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
    public static Group addPerm(String perm, Group group) {
        group.perms.add(perm);
        return group;
    }

    /**
     * 增加权限
     * @param perms 权限
     * @param group 权限组
     */
    public static Group addPerms(List<String> perms, Group group) {
        group.perms.addAll(perms);
        return group;
    }

    /**
     * 设置权限
     * @param perms 权限
     * @param group 权限组
     */
    public static Group setPerms(List<String> perms, Group group) {
        group.perms = perms;
        return group;
    }

    /**
     * 移除权限
     * @param perms 权限
     * @param group 权限组
     */
    public static Group removePerms(List<String> perms, Group group) {
        group.perms.removeAll(perms);
        return group;
    }

    /**
     * 移除权限
     * @param perm 权限
     * @param group 权限组
     */
    public static Group removePerm(String perm, Group group) {
        group.perms.remove(perm);
        return group;
    }

    /**
     * 修改默认权限组
     * @param group 权限组
     * @return true代表成功，false代表失败
     */
    public static Boolean modifyDefaultGroup(Group group) {
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
    public static Group getDefaultGroup() {
        Group group = new Group();
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
    public static List<String> getGroupPerms(Group group) {
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
        if (perm == null) return true;
        boolean hasPerm = false;
        if (!this.perms.contains(perm)) {
            for (String s : this.perms) {
                if ((s.lastIndexOf('*') == s.length()-1)) {
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
    public static Boolean hasPerm(String perm, Group group) {
        return group.hasPerm(perm);
    }
}
