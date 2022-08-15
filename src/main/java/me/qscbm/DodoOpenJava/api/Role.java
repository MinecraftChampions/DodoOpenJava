package me.qscbm.DodoOpenJava.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.qscbm.DodoOpenJava.Utils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Role {
    public static String url,parm;

    /**
     * 获取身份组列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getRoleList(String clientId, String token, String islandId, Boolean returnJSONText) throws IOException {
        return getRoleList(Utils.Authorization(clientId, token), islandId, returnJSONText);
    }

    /**
     * 获取身份组列表
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getRoleList(String Authorization, String islandId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/list";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\"\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        String island;
        if (!returnJSONText) {
            JSONArray JSONList = JSONObject.parseObject(Parm).getJSONArray("data");
            int JSONListSize = JSONList.size();
            String[] List = new String[0];
            for (int i = 0; i < JSONListSize; i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String roleCount = String.valueOf(i + 1);
                String roleId = JSONObject.parseObject(JSONText).getString("roleId");
                String roleName = JSONObject.parseObject(JSONText).getString("roleName");
                String roleColor = JSONObject.parseObject(JSONText).getString("roleColor");
                String position = String.valueOf(JSONObject.parseObject(JSONText).getIntValue("position"));
                String permission = JSONObject.parseObject(JSONText).getString("permission");
                island = "  身份组" + roleCount + ": \n" +
                        "    身份组ID: \"" + roleId + "\"\n" +
                        "    身份组名称: \"" + roleName + "\"\n" +
                        "    身份组颜色: \"" + roleColor + "\"\n" +
                        "    身份组排序位置: \"" + position + "\"\n" +
                        "    身份组权限值(16进制): \"" + permission + "\"\n";
                List[i] = island;
            }
            String roleList = StringUtils.join(List);
            Parm = "身份组列表:\n" + roleList;
        }
        return Parm;
    }

    /**
     * 赋予成员身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数，由于没什么好解析的，所以false就是返回null
     */
    public static String setRoleMemberAdd(String clientId, String token, String islandId, String DodoId, String roleId, Boolean returnJSONText) throws IOException {
        return setRoleMemberAdd(Utils.Authorization(clientId, token), islandId, DodoId, roleId, returnJSONText);
    }

    /**
     * 赋予成员身份组
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数，由于没什么好解析的，所以false就是返回null
     */
    public static String setRoleMemberAdd(String Authorization, String islandId, String DodoId, String roleId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/member/add";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"roleId\": \"" + roleId + "\",\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 取消成员身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数，由于没什么好解析的，所以false就是返回null
     */
    public static String setRoleMemberRemove(String clientId, String token, String islandId, String DodoId, String roleId, Boolean returnJSONText) throws IOException {
        return setRoleMemberRemove(Utils.Authorization(clientId, token), islandId, DodoId, roleId, returnJSONText);
    }

    /**
     * 取消成员身份组
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数，由于没什么好解析的，所以false就是返回null
     */
    public static String setRoleMemberRemove(String Authorization, String islandId, String DodoId, String roleId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/member/remove";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"roleId\": \"" + roleId + "\",\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }
}
