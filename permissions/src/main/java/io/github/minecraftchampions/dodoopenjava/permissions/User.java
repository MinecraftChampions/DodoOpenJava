package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.utils.MapUtil;

import java.util.*;

/**
 * 用户
 * @author qscbm187531
 */
public class User {
    public static HashMap<String, Group> UserGroup = new HashMap<>();

    public static HashMap<String,List<String>> UserPerms = new HashMap<>();

    /**
     * 获取用户的权限组
     * @param DodoId DodoID
     * @return 权限组
     */
    public static Group getUserGroup(String DodoId) {
        List<List<Object>> list= MapUtil.ergodicHashMaps(UserGroup);
        Group group = Group.getDefaultGroup();
        for (List<Object> objects : list) {
            if (objects.get(0) == DodoId) {
                group = (Group) objects.get(1);
            }
        }
        return group;
    }

    /**
     * 编辑用户权限组
     * @param DodoId DodoID
     * @param group 权限组
     * @return false代表失败，true代表成功
     */
    public static Boolean editUserGroup(String DodoId, Group group) {
        if (Group.getGroups().contains(group)) return false;
        List<List<Object>> list= MapUtil.ergodicHashMaps(UserGroup);
        boolean hasUser = false;
        for (List<Object> objects : list) {
            if (objects.get(0) == DodoId) {
                hasUser = true;
                break;
            }
        }
        if (!hasUser) UserGroup.put(DodoId, Group.getDefaultGroup());
        UserGroup.replace(DodoId,group);
        return true;
    }

    /**
     * 增加权限
     * @param DodoId DodoID
     * @param perm 权限
     * @return false代表原本已经有这个权限了，true就是成功
     */
    public static Boolean addPerm(String DodoId, String perm) {
        List<List<Object>> list= MapUtil.ergodicHashMaps(UserPerms);
        List<String> perms = new ArrayList<>();
        boolean hasUser = false;
        boolean hasPerm = false;
        for (List<Object> objects : list) {
            if (objects.get(0) == DodoId) {
                hasUser = true;
                perms = (List<String>) objects.get(1);
                if (perms.contains(perm)) {
                    hasPerm = true;
                }
                break;
            }
        }
        if (!hasUser) UserPerms.put(DodoId,new ArrayList<>());
        if (hasPerm) return false;
        perms.add(perm);
        UserPerms.replace(DodoId,perms);
        return true;
    }

    /**
     * 增加权限
     * @param DodoId DodoID
     * @param Perms 权限
     * @return false代表原本已经有这些权限了，true就是成功
     */
    public static Boolean addPerm(String DodoId, List<String> Perms) {
        List<List<Object>> list= MapUtil.ergodicHashMaps(UserPerms);
        List<String> perms = new ArrayList<>();
        boolean hasUser = false;
        boolean hasPerm = false;
        for (List<Object> objects : list) {
            if (objects.get(0) == DodoId) {
                hasUser = true;
                perms = (List<String>) objects.get(1);
                if (new HashSet<>(perms).containsAll(Perms)) {
                    hasPerm = true;
                }
                break;
            }
        }
        if (!hasUser) UserPerms.put(DodoId,new ArrayList<>());
        if (hasPerm) return false;
        perms.addAll(Perms);
        UserPerms.replace(DodoId,perms);
        return true;
    }

    /**
     * 移除权限
     * @param DodoId DodoID
     * @param perm 权限
     * @return false代表原本没有这个权限了，true就是成功
     */
    public static Boolean removePerm(String DodoId, String perm) {
        List<List<Object>> list= MapUtil.ergodicHashMaps(UserPerms);
        List<String> perms = new ArrayList<>();
        boolean hasUser = false;
        boolean hasPerm = false;
        for (List<Object> objects : list) {
            if (objects.get(0) == DodoId) {
                hasUser = true;
                perms = (List<String>) objects.get(1);
                if (perms.contains(perm)) {
                    hasPerm = true;
                }
                break;
            }
        }
        if (!hasUser) {
            UserPerms.put(DodoId,new ArrayList<>());
            return false;
        }
        if (!hasPerm) return false;
        perms.remove(perm);
        UserPerms.replace(DodoId,perms);
        return true;
    }

    /**
     * 移除权限
     * @param DodoId DodoID
     * @param Perms 权限
     * @return false代表原本没有这些权限了，true就是成功
     */
    public static Boolean removePerm(String DodoId, List<String> Perms) {
        List<List<Object>> list= MapUtil.ergodicHashMaps(UserPerms);
        List<String> perms = new ArrayList<>();
        boolean hasUser = false;
        boolean hasPerm = false;
        for (List<Object> objects : list) {
            if (objects.get(0) == DodoId) {
                hasUser = true;
                perms = (List<String>) objects.get(1);
                if (new HashSet<>(perms).containsAll(Perms)) {
                    hasPerm = true;
                }
                break;
            }
        }
        if (!hasUser) {
            UserPerms.put(DodoId,new ArrayList<>());
            return false;
        }
        if (!hasPerm) return false;
        perms.removeAll(Perms);
        UserPerms.replace(DodoId,perms);
        return true;
    }

    /**
     * 判断用户是否有这个权限
     * @param DodoId DodoId
     * @param perm 权限
     * @return 是否拥有
     */
    public static Boolean hasPerm(String DodoId,String perm) {
        if (perm == null) return true;
        List<List<Object>> list= MapUtil.ergodicHashMaps(UserPerms);
        List<String> perms = new ArrayList<>();
        boolean hasUser = false;
        for (List<Object> objects : list) {
            if (objects.get(0) == DodoId) {
                hasUser = true;
                perms = (List<String>) objects.get(1);
            }
        }
        if (!hasUser) {
            UserPerms.put(DodoId,new ArrayList<>());
            return false;
        }

        boolean hasPerm = false;

        if (!perms.contains(perm)) {
            for (String s : perms) {
                if ((s.lastIndexOf('*') == s.length()-1)) {
                    if (s.equals("*")) {
                        hasPerm = true;
                    } else {
                        if (getUserGroup(DodoId).hasPerm(perm)) {
                            hasPerm = true;
                        } else {
                            List<String> UserPerm = new ArrayList<>(List.of(s.split("\\.")));
                            UserPerm.remove("*");
                            List<String> Perm = new ArrayList<>(List.of(perm.split("\\.")));
                            for (int i = Perm.size(); UserPerm.size() < i; i--) {
                                Perm.remove(i - 1);
                                if (Objects.equals(Perm.toString(), UserPerm.toString())) {
                                    hasPerm = true;
                                }
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
}
