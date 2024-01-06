package io.github.minecraftchampions.dodoopenjava.permissions;

import lombok.Getter;
import lombok.NonNull;

import java.io.File;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 用户管理
 */
public class UserManager {
    public static boolean hasPerm(@NonNull User user, String perm) {
        if (UserManager.users.containsKey(user.getLastName())) {
            UserManager.addUser(user);
        }
        for (String access : user.getPermissions()) {
            if (Util.hasPermission(access, perm)) {
                return true;
            }
        }
        Group g = user.getGroup();
        if (g.hasPerm(perm)) {
            return true;
        }
        for (String s : GroupManager.getGroup(g.getName()).getInherits()) {
            Group now = GroupManager.getGroup(s);
            if (now.hasPerm(perm)) {
                return true;
            }
        }
        return false;
    }

    public static boolean addUser(@NonNull User user) {
        if (users.containsKey(user.getLastName())) {
            return false;
        }
        users.put(user.getLastName(), user);
        return true;
    }

    @Getter
    private static File usersFile;
    private static boolean changed = false;

    @Getter
    private static SortedMap<String, User> users = new TreeMap<>();

    public static void setUsers(@NonNull SortedMap<String, User> users) {
        UserManager.users = users;
    }

    public static User getUser(@NonNull String name) {
        return users.get(name);
    }

    void resetUsers() {
        users.clear();
    }

    public static void setUsersFile(@NonNull File usersFile) {
        UserManager.usersFile = usersFile;
    }

    public static boolean isUsersChanged() {
        return changed;
    }

    void setAllChanged() {
        setUsersChanged(true);
        users.forEach((key, value) -> value.setChanged(true));
    }

    public static void setUsersChanged(boolean c) {
        changed = c;
    }
}
