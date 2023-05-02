package io.github.minecraftchampions.dodoopenjava.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ȩ����
 * @author qscbm187531
 */
public class Group {
    public static List<Group> groups = new ArrayList<>();

    public List<String> perms = new ArrayList<>();

    public Boolean isDefault;

    public String name;

    /**
     * ���캯��
     * @param perms Ȩ��
     * @param isDefault �Ƿ�Ĭ��
     * @param name ����
     */
    public Group(List<String> perms, Boolean isDefault, String name) {
        this.perms = perms;
        this.isDefault = isDefault;
        this.name = name;
    }

    /**
     * ���캯��
     * @param perms Ȩ��
     * @param name ����
     */
    public Group(List<String> perms, String name) {
        this.perms = perms;
        this.isDefault = false;
        this.name = name;
    }

    /**
     * ���캯��
     * @param isDefault �Ƿ�Ĭ��
     * @param name ����
     */
    public Group(Boolean isDefault, String name) {
        this.perms = new ArrayList<>();
        this.isDefault = isDefault;
        this.name = name;
    }

    /**
     * ���캯��
     * @param name ����
     */
    public Group(String name) {
        this.perms = new ArrayList<>();
        this.isDefault = false;
        this.name = name;
    }

    public Group() {
    }

    /**
     * ��ȡ����Ȩ����
     * @return Ȩ���鼯��
     */
    public static List<Group> getGroups() {
        return groups;
    }

    /**
     * �ж�Ȩ�����Ƿ�ΪĬ��Ȩ����
     * @param group Ȩ����
     * @return true �ǣ�false ����
     */
    public static Boolean isDefault(Group group) {
        return group.isDefault;
    }
    
    /**
     * ��ȡȨ��������
     * @param group Ȩ����
     * @return ����
     */
    public static String getName(Group group) {
        return group.name;
    }

    /**
     * ��ȡȨ����Ȩ��
     * @param group Ȩ����
     * @return Ȩ�޼���
     */
    public static List<String> getPerms(Group group) {
        return group.perms;
    }

    /**
     * �༭Ȩ��������
     * @param group Ȩ����
     * @return ����
     */
    public static Group editName(String name, Group group) {
        group.name = name;
        return group;
    }

    /**
     * �༭Ȩ��������
     */
    public void editName(String name) {
        this.name = name;
    }

    /**
     * �ж�Ȩ�����Ƿ�ΪĬ��Ȩ����
     * @return true���ǣ�false�Ͳ���
     */
    public Boolean isDefault() {
        return this.isDefault;
    }

    /**
     * ��ȡȨ��������
     * @return ����
     */
    public String getName() {
        return this.name;
    }

    /**
     * ����һ��Ȩ����
     * @param group Ȩ����
     */
    public static Group addGroup(Group group) {
        groups.add(group);
        return group;
    }

    /**
     * ����һ��Ȩ����
     * @param groups Ȩ����
     */
    public static void addGroups(List<Group> groups) {
        Group.groups.addAll(groups);
    }

    /**
     * �Ƴ�һ��Ȩ����
     * @param group Ȩ����
     */
    public static Group removeGroup(Group group) {
        groups.remove(group);
        return group;
    }

    /**
     * ����һ��Ȩ����
     * @param groups Ȩ����
     */
    public static void removeGroups(List<Group> groups) {
        Group.groups.removeAll(groups);
    }

    /**
     * ����Ȩ����
     * @param groups Ȩ����
     */
    public static void setGroups(List<Group> groups) {
        Group.groups = groups;
    }

    /**
     * ����Ȩ��
     * @param perm Ȩ��
     */
    public void addPerm(String perm) {
        this.perms.add(perm);
    }

    /**
     * ����Ȩ��
     * @param perms Ȩ��
     */
    public void addPerms(List<String> perms) {
        this.perms.addAll(perms);
    }

    /**
     * ����Ȩ��
     * @param perms Ȩ��
     */
    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    /**
     * �Ƴ�Ȩ��
     * @param perms Ȩ��
     */
    public void removePerms(List<String> perms) {
        this.perms.removeAll(perms);
    }

    /**
     * �Ƴ�Ȩ��
     * @param perm Ȩ��
     */
    public void removePerm(String perm) {
        this.perms.remove(perm);
    }

    /**
     * ����Ȩ��
     * @param perm Ȩ��
     * @param group Ȩ����
     */
    public static Group addPerm(String perm, Group group) {
        group.perms.add(perm);
        return group;
    }

    /**
     * ����Ȩ��
     * @param perms Ȩ��
     * @param group Ȩ����
     */
    public static Group addPerms(List<String> perms, Group group) {
        group.perms.addAll(perms);
        return group;
    }

    /**
     * ����Ȩ��
     * @param perms Ȩ��
     * @param group Ȩ����
     */
    public static Group setPerms(List<String> perms, Group group) {
        group.perms = perms;
        return group;
    }

    /**
     * �Ƴ�Ȩ��
     * @param perms Ȩ��
     * @param group Ȩ����
     */
    public static Group removePerms(List<String> perms, Group group) {
        group.perms.removeAll(perms);
        return group;
    }

    /**
     * �Ƴ�Ȩ��
     * @param perm Ȩ��
     * @param group Ȩ����
     */
    public static Group removePerm(String perm, Group group) {
        group.perms.remove(perm);
        return group;
    }

    /**
     * �޸�Ĭ��Ȩ����
     * @param group Ȩ����
     * @return true����ɹ���false����ʧ��
     */
    public static Boolean modifyDefaultGroup(Group group) {
        if (!groups.contains(group)) return false;
        if (isDefault(group)) return false;
        groups.get(groups.indexOf(getDefaultGroup())).isDefault = false;
        groups.get(groups.indexOf(group)).isDefault = true;
        return true;
    }

    /**
     * ��ȡĬ��Ȩ����
     * @return Ȩ����
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
     * ��ȡȨ�����Ȩ��
     * @param group Ȩ����
     * @return Ȩ��
     */
    public static List<String> getGroupPerms(Group group) {
        return group.getGroupPerms();
    }

    /**
     * ��ȡȨ�����Ȩ��
     * @return Ȩ��
     */
    public List<String> getGroupPerms() {
        return this.perms;
    }

    /**
     * Ȩ�����Ƿ�ӵ��Ȩ��
     * @param perm Ȩ��
     * @return true�����У�false����ʧ��
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
     * Ȩ�����Ƿ�ӵ��Ȩ��
     * @param perm Ȩ��
     * @param group Ȩ����
     * @return true�����У�false����ʧ��
     */
    public static Boolean hasPerm(String perm, Group group) {
        return group.hasPerm(perm);
    }
}
