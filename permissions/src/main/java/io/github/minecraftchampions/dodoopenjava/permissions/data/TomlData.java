package io.github.minecraftchampions.dodoopenjava.permissions.data;

import io.github.minecraftchampions.dodoopenjava.configuration.util.ConfigUtil;
import io.github.minecraftchampions.dodoopenjava.permissions.*;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import org.json.JSONObject;
import org.tomlj.Toml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Toml文件存储方式
 */
public class TomlData extends PermData {
    /**
     * 初始化
     */
    public TomlData() {
        //类似JsonData的注释
        File Config = new File(ConfigUtil.getJarPath() + "permissions/");
        if (!Config.exists()) {
            Config.mkdir();
        }

        Group = new File(ConfigUtil.getJarPath() + "permissions/permGroup.json");
        if (!Group.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permGroup.json", Group.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        User = new File(ConfigUtil.getJarPath() + "permissions/permUser.json");
        if (!User.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permUser.json", User.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        GroupManager.setGroupsFile(Group);
        UserManager.setUsersFile(User);
        JSONObject groupJson = getGroupFile();
        JSONObject userJson = getUserFile();
        Set<String> groupSet = groupJson.getJSONObject("Groups").keySet();
        List<Group> groups = new ArrayList<>();
        io.github.minecraftchampions.dodoopenjava.permissions.Group defaultGroup = null;
        for (String group : groupSet) {
            List<String> perms = BaseUtil.toStringList(groupJson.getJSONObject("Groups").
                    getJSONObject(group).getJSONArray("perms").toList());
            io.github.minecraftchampions.dodoopenjava.permissions.Group g = new Group(group);
            for (String perm : perms) {
                g.addPermission(perm);
            }

            if (groupJson.getJSONObject("Groups").getJSONObject(group).getBoolean("isDefault")) {
                if (defaultGroup != null) {
                    System.out.println("两个重复的默认组");
                } else {
                    defaultGroup = g;
                }
            }
            groups.add(g);
        }
        GroupManager.setGroups(new TreeMap<>());
        for (io.github.minecraftchampions.dodoopenjava.permissions.Group group : groups) {
            String name = group.getName();
            if (groupJson.getJSONObject("Groups").getJSONObject(name).keySet().contains("extend")) {
                for (String s : BaseUtil.toStringList(groupJson.getJSONObject("Groups")
                        .getJSONObject(name).getJSONArray("extend").toList())) {
                    List<Group> list = groups;
                    list.remove(group);
                    for (io.github.minecraftchampions.dodoopenjava.permissions.Group g : list) {
                        if (Objects.equals(g.getName(), s)) {
                            group.addInherits(g);
                            break;
                        }
                    }
                }
            }
            GroupManager.addGroup(group);
        }
        GroupManager.setDefaultGroup(defaultGroup);
        Set<String> userSet = userJson.getJSONObject("Users").keySet();
        GroupManager.setGroups(new TreeMap<>());
        for (String s : userSet) {
            io.github.minecraftchampions.dodoopenjava.permissions.User user = new User(s);
            Group group = GroupManager.getGroup(userJson.getJSONObject("Users").getJSONObject(s).getString("Group"));
            for (String perm : BaseUtil.toStringList(userJson.getJSONObject("Users").getJSONObject(s).getJSONArray("perms").toList())) {
                user.addPermission(perm);
            }
            user.setGroup(group);
            UserManager.addUser(user);
        }
    }

    /**
     * 保存到文件
     *
     * @throws IOException 异常
     */
    @Override
    public void saveToFile() throws IOException {
        Map<String, Group> map = GroupManager.getGroups();
        JSONObject group = new JSONObject();
        group.put("Groups", new JSONObject());
        map.forEach((key, value) -> {
            String name = value.getName();
            boolean isDefault = value.isDefault();
            List<String> perms = value.getPermissions();
            List<String> extendGroups = value.getInherits();
            JSONObject data = new JSONObject();
            data.put("isDefault", isDefault);
            data.put("perms", perms);
            data.put("extend", extendGroups);
            group.getJSONObject("Groups").put(name, data);
        });
        ConfigUtil.saveToFile(Util.toToml(group.toString()), Group);
        Map<String, User> userMap = UserManager.getUsers();
        JSONObject user = new JSONObject();
        user.put("Users", new JSONObject());
        userMap.forEach((key, value) -> {
            String name = value.getLastName();
            List<String> perms = value.getPermissions();
            String userName = value.getGroupName();
            JSONObject data = new JSONObject();
            data.put("Group", userName);
            data.put("perms", perms);
            user.getJSONObject("Users").put(name, data);
        });
        ConfigUtil.saveToFile(Util.toToml(user.toString()), User);
    }


    /**
     * 获取权限组文件
     *
     * @return json对象
     */
    public JSONObject getGroupFile() {
        try {
            return new JSONObject(Toml.parse(Paths.get(Group.getPath())).toMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取用户文件
     *
     * @return json对象
     */
    public JSONObject getUserFile() {
        try {
            return new JSONObject(Toml.parse(Paths.get(User.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
