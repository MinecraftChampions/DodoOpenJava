package io.github.minecraftchampions.dodoopenjava.permissions.data;

import io.github.minecraftchampions.dodoopenjava.configuration.util.ConfigUtil;
import io.github.minecraftchampions.dodoopenjava.permissions.Group;
import io.github.minecraftchampions.dodoopenjava.permissions.GroupManager;
import io.github.minecraftchampions.dodoopenjava.permissions.User;
import io.github.minecraftchampions.dodoopenjava.permissions.UserManager;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.XmlUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * XML�ļ��洢
 * @author qscbm187531
 */
public class XmlData extends PermData {
    public static File User;
    public static File Group;

    /**
     * ��ʼ��
     */
    public static void init() {
        //����JsonData��ע��
        File Config = new File(ConfigUtil.getJarPath() + "permissions/");
        if (!Config.exists()) {
            Config.mkdir();
        }

        Group = new File(ConfigUtil.getJarPath() + "permissions/permGroup.xml");
        if (!Group.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permGroup.xml", Group.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        User = new File(ConfigUtil.getJarPath() + "permissions/permUser.xml");
        if (!User.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permUser.xml", User.getPath());
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
            io.github.minecraftchampions.dodoopenjava.permissions.Group g = new Group(group);
            if (groupJson.getJSONObject("Groups").getJSONObject(g.getName()).get("perms") instanceof JSONArray array) {
                List<String> perms = BaseUtil.toStringList(array.toList());
                for (String perm : perms) {
                    g.addPermission(perm);
                }
            } else {
                g.addPermission(groupJson.getJSONObject("Groups").getJSONObject(g.getName()).getString("perms"));
            }

            if (groupJson.getJSONObject("Groups").getJSONObject(group).getBoolean("isDefault")) {
                if (defaultGroup != null) {
                    System.out.println("�����ظ���Ĭ����");
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
                if (groupJson.getJSONObject("Groups").getJSONObject(name).get("extend") instanceof JSONArray array) {
                    for (String s : BaseUtil.toStringList(array.toList())) {
                        List<Group> list = new ArrayList<>(groups);
                        list.remove(group);
                        for (io.github.minecraftchampions.dodoopenjava.permissions.Group g : list) {
                            if (Objects.equals(g.getName(), s)) {
                                group.addInherits(g);
                                break;
                            }
                        }
                    }
                } else {
                    group.addInherits(GroupManager.getGroup(groupJson.getJSONObject("Groups").getJSONObject(name).getString("extend")));
                }
            }
            GroupManager.addGroup(group);
        }
        GroupManager.setDefaultGroup(defaultGroup);
        UserManager.setUsers(new TreeMap<>());
        for (Object object : userJson.getJSONObject("Users").getJSONArray("User")) {
            if (object instanceof JSONObject jsonObject) {
                io.github.minecraftchampions.dodoopenjava.permissions.User user = new User(jsonObject.getString("DodoId"));
                Group group = GroupManager.getGroup(jsonObject.getString("Group"));
                if (jsonObject.get("perms") instanceof JSONArray array) {
                    for (String perm : BaseUtil.toStringList(array.toList())) {
                        user.addPermission(perm);
                    }
                } else {
                    user.addPermission(jsonObject.getString("perms"));
                }
                user.setGroup(group);
                UserManager.addUser(user);
            }
        }
    }

    public static void saveToFile() throws IOException {
        Map<String,Group> map = GroupManager.getGroups();
        JSONObject group = new JSONObject();
        group.put("Groups",new JSONObject());
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
        ConfigUtil.saveToFile(XmlUtil.jsonToXml(group),Group);
        Map<String,User> userMap = UserManager.getUsers();
        JSONObject user = new JSONObject();
        user.put("Users",new JSONObject());
        userMap.forEach((key, value) -> {
            String name = value.getLastName();
            List<String> perms = value.getPermissions();
            String userName = value.getGroupName();
            JSONObject data = new JSONObject();
            data.put("Group", userName);
            data.put("perms", perms);
            user.getJSONObject("Users").put(name, data);
        });
        ConfigUtil.saveToFile(XmlUtil.jsonToXml(user),User);
    }

    /**
     * ��ȡȨ�����ļ�
     * @return JSON����
     */
    public static JSONObject getGroupFile() {
        return XmlUtil.toJSONObject(ConfigUtil.readFile(Group));
    }

    /**
     * ��ȡ�û��ļ�
     * @return JSON����
     */
    public static JSONObject getUserFile() {
        return XmlUtil.toJSONObject(ConfigUtil.readFile(User));
    }
}