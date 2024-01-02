package io.github.minecraftchampions.dodoopenjava.permissions.data;

import io.github.minecraftchampions.dodoopenjava.permissions.Group;
import io.github.minecraftchampions.dodoopenjava.permissions.GroupManager;
import io.github.minecraftchampions.dodoopenjava.permissions.User;
import io.github.minecraftchampions.dodoopenjava.permissions.UserManager;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.ConfigUtil;
import io.github.minecraftchampions.dodoopenjava.utils.Equal;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Json文件存储
 */
public class JsonData extends PermData {
    /**
     * 初始化
     */
    public JsonData() {
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
        //获取文件内容
        JSONObject groupJson = getGroupFileData();
        JSONObject userJson = getUserFileData();
        //键数组
        Set<String> groupSet = groupJson.getJSONObject("Groups").keySet();
        List<Group> groups = new ArrayList<>();
        io.github.minecraftchampions.dodoopenjava.permissions.Group defaultGroup = null; //保证重新加载
        for (String group : groupSet) {
            //获取一个组的权限
            List<String> perms = BaseUtil.toStringList(groupJson.getJSONObject("Groups").
                    getJSONObject(group).getJSONArray("perms").toList());
            io.github.minecraftchampions.dodoopenjava.permissions.Group g = new Group(group);
            for (String perm : perms) {
                //增加权限
                g.addPermission(perm);
            }

            if (groupJson.getJSONObject("Groups").getJSONObject(group).getBoolean("isDefault")) {
                if (defaultGroup != null) {
                    System.out.println("两个重复的默认组");
                } else {
                    defaultGroup = g;
                }
            }
            groups.add(g);//将组添加到集合
        }
        //保证重新加载
        GroupManager.setGroups(new TreeMap<>());
        groups.stream().filter(group ->
                        !groupJson.getJSONObject("Groups")
                                .getJSONObject(group.getName()).keySet().contains("extend"))
                .forEach(GroupManager::addGroup);
        groups.stream().filter(group ->
                        groupJson.getJSONObject("Groups")
                                .getJSONObject(group.getName()).keySet().contains("extend"))
                .forEach(group -> {
                    for (String s : BaseUtil.toStringList(groupJson.getJSONObject("Groups")
                            .getJSONObject(group.getName()).getJSONArray("extend").toList())) {
                        List<Group> list = new ArrayList<>(groups);
                        list.remove(group);
                        if (list.stream().anyMatch(g -> Equal.of(g.getName()).equal(s).isEqual()))
                            group.addInherits(list.stream().filter(g -> Objects.equals(g.getName(), s))
                                    .toList().get(0));
                        GroupManager.addGroup(group);
                    }
                });
        //保证数据重加载
        GroupManager.setDefaultGroup(defaultGroup);
        //同上
        Set<String> userSet = userJson.getJSONObject("Users").keySet();
        UserManager.setUsers(new TreeMap<>());
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
        ConfigUtil.saveToFile(group.toString(), Group);
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
        ConfigUtil.saveToFile(user.toString(), User);
    }

    /**
     * 获取权限组文件数据
     *
     * @return JSON对象
     */
    public JSONObject getGroupFileData() {
        return new JSONObject(Objects.requireNonNull(ConfigUtil.readFile(Group)));
    }

    /**
     * 获取用户文件数据
     *
     * @return JSON对象
     */
    public JSONObject getUserFileData() {
        return new JSONObject(Objects.requireNonNull(ConfigUtil.readFile(User)));
    }
}
