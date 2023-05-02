package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * �����API
 * @author qscbm187531
 */
public class RoleApi {
    public static String url, param;

    /**
     * ��ȡ������б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getRoleList(String clientId, String token, String islandSourceId) throws IOException {
        return getRoleList(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * ��ȡ������б�
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getRoleList(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/role/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * �����Ա�����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addRoleMember(String clientId, String token, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        return addRoleMember(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, roleId);
    }

    /**
     * �����Ա�����
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ȡ����Ա�����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeRoleMember(String clientId, String token, String islandSourceId, String dodoSourceId, String roleId) throws IOException {
        return removeRoleMember(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, roleId);
    }

    /**
     * ȡ����Ա�����
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ���������
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addRole(String clientId, String token, String islandSourceId, String roleName, String roleColor,int position, String permission) throws IOException {
        return addRole(BaseUtil.Authorization(clientId, token), islandSourceId, roleName, roleColor, position, permission);
    }

    /**
     * ���������
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * �༭�����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param roleId �����ID
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editRole(String clientId, String token, String islandSourceId, String roleId, String roleName, String roleColor,int position, String permission) throws IOException {
        return editRole(BaseUtil.Authorization(clientId, token), islandSourceId, roleId, roleName, roleColor, position, permission);
    }

    /**
     * �༭�����
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param roleId �����ID
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ɾ�������
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject deleteRole(String clientId, String token, String islandSourceId, String roleId) throws IOException {
        return deleteRole(BaseUtil.Authorization(clientId, token), islandSourceId, roleId);
    }

    /**
     * ɾ�������
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
