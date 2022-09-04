package io.github.mcchampions.DodoOpenJava.permissions.Data;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.ConfigUtil;
import io.github.mcchampions.DodoOpenJava.Utils.XmlUtil;
import io.github.mcchampions.DodoOpenJava.permissions.PermissionsGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * XML文件存储
 */
public class Xml {
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

        Group = new File(ConfigUtil.getJarPath() + "permissions/permGroup.xml");
        if (!Group.exists()) {
            ConfigUtil.copyResourcesToFile("permissions/permGroup.xml", Group.getPath());
        }

        User = new File(ConfigUtil.getJarPath() + "permissions/permUser.xml");
        if (!User.exists()) {
            ConfigUtil.copyResourcesToFile("permissions/permUser.xml", Group.getPath());
        }

        List<PermissionsGroup> groups = new ArrayList<>();
        for (int i = 0;i<getGroupFile().getJSONObject("Groups").keySet().size();i++) {
            String name = getGroupFile().getJSONObject("Groups").keySet().stream().toList().get(i);
            List<String> perms = getGroupFile().getJSONObject("Groups").getJSONObject(name).getJSONArray("perms").toList(String.class);
            Boolean isDefault = getGroupFile().getJSONObject("Groups").getJSONObject(name).getBoolean("isDefault");
            groups.add(new PermissionsGroup(perms,isDefault,name));
        }
        PermissionsGroup.addGroups(groups);

        for (int i = 0 ; i < getUserFile().getJSONObject("Users").getJSONArray("User").size(); i++) {
            JSONObject json = getGroupFile().getJSONObject("Users").getJSONArray("User").getJSONObject(i);
            String DodoId = json.getString("DodoId");
            List<String> perms = json.getJSONArray("perm").toList(String.class);
            String group = json.getString("Group");
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
        return XmlUtil.toJSONObject(ConfigUtil.readJsonFile(Group));
    }

    /**
     * 获取用户文件
     * @return JSON对象
     */
    public static JSONObject getUserFile() {
        return XmlUtil.toJSONObject(ConfigUtil.readJsonFile(User));
    }
}
