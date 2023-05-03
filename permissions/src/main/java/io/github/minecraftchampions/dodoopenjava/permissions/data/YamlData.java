package io.github.minecraftchampions.dodoopenjava.permissions.data;

import io.github.minecraftchampions.dodoopenjava.configuration.util.ConfigUtil;
import io.github.minecraftchampions.dodoopenjava.configuration.file.FileConfiguration;
import io.github.minecraftchampions.dodoopenjava.permissions.Group;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class YamlData {
    public static File User;
    public static File Group;

    public static void init() {
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

        List<io.github.minecraftchampions.dodoopenjava.permissions.Group> groups = new ArrayList<>();
        for (int i = 0;i<getGroupFile().getConfigurationSection("Groups").getKeys(false).size();i++) {
            String name = getGroupFile().getConfigurationSection("Groups").getKeys(false).stream().toList().get(i);
            List<String> perms = getGroupFile().getStringList("Groups." + name + ".perms");
            Boolean isDefault = getGroupFile().getBoolean("Groups." + name + ".isDefault");
            groups.add(new Group(perms,isDefault,name));
        }
        io.github.minecraftchampions.dodoopenjava.permissions.Group.addGroups(groups);

        for (int i = 0 ; i < getUserFile().getConfigurationSection("Users").getKeys(false).size(); i++) {
            String DodoId = getUserFile().getConfigurationSection("Users").getKeys(false).stream().toList().get(i);
            List<String> perms = getUserFile().getStringList("Users." + DodoId + ".perms");
            String group = getUserFile().getString("Users." + DodoId + ".Group");
            io.github.minecraftchampions.dodoopenjava.permissions.Group Group = new Group();
            for (int I = 0; I < io.github.minecraftchampions.dodoopenjava.permissions.Group.getGroups().size(); I++) {
                if (Objects.equals(io.github.minecraftchampions.dodoopenjava.permissions.Group.getGroups().get(I).getName(), group)) {
                    Group = io.github.minecraftchampions.dodoopenjava.permissions.Group.getGroups().get(I);
                    break;
                }
            }
            io.github.minecraftchampions.dodoopenjava.permissions.User.editUserGroup(DodoId,Group);
            io.github.minecraftchampions.dodoopenjava.permissions.User.addPerm(DodoId,perms);
        }
    }

    public static FileConfiguration getGroupFile() {
        return ConfigUtil.load(Group);
    }

    public static FileConfiguration getUserFile() {
        return ConfigUtil.load(User);
    }
}
