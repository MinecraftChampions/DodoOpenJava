package io.github.mcchampions.DodoOpenJava.Permissions.Data;

import io.github.mcchampions.DodoOpenJava.Permissions.PermissionsGroup;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.ConfigUtil;
import org.json.JSONObject;
import org.tomlj.Toml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Toml文件存储方式
 */
public class TomlData {
    public static File User;
    public static File Group;

    /**
     * 初始化
     * @throws IOException 失败时抛出
     */
    public static void init() throws IOException {
        File Config = new File(ConfigUtil.getJarPath() + "permissions/");
        if (!Config.exists()) {
            Config.mkdir();
        }

        Group = new File(ConfigUtil.getJarPath() + "permissions/permGroup.toml");
        if (!Group.exists()) {
            ConfigUtil.copyResourcesToFile("permissions/permGroup.toml", Group.getPath());
        }

        User = new File(ConfigUtil.getJarPath() + "permissions/permUser.toml");
        if (!User.exists()) {
            ConfigUtil.copyResourcesToFile("permissions/permUser.toml", User.getPath());
        }

        List<PermissionsGroup> groups = new ArrayList<>();
        for (int i = 0;i<getGroupFile().getJSONObject("Groups").keySet().size();i++) {
            String name = getGroupFile().getJSONObject("Groups").keySet().stream().toList().get(i);
            List<String> perms = BaseUtil.toStringList(getGroupFile().getJSONObject("Groups").getJSONObject(name).getJSONArray("perms").toList());
            Boolean isDefault = getGroupFile().getJSONObject("Groups").getJSONObject(name).getBoolean("isDefault");
            groups.add(new PermissionsGroup(perms,isDefault,name));
        }
        PermissionsGroup.addGroups(groups);

        for (int i = 0 ; i < getUserFile().getJSONObject("Users").keySet().size(); i++) {
            String DodoId = getGroupFile().getJSONObject("Users").keySet().stream().toList().get(i);
            List<String> perms = BaseUtil.toStringList(getGroupFile().getJSONObject("Users").getJSONObject(DodoId).getJSONArray("perms").toList());
            String group = getGroupFile().getJSONObject("Groups").getJSONObject(DodoId).getString("Group");
            PermissionsGroup Group = new PermissionsGroup();
            for (int I = 0; I < PermissionsGroup.getGroups().size();I++) {
                if (Objects.equals(PermissionsGroup.getGroups().get(I).getName(), group)) {
                    Group = PermissionsGroup.getGroups().get(I);
                    break;
                }
            }
            io.github.mcchampions.DodoOpenJava.Permissions.User.editUserGroup(DodoId,Group);
            io.github.mcchampions.DodoOpenJava.Permissions.User.addPerm(DodoId,perms);
        }
    }

    /**
     * 获取权限组文件
     * @return json对象
     */
    public static JSONObject getGroupFile() throws IOException {
        return new JSONObject(Toml.parse(Paths.get(Group.getPath())));
    }

    /**
     * 获取用户文件
     * @return json对象
     */
    public static JSONObject getUserFile() throws IOException {
        return new JSONObject(Toml.parse(Paths.get(User.getPath())));
    }
}
