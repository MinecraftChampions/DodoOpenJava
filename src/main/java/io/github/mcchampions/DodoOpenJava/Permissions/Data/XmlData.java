package io.github.mcchampions.DodoOpenJava.Permissions.Data;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.ConfigUtil;
import io.github.mcchampions.DodoOpenJava.Utils.XmlUtil;
import io.github.mcchampions.DodoOpenJava.Permissions.Group;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * XML文件存储
 * @author qscbm187531
 */
public class XmlData {
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

        List<io.github.mcchampions.DodoOpenJava.Permissions.Group> groups = new ArrayList<>();
        for (int i = 0;i<getGroupFile().getJSONObject("Groups").keySet().size();i++) {
            String name = getGroupFile().getJSONObject("Groups").keySet().stream().toList().get(i);
            List<String> perms = BaseUtil.toStringList(getGroupFile().getJSONObject("Groups").getJSONObject(name).getJSONArray("perms").toList());
            Boolean isDefault = getGroupFile().getJSONObject("Groups").getJSONObject(name).getBoolean("isDefault");
            groups.add(new Group(perms,isDefault,name));
        }
        io.github.mcchampions.DodoOpenJava.Permissions.Group.addGroups(groups);

        for (int i = 0 ; i < getUserFile().getJSONObject("Users").getJSONArray("User").toList().size(); i++) {
            JSONObject json = getUserFile().getJSONObject("Users").getJSONArray("User").getJSONObject(i);
            String DodoId = json.getString("DodoId");
            List<String> perms = BaseUtil.toStringList(json.getJSONArray("perm").toList());
            String group = json.getString("Group");
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
     * @return JSON对象
     */
    public static JSONObject getGroupFile() {
        return XmlUtil.toJSONObject(ConfigUtil.readFile(Group));
    }

    /**
     * 获取用户文件
     * @return JSON对象
     */
    public static JSONObject getUserFile() {
        return XmlUtil.toJSONObject(ConfigUtil.readFile(User));
    }
}
