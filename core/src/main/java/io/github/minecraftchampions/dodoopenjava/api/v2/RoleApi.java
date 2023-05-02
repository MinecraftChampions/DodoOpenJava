package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 身份组API
 * @author qscbm187531
 */
public class RoleApi {
    public static String url, param;

    /**
     * 获取身份组列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getRoleList(String clientId, String token, String islandSourceId) throws IOException {
        return getRoleList(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * 获取身份组列表
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getRoleList(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/role/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 赋予成员身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRoleMember(String clientId, String token, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        return addRoleMember(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, roleId);
    }

    /**
     * 赋予成员身份组
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRoleMember(String authorization, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/role/member/add";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 取消成员身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeRoleMember(String clientId, String token, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        return removeRoleMember(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, roleId);
    }

    /**
     * 取消成员身份组
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeRoleMember(String authorization, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/role/member/remove";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 创建身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRole(String clientId, String token, String islandSourceId, String roleName, String roleColor,int position, String permission) throws IOException {
        return addRole(BaseUtil.Authorization(clientId, token), islandSourceId, roleName, roleColor, position, permission);
    }

    /**
     * 创建身份组
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addRole(String authorization, String islandSourceId, String roleName, String roleColor,int position, String permission) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/role/add";
        JSONObject param = new JSONObject("{" +
                "  \"islandSourceId\": \"" + islandSourceId + "\"}");
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

        return new JSONObject(NetUtil.sendRequest(param.toString(), url, authorization)) ;
    }

    /**
     * 编辑身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleId 身份组ID
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editRole(String clientId, String token, String islandSourceId, String roleId, String roleName, String roleColor,int position, String permission) throws IOException {
        return editRole(BaseUtil.Authorization(clientId, token), islandSourceId, roleId, roleName, roleColor, position, permission);
    }

    /**
     * 编辑身份组
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param roleId 身份组ID
     * @param roleName 身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor 身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position 身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission 身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editRole(String authorization, String islandSourceId, String roleId, String roleName, String roleColor,int position, String permission) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/role/edit";
        JSONObject param = new JSONObject("{" +
                "  \"islandSourceId\": \"" + islandSourceId + "\"," +
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
        return new JSONObject(NetUtil.sendRequest(param.toString(), url, authorization)) ;
    }

    /**
     * 删除身份组
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteRole(String clientId, String token, String islandSourceId, String roleId) throws IOException {
        return deleteRole(BaseUtil.Authorization(clientId, token), islandSourceId, roleId);
    }

    /**
     * 删除身份组
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param roleId 身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteRole(String authorization, String islandSourceId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/role/remove";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
