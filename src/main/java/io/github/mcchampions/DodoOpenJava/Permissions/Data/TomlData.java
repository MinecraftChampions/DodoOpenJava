package io.github.mcchampions.DodoOpenJava.Permissions.Data;

import io.github.mcchampions.DodoOpenJava.Permissions.Group;
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
 * @author qscbm187531
 */
public class TomlData {
    public static File User;
    public static File Group;

    /**
     * 初始化
     */
    public static void init() {
        File Config = new File(ConfigUtil.getJarPath() + "permissions/");
        if (!Config.exists()) {
            Config.mkdir();
        }

        Group = new File(ConfigUtil.getJarPath() + "permissions/permGroup.toml");
        if (!Group.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permGroup.toml", Group.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        User = new File(ConfigUtil.getJarPath() + "permissions/permUser.toml");
        if (!User.exists()) {
            try {
                ConfigUtil.copyResourcesToFile("permissions/permUser.toml", User.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<io.github.mcchampions.DodoOpenJava.Permissions.Group> groups = new ArrayList<>();
        for (int i = 0;i<getGroupFile().getJSONObject("Groups").keySet().size();i++) {
            String name = getGroupFile().getJSONObject("Groups").keySet().stream().toList().get(i);
            List<String> perms = BaseUtil.toStringList(getGroupFile().getJSONObject("Groups").getJSONObject(name).getJSONArray("perms").toList());
            Boolean isDefault = getGroupFile().getJSONObject("Groups").getJSONObject(name).getBoolean("isDefault");
            groups.add(new Group(perms,isDefault,name));
        }
        io.github.mcchampions.DodoOpenJava.Permissions.Group.addGroups(groups);

        for (int i = 0 ; i < getUserFile().getJSONObject("Users").keySet().size(); i++) {
            String DodoId = getUserFile().getJSONObject("Users").keySet().stream().toList().get(i);
            List<String> perms = BaseUtil.toStringList(getUserFile().getJSONObject("Users").getJSONObject(DodoId).getJSONArray("perms").toList());
            String group = getUserFile().getJSONObject("Groups").getJSONObject(DodoId).getString("Group");
            io.github.mcchampions.DodoOpenJava.Permissions.Group Group = new Group();
            for (int I = 0; I < io.github.mcchampions.DodoOpenJava.Permissions.Group.getGroups().size(); I++) {
                if (Objects.equals(io.github.mcchampions.DodoOpenJava.Permissions.Group.getGroups().get(I).getName(), group)) {
                    Group = io.github.mcchampions.DodoOpenJava.Permissions.Group.getGroups().get(I);
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
    public static JSONObject getGroupFile() {
        try {
            return new JSONObject(Toml.parse(Paths.get(Group.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取用户文件
     * @return json对象
     */
    public static JSONObject getUserFile() {
        try {
            return new JSONObject(Toml.parse(Paths.get(User.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
