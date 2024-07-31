package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

import java.util.Map;

/**
 * 身份组API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class RoleApi {
    @NonNull
    private Bot bot;

    /**
     * 获取身份组列表
     *
     * @param islandSourceId 群号
     * @return result
     */
    public Result getRoleList(String islandSourceId) {
        String url = DodoOpenJava.BASEURL + "role/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 赋予成员身份组
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param roleId         身份组ID
     * @return result
     */
    public Result addRoleMember(String islandSourceId, String dodoSourceId, String roleId) {
        String url = DodoOpenJava.BASEURL + "role/member/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId).put("dodoSourceId", dodoSourceId).put("roleId", roleId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 取消成员身份组
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param roleId         身份组ID
     * @return result
     */
    public Result removeRoleMember(String islandSourceId, String dodoSourceId, String roleId) {
        String url = DodoOpenJava.BASEURL + "role/member/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId).put("dodoSourceId", dodoSourceId).put("roleId", roleId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }


    /**
     * 创建身份组
     *
     * @param islandSourceId 群号
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     权限
     * @return result
     */
    public Result addRole(String islandSourceId, String roleName, String roleColor,
                          int position, Permission permission) {
        return addRole(islandSourceId, roleName, roleColor, position, permission.toHexString());
    }

    /**
     * 创建身份组
     *
     * @param islandSourceId 群号
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     身份组权限值（16进制），设置为null时默认为：“0”
     * @return result
     */
    public Result addRole(String islandSourceId, String roleName, String roleColor,
                          int position, String permission) {
        String url = DodoOpenJava.BASEURL + "role/add";
        JSONObject param = new JSONObject("{" + "  \"islandSourceId\": \"" + islandSourceId + "\"}");
        return sendResult(roleName, roleColor, position, permission, url, param);
    }

    /**
     * 获取成员列表
     *
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return result
     */
    public Result getMemberList(String islandSourceId, String roleId, int pageSize, long maxId) {
        String url = DodoOpenJava.BASEURL + "role/member/list";
        JSONObject param = new JSONObject(Map.of("islandSourceId", islandSourceId,
                "roleId", roleId,
                "pageSize", pageSize,
                "maxId", maxId));
        return NetUtils.sendRequest(param.toString(), url, bot.getAuthorization());
    }

    /**
     * 编辑身份组
     *
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     身份组权限值（16进制），设置为null时默认为：“0”
     * @return result
     */
    public Result editRole(String islandSourceId, String roleId, String roleName,
                           String roleColor, int position, String permission) {
        String url = DodoOpenJava.BASEURL + "role/edit";
        JSONObject param = new JSONObject();
        param.put("islandSourceId", islandSourceId);
        param.put("roleId", roleId);
        return sendResult(roleName, roleColor, position, permission, url, param);
    }

    /**
     * 编辑身份组
     *
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @param roleName       身份组名称，设置为null时默认为：“为新的身份组”，不能大于32个字符或16个汉字
     * @param roleColor      身份组颜色，设置为null时默认为：“#333333”，16进制HEX格式颜色码
     * @param position       身份组排序位置，设置为1时默认为：“1”（废话），不可传比机器人身份组大的排序值
     * @param permission     权限
     * @return result
     */
    public Result editRole(String islandSourceId, String roleId, String roleName,
                           String roleColor, int position, Permission permission) {
        return editRole(islandSourceId, roleId, roleName, roleColor, position, permission.toHexString());
    }

    private Result sendResult(String roleName, String roleColor, int position, String permission, String url, JSONObject param) {
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
        return NetUtils.sendRequest(param.toString(), url, bot.getAuthorization());
    }

    /**
     * 删除身份组
     *
     * @param islandSourceId 群号
     * @param roleId         身份组ID
     * @return result
     */
    public Result deleteRole(String islandSourceId, String roleId) {
        String url = DodoOpenJava.BASEURL + "role/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId).put("roleId", roleId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
