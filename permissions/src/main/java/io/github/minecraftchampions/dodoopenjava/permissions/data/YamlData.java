package io.github.minecraftchampions.dodoopenjava.permissions.data;

import io.github.minecraftchampions.dodoopenjava.configuration.file.FileConfiguration;
import io.github.minecraftchampions.dodoopenjava.configuration.util.ConfigUtil;
import io.github.minecraftchampions.dodoopenjava.permissions.Group;
import io.github.minecraftchampions.dodoopenjava.permissions.GroupManager;
import io.github.minecraftchampions.dodoopenjava.permissions.User;
import io.github.minecraftchampions.dodoopenjava.permissions.UserManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class YamlData extends PermData {
    public static File User;
    public static File Group;

    public static void saveToFile() throws IOException {
        FileConfiguration groupFile = getGroupFile();
        Map<String,Group> map = GroupManager.getGroups();
        map.forEach((key,value) -> {
            List<String> perms = value.getPermissions();
            List<String> extend = value.getInherits();
            boolean isDefault = value.isDefault();
            groupFile.set("Groups." + key + ".isDefault",isDefault);
            groupFile.set("Groups." + key + ".perms",perms);
            groupFile.set("Groups." + key + ".extend",extend);
        });
        groupFile.save(Group);
        FileConfiguration userFile = getUserFile();
        Map<String,User> hashMap = UserManager.getUsers();
        hashMap.forEach((key,value) -> {
            List<String> perms = value.getPermissions();
            String group = value.getGroupName();
            userFile.set("Users." + key + ".Group",group);
            userFile.set("Users." + key + ".perms",perms);
        });
        userFile.save(User);
    }

    public static void init() {
        //ÀàËÆJsonDataµÄ×¢ÊÍ
        File Config = new File(ConfigUtil.getJarPath() + "permissions/");
        if (!Config.exists()) {
            Config.mkdir();
        }

        Group = new File(ConfigUtil.getJarPath() + "permissions/permGroup.yml");
        if (!Group.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permGroup.yml",Group.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        User = new File(ConfigUtil.getJarPath() + "permissions/permUser.yml");
        if (!User.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permUser.yml",User.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        GroupManager.setGroupsFile(Group);
        UserManager.setUsersFile(User);
        GroupManager.setGroups(new TreeMap<>());
        for (int i = 0;i<getGroupFile().getConfigurationSection("Groups").getKeys(false).size();i++) {
            String name = getGroupFile().getConfigurationSection("Groups").getKeys(false).stream().toList().get(i);
            List<String> perms = getGroupFile().getStringList("Groups." + name + ".perms");
            Boolean isDefault = getGroupFile().getBoolean("Groups." + name + ".isDefault");
            List<String> extendsGroup = getGroupFile().getStringList("Groups."+name+"extend");
            io.github.minecraftchampions.dodoopenjava.permissions.Group group = new Group(name);
            for (String perm : perms) {
                group.addPermission(perm);
            }
            for (String g : extendsGroup) {
                group.addInherits(GroupManager.getGroup(g));
            }
            GroupManager.addGroup(group);
            if (isDefault) {
                GroupManager.setDefaultGroup(GroupManager.getGroup(name));
            }
        }

        UserManager.setUsers(new TreeMap<>());
        for (int i = 0 ; i < getUserFile().getConfigurationSection("Users").getKeys(false).size(); i++) {
            String DodoId = getUserFile().getConfigurationSection("Users").getKeys(false).stream().toList().get(i);
            List<String> perms = getUserFile().getStringList("Users." + DodoId + ".perms");
            String group = getUserFile().getString("Users." + DodoId + ".Group");
            User user = new User(DodoId);
            for (String perm : perms) {
                user.addPermission(perm);
            }
            user.setGroup(GroupManager.getGroup(group));
            UserManager.addUser(user);
        }
    }

    public static FileConfiguration getGroupFile() {
        return ConfigUtil.load(Group);
    }

    public static FileConfiguration getUserFile() {
        return ConfigUtil.load(User);
    }
}