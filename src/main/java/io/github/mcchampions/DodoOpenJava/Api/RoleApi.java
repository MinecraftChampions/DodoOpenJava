package io.github.mcchampions.DodoOpenJava.Api;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 身份组API
 */
public class RoleApi {
    public static String url, param;

    /**
     * 获取身份组列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getRoleList(String clientId, String token, String islandId) throws IOException {
        return getRoleList(BaseUtil.Authorization(clientId, token), islandId);
    }

    /**
     * 获取身份组列表
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getRoleList(String Authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 赋予成员身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRoleMember(String clientId, String token, String islandId, String DodoId, String roleId) throws IOException {
        return addRoleMember(BaseUtil.Authorization(clientId, token), islandId, DodoId, roleId);
    }

    /**
     * 赋予成员身份组
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRoleMember(String Authorization, String islandId, String DodoId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/member/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + DodoId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 取消成员身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeRoleMember(String clientId, String token, String islandId, String DodoId, String roleId) throws IOException {
        return removeRoleMember(BaseUtil.Authorization(clientId, token), islandId, DodoId, roleId);
    }

    /**
     * 取消成员身份组
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeRoleMember(String Authorization, String islandId, String DodoId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/member/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + DodoId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 创建身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRole(String clientId, String token, String islandId, String roleName, String roleColor,int position, String permission) throws IOException {
        return addRole(BaseUtil.Authorization(clientId, token), islandId, roleName, roleColor, position, permission);
    }

    /**
     * 创建身份组
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRole(String Authorization, String islandId, String roleName, String roleColor,int position, String permission) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/add";
        JSONObject param = new JSONObject("{" +
                "  \"islandId\": \"" + islandId + "\"}");
        if (roleName != null) {
            param.put("roleName", roleName);
        }

        if (roleColor != null) {
            param.put("roleColor", roleColor);
        }

        if (position != 1) {
            param.put("position", position);
        }

        if (roleColor != null) {
            param.put("permission", permission);
        }

        return new JSONObject(NetUtil.sendRequest(param.toString(), url, Authorization)) ;
    }

    /**
     * 编辑身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param roleId 身份组ID
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editRole(String clientId, String token, String islandId, String roleId, String roleName, String roleColor,int position, String permission) throws IOException {
        return editRole(BaseUtil.Authorization(clientId, token), islandId, roleId, roleName, roleColor, position, permission);
    }

    /**
     * 编辑身份组
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param roleId 身份组ID
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editRole(String Authorization, String islandId, String roleId, String roleName, String roleColor,int position, String permission) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/edit";
        JSONObject param = new JSONObject("{" +
                "  \"islandId\": \"" + islandId + "\"," +
                "  \"roleId\": \"" + roleId + "\"}");
        if (roleName != null) {
            param.put("roleName", roleName);
        }

        if (roleColor != null) {
            param.put("roleColor", roleColor);
        }

        if (position != 1) {
            param.put("position", position);
        }

        if (roleColor != null) {
            param.put("permission", permission);
        }
        return new JSONObject(NetUtil.sendRequest(param.toString(), url, Authorization)) ;
    }

    /**
     * 删除身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteRole(String clientId, String token, String islandId, String roleId) throws IOException {
        return deleteRole(BaseUtil.Authorization(clientId, token), islandId, roleId);
    }

    /**
     * 删除身份组
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteRole(String Authorization, String islandId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }
}
