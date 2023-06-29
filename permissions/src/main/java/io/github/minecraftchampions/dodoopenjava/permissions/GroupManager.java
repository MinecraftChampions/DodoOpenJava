package io.github.minecraftchampions.dodoopenjava.permissions;

import java.io.File;
import java.util.SortedMap;
import java.util.TreeMap;

import static io.github.minecraftchampions.dodoopenjava.permissions.Util.comparePermissionString;

/**
 * 权限组管理
 */
public class GroupManager {
    private static Group defaultGroup;
    private static File groupsFile;
    private static boolean changed = false;
    
    private static SortedMap<String, Group> groups = new TreeMap<>();

    public static boolean hasPerm(Group now,String perm) {
        for (String access : getGroup(now.getName()).getPermissions()) {
            if (comparePermissionString(access, perm)) {
                return true;
            }
        }
        return false;
    }

    public static void setGroups(SortedMap<String, Group> groups) {
        GroupManager.groups = groups;
    }

    public static boolean addGroup(Group group) {
        if (groups.containsKey(group.getName())) {
            return false;
        }
        groups.put(group.getName(),group);
        return true;
    }

    public static Group getGroup(String name) {
        return groups.get(name);
    }

    public static Group getDefaultGroup() {
        if (defaultGroup == null) {
            return groups.entrySet().stream().toList().get(0).getValue();
        }
        return defaultGroup;
    }

    public static void setDefaultGroup(Group defaultGroup) {
        GroupManager.defaultGroup = defaultGroup;
    }
    
    public static SortedMap<String, Group> getGroups() {
        return groups;
    }

    public static void resetGroups() {
        groups.clear();
    }
    
    public static File getGroupsFile() {
        return groupsFile;
    }

    public static boolean groupExists(String groupName) {
        return getGroups().containsKey(groupName);
    }
    
    public static void setGroupsFile(File groupsFile) {
        GroupManager.groupsFile = groupsFile;
    }
    
    public static boolean isGroupsChanged() {
        return changed;
    }
    
    public static void setAllChanged() {
        setGroupsChanged(true);
        groups.entrySet().forEach(entry -> entry.getValue().setChanged(true));
    }
 
    public static void setGroupsChanged(boolean changed) {
        GroupManager.changed = changed;
    }
}
