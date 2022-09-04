package io.github.mcchampions.DodoOpenJava.permissions.Data;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.ConfigUtil;
import io.github.mcchampions.DodoOpenJava.permissions.PermissionsGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Json文件存储
 */
public class Json {
    public static File User;
    public static File Group;

    /**
     * 初始化
     * @throws IOException
     */
    public static void init() throws IOException {
        File Config = new File(ConfigUtil.getJarPath() + "permissions/");
        if (!Config.exists()) {
            Config.mkdir();
        }

        Group = new File(ConfigUtil.getJarPath() + "permissions/permGroup.json");
        if (!Group.exists()) {
            ConfigUtil.copyResourcesToFile("permissions/permGroup.json", Group.getPath());
        }

        User = new File(ConfigUtil.getJarPath() + "permissions/permUser.json");
        if (!User.exists()) {
            ConfigUtil.copyResourcesToFile("permissions/permUser.json", Group.getPath());
        }

        List<PermissionsGroup> groups = new ArrayList<>();
        for (int i = 0;i<getGroupFile().getJSONObject("Groups").keySet().size();i++) {
            String name = getGroupFile().getJSONObject("Groups").keySet().stream().toList().get(i);
            List<String> perms = getGroupFile().getJSONObject("Groups").getJSONObject(name).getJSONArray("perms").toList(String.class);
            Boolean isDefault = getGroupFile().getJSONObject("Groups").getJSONObject(name).getBoolean("isDefault");
            groups.add(new PermissionsGroup(perms,isDefault,name));
        }
        PermissionsGroup.addGroups(groups);

        for (int i = 0 ; i < getUserFile().getJSONObject("Users").keySet().size(); i++) {
            String DodoId = getGroupFile().getJSONObject("Users").keySet().stream().toList().get(i);
            List<String> perms = getGroupFile().getJSONObject("Users").getJSONObject(DodoId).getJSONArray("perms").toList(String.class);
            String group = getGroupFile().getJSONObject("Groups").getJSONObject(DodoId).getString("Group");
            PermissionsGroup Group = new PermissionsGroup();
            for (int I = 0; I < PermissionsGroup.getGroups().size();I++) {
                if (Objects.equals(PermissionsGroup.getGroups().get(I).getName(), group)) {
                    Group = PermissionsGroup.getGroups().get(I);
                    break;
                }
            }
            io.github.mcchampions.DodoOpenJava.permissions.User.editUserGroup(DodoId,Group);
            io.github.mcchampions.DodoOpenJava.permissions.User.addPerm(DodoId,perms);
        }
    }

    /**
     * 获取权限组文件
     * @return JSON对象
     */
    public static JSONObject getGroupFile() {
        return JSONObject.parseObject(ConfigUtil.readJsonFile(Group));
    }

    /**
     * 获取用户文件
     * @return JSON对象
     */
    public static JSONObject getUserFile() {
        return JSONObject.parseObject(ConfigUtil.readJsonFile(User));
    }
}
