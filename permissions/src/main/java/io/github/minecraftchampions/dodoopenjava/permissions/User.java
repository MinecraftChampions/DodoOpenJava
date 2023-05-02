package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.utils.MapUtil;

import java.util.*;

/**
 * �û�
 * @author qscbm187531
 */
public class User {
    public static HashMap<String, Group> UserGroup = new HashMap<>();

    public static HashMap<String,List<String>> UserPerms = new HashMap<>();

    /**
     * ��ȡ�û���Ȩ����
     * @param DodoId DodoID
     * @return Ȩ����
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
     * �༭�û�Ȩ����
     * @param DodoId DodoID
     * @param group Ȩ����
     * @return false����ʧ�ܣ�true����ɹ�
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
     * ����Ȩ��
     * @param DodoId DodoID
     * @param perm Ȩ��
     * @return false����ԭ���Ѿ������Ȩ���ˣ�true���ǳɹ�
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
     * ����Ȩ��
     * @param DodoId DodoID
     * @param Perms Ȩ��
     * @return false����ԭ���Ѿ�����ЩȨ���ˣ�true���ǳɹ�
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
     * �Ƴ�Ȩ��
     * @param DodoId DodoID
     * @param perm Ȩ��
     * @return false����ԭ��û�����Ȩ���ˣ�true���ǳɹ�
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
     * �Ƴ�Ȩ��
     * @param DodoId DodoID
     * @param Perms Ȩ��
     * @return false����ԭ��û����ЩȨ���ˣ�true���ǳɹ�
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
     * �ж��û��Ƿ������Ȩ��
     * @param DodoId DodoId
     * @param perm Ȩ��
     * @return �Ƿ�ӵ��
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
