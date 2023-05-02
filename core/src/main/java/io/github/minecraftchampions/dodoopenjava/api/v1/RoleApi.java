package io.github.minecraftchampions.dodoopenjava.api.v1;

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
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getRoleList(String clientId, String token, String islandId) throws IOException {
        return getRoleList(BaseUtil.Authorization(clientId, token), islandId);
    }

    /**
     * ��ȡ������б�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getRoleList(String authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * �����Ա�����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addRoleMember(String clientId, String token, String islandId, String dodoId, String roleId) throws IOException {
        return addRoleMember(BaseUtil.Authorization(clientId, token), islandId, dodoId, roleId);
    }

    /**
     * �����Ա�����
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addRoleMember(String authorization, String islandId, String dodoId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/member/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ȡ����Ա�����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeRoleMember(String clientId, String token, String islandId, String dodoId, String roleId) throws IOException {
        return removeRoleMember(BaseUtil.Authorization(clientId, token), islandId, dodoId, roleId);
    }

    /**
     * ȡ����Ա�����
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeRoleMember(String authorization, String islandId, String dodoId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/member/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���������
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addRole(String clientId, String token, String islandId, String roleName, String roleColor,int position, String permission) throws IOException {
        return addRole(BaseUtil.Authorization(clientId, token), islandId, roleName, roleColor, position, permission);
    }

    /**
     * ���������
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addRole(String authorization, String islandId, String roleName, String roleColor,int position, String permission) throws IOException {
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

        return new JSONObject(NetUtil.sendRequest(param.toString(), url, authorization)) ;
    }

    /**
     * �༭�����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param roleId �����ID
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editRole(String clientId, String token, String islandId, String roleId, String roleName, String roleColor,int position, String permission) throws IOException {
        return editRole(BaseUtil.Authorization(clientId, token), islandId, roleId, roleName, roleColor, position, permission);
    }

    /**
     * �༭�����
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param roleId �����ID
     * @param roleName ��������ƣ�����ΪnullʱĬ��Ϊ����Ϊ�µ�����顱�����ܴ���32���ַ���16������
     * @param roleColor �������ɫ������ΪnullʱĬ��Ϊ����#333333����16����HEX��ʽ��ɫ��
     * @param position ���������λ�ã�����Ϊ1ʱĬ��Ϊ����1�����ϻ��������ɴ��Ȼ����������������ֵ
     * @param permission �����Ȩ��ֵ��16���ƣ�������ΪnullʱĬ��Ϊ����0��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editRole(String authorization, String islandId, String roleId, String roleName, String roleColor,int position, String permission) throws IOException {
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
        return new JSONObject(NetUtil.sendRequest(param.toString(), url, authorization)) ;
    }

    /**
     * ɾ�������
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject deleteRole(String clientId, String token, String islandId, String roleId) throws IOException {
        return deleteRole(BaseUtil.Authorization(clientId, token), islandId, roleId);
    }

    /**
     * ɾ�������
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param roleId �����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject deleteRole(String authorization, String islandId, String roleId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/role/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"roleId\": \"" + roleId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
