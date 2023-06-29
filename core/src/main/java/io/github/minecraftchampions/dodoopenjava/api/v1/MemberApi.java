package io.github.minecraftchampions.dodoopenjava.api.v1;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ��ԱAPI
 */
public class MemberApi {
    public static String url, param;

    /**
     * ��ȡ��Ա�б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberList(String clientId, String token, String islandId, int pageSize, long maxId) throws IOException {
        return getMemberList(BaseUtil.Authorization(clientId, token), islandId, pageSize, maxId);
    }

    /**
     * ��ȡ��Ա�б�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberList(String authorization, String islandId, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"pageSize\": \"" + pageSize + "\"," +
                "    \"maxId\": \"" + maxId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId ���Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInfo(String clientId, String token, String islandId,String dodoId) throws IOException {
        return getMemberInfo(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * ��ȡ��Ա��Ϣ
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId ���Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInfo(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/info";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա������б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberRoleList(String clientId, String token, String islandId, String dodoId) throws IOException {
        return getMemberRoleList(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * ��ȡ��Ա������б�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberRoleList(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/role/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoID\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա������Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInvitationInfo(String clientId, String token, String islandId, String dodoId) throws IOException {
        return getMemberInvitationInfo(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * ��ȡ��Ա������Ϣ
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInvitationInfo(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/role/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * �༭��ԱȺ�ǳ�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param nickName Ⱥ�ǳƣ��ǳƲ��ܴ���32���ַ���16������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editMemberNickName(String clientId, String token, String islandId, String dodoId, String nickName) throws IOException {
        return editMemberNickName(BaseUtil.Authorization(clientId, token), islandId, dodoId, nickName);
    }

    /**
     * �༭��ԱȺ�ǳ�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param nickName Ⱥ�ǳƣ��ǳƲ��ܴ���32���ַ���16������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editMemberNickName(String authorization, String islandId, String dodoId, String nickName) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/nick/set";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"nickName\": \"" + nickName + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���Գ�Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberMute(String clientId, String token, String islandId, String dodoId, int duration) throws IOException {
        return addMemberMute(BaseUtil.Authorization(clientId, token), islandId, dodoId, duration);
    }

    /**
     * ���Գ�Ա
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberMute(String authorization, String islandId, String dodoId, int duration) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/set";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"duration\": " + duration + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���Գ�Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @param dodoId Dodo��
     * @param reason ����ԭ��ԭ���ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonrMute(String clientId, String token, String islandId, String dodoId, int duration, String reason) throws IOException {
        return addMemberReasonrMute(BaseUtil.Authorization(clientId, token), islandId, dodoId, duration, reason);
    }

    /**
     * ���Գ�Ա
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @param reason ����ԭ��ԭ���ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonrMute(String authorization, String islandId, String dodoId, int duration, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/set";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"duration\": " + duration + "," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ȡ����Ա����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberMute(String clientId, String token, String islandId, String dodoId) throws IOException {
        return removeMemberMute(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * ȡ����Ա����
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberMute(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/mute/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String clientId, String token, String islandId, String dodoId) throws IOException {
        return addMemberBan(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param reason �������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonBan(String clientId, String token, String islandId, String dodoId, String reason) throws IOException {
        return addMemberReasonBan(BaseUtil.Authorization(clientId, token), islandId, dodoId, reason);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param reason �������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonBan(String authorization, String islandId, String dodoId, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberChannelBan(String clientId, String token, String islandId, String dodoId, String noticeChannelId) throws IOException {
        return addMemberChannelBan(BaseUtil.Authorization(clientId, token), islandId, dodoId, noticeChannelId);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberChannelBan(String authorization, String islandId, String dodoId, String noticeChannelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"noticeChannelId\": \"" + noticeChannelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @param reason ������ɣ����ɲ��ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String clientId, String token, String islandId, String dodoId, String noticeChannelId, String reason) throws IOException {
        return addMemberBan(BaseUtil.Authorization(clientId, token), islandId, dodoId, noticeChannelId, reason);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @param reason ������ɣ����ɲ��ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String authorization, String islandId, String dodoId, String noticeChannelId, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"noticeChannelId\": \"" + noticeChannelId + "\"," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ȡ����Ա���÷��
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberBan(String clientId, String token, String islandId, String dodoId) throws IOException {
        return removeMemberBan(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * ȡ����Ա���÷��
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberBan(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
