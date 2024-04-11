package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;
import io.github.minecraftchampions.dodoopenjava.util.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.util.NetUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * 身份组API
 *
 * @author qscbm187531
 */
public class RoleApi {
    /**
     * 获取身份组列表
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getRoleList(String clientId, String token, String islandSourceId) throws IOException {
        return getRoleList(BaseUtil.generateAuthorization(clientId, token), islandSourceId);
    }

    /**
     * 获取身份组列表
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getRoleList(String authorization, String islandSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "role/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 赋予成员身份组
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param roleId         身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addRoleMember(String clientId, String token, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        return addRoleMember(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, roleId);
    }

    /**
     * 赋予成员身份组
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param roleId         身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addRoleMember(String authorization, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        String url = DodoOpenJava.BASEURL + "role/member/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId).put("dodoSourceId", dodoSourceId).put("roleId", roleId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 取消成员身份组
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param roleId         身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeRoleMember(String clientId, String token, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        return removeRoleMember(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, roleId);
    }

    /**
     * 取消成员身份组
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param roleId         身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeRoleMember(String authorization, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        String url = DodoOpenJava.BASEURL + "role/member/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId).put("dodoSourceId", dodoSourceId).put("roleId", roleId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 创建身份组
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addRole(String clientId, String token, String islandSourceId, String roleName, String roleColor, int position, String permission) throws IOException {
        return addRole(BaseUtil.generateAuthorization(clientId, token), islandSourceId, roleName, roleColor, position, permission);
    }

    /**
     * 创建身份组
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     权限
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addRole(String clientId, String token, String islandSourceId, String roleName, String roleColor, int position, Permission permission) throws IOException {
        return addRole(BaseUtil.generateAuthorization(clientId, token), islandSourceId, roleName, roleColor, position, permission.toHexString());
    }

    /**
     * 创建身份组
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     权限
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addRole(String authorization, String islandSourceId, String roleName, String roleColor, int position, Permission permission) throws IOException {
        return addRole(authorization, islandSourceId, roleName, roleColor, position, permission.toHexString());
    }

    /**
     * 创建身份组
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addRole(String authorization, String islandSourceId, String roleName, String roleColor, int position, String permission) throws IOException {
        String url = DodoOpenJava.BASEURL + "role/add";
        JSONObject param = new JSONObject("{" + "  \"islandSourceId\": \"" + islandSourceId + "\"}");
        return sendResult(authorization, roleName, roleColor, position, permission, url, param);
    }

    /**
     * 编辑身份组
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editRole(String clientId, String token, String islandSourceId, String roleId, String roleName, String roleColor, int position, String permission) throws IOException {
        return editRole(BaseUtil.generateAuthorization(clientId, token), islandSourceId, roleId, roleName, roleColor, position, permission);
    }

    /**
     * 获取成员列表
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberList(String authorization, String islandSourceId, String roleId, int pageSize, long maxId) throws IOException {
        String url = DodoOpenJava.BASEURL + "role/member/list";
        JSONObject param = new JSONObject(Map.of("islandSourceId", islandSourceId, "roleId", roleId, "pageSize", pageSize, "maxId", maxId));
        return NetUtil.sendRequest(param.toString(), url, authorization);
    }

    /**
     * 获取成员列表
     *
     * @param clientId       clientId
     * @param token          token
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberList(String clientId, String token, String islandSourceId, String roleId, int pageSize, long maxId) throws IOException {
        return getMemberList(BaseUtil.generateAuthorization(clientId, token), islandSourceId, roleId, pageSize, maxId);
    }

    /**
     * 编辑身份组
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     身份组权限值（16进制），设置为null时默认为：“0”
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editRole(String authorization, String islandSourceId, String roleId, String roleName, String roleColor, int position, String permission) throws IOException {
        String url = DodoOpenJava.BASEURL + "role/edit";
        JSONObject param = new JSONObject("{" + "  \"islandSourceId\": \"" + islandSourceId + "\"," + "  \"roleId\": \"" + roleId + "\"}");
        return sendResult(authorization, roleName, roleColor, position, permission, url, param);
    }

    /**
     * 编辑身份组
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     权限
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editRole(String clientId, String token, String islandSourceId, String roleId, String roleName, String roleColor, int position, Permission permission) throws IOException {
        return editRole(BaseUtil.generateAuthorization(clientId, token), islandSourceId, roleId, roleName, roleColor, position, permission.toHexString());
    }

    /**
     * 编辑身份组
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     权限
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editRole(String authorization, String islandSourceId, String roleId, String roleName, String roleColor, int position, Permission permission) throws IOException {
        return editRole(authorization, islandSourceId, roleId, roleName, roleColor, position, permission.toHexString());
    }

    private static Result sendResult(String authorization, String roleName, String roleColor, int position, String permission, String url, JSONObject param) throws IOException {
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
        return NetUtil.sendRequest(param.toString(), url, authorization);
    }

    /**
     * 删除身份组
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result deleteRole(String clientId, String token, String islandSourceId, String roleId) throws IOException {
        return deleteRole(BaseUtil.generateAuthorization(clientId, token), islandSourceId, roleId);
    }

    /**
     * 删除身份组
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result deleteRole(String authorization, String islandSourceId, String roleId) throws IOException {
        String url = DodoOpenJava.BASEURL + "role/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId).put("roleId", roleId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }
}
