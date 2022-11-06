package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ��ԱAPI
 * @author qscbm187531
 */
public class MemberApi {
    public static String url, param;

    /**
     * ��ȡ��Ա�б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberList(String clientId, String token, String islandSourceId, int pageSize, long maxId) throws IOException {
        return getMemberList(BaseUtil.Authorization(clientId, token), islandSourceId, pageSize, maxId);
    }

    /**
     * ��ȡ��Ա�б�
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberList(String authorization, String islandSourceId, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
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
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId ���Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInfo(String clientId, String token, String islandSourceId,String dodoSourceId) throws IOException {
        return getMemberInfo(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * ��ȡ��Ա��Ϣ
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId ���Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInfo(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/info";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա������б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberRoleList(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getMemberRoleList(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * ��ȡ��Ա������б�
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberRoleList(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/role/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա������Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInvitationInfo(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getMemberInvitationInfo(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * ��ȡ��Ա������Ϣ
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberInvitationInfo(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/role/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * �༭��ԱȺ�ǳ�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param nickName Ⱥ�ǳƣ��ǳƲ��ܴ���32���ַ���16������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editMemberNickName(String clientId, String token, String islandSourceId, String dodoSourceId, String nickName) throws IOException {
        return editMemberNickName(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, nickName);
    }

    /**
     * �༭��ԱȺ�ǳ�
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param nickName Ⱥ�ǳƣ��ǳƲ��ܴ���32���ַ���16������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editMemberNickName(String authorization, String islandSourceId, String dodoSourceId, String nickName) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/nick/set";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"nickName\": \"" + nickName + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���Գ�Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberMute(String clientId, String token, String islandSourceId, String dodoSourceId, int duration) throws IOException {
        return addMemberMute(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, duration);
    }

    /**
     * ���Գ�Ա
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberMute(String authorization, String islandSourceId, String dodoSourceId, int duration) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/ban/set";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"duration\": " + duration + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���Գ�Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @param dodoSourceId Dodo��
     * @param reason ����ԭ��ԭ���ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonrMute(String clientId, String token, String islandSourceId, String dodoSourceId, int duration, String reason) throws IOException {
        return addMemberReasonrMute(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, duration, reason);
    }

    /**
     * ���Գ�Ա
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param duration ����ʱ��(��),�Ϊ7��
     * @param reason ����ԭ��ԭ���ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonrMute(String authorization, String islandSourceId, String dodoSourceId, int duration, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/ban/set";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
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
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberMute(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return removeMemberMute(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * ȡ����Ա����
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberMute(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/mute/remove";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return addMemberBan(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/ban/add";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param reason �������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonBan(String clientId, String token, String islandSourceId, String dodoSourceId, String reason) throws IOException {
        return addMemberReasonBan(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, reason);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param reason �������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberReasonBan(String authorization, String islandSourceId, String dodoSourceId, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/ban/add";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberChannelBan(String clientId, String token, String islandSourceId, String dodoSourceId, String noticeChannelId) throws IOException {
        return addMemberChannelBan(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, noticeChannelId);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberChannelBan(String authorization, String islandSourceId, String dodoSourceId, String noticeChannelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/ban/add";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"noticeChannelId\": \"" + noticeChannelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���÷����Ա
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @param reason ������ɣ����ɲ��ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String clientId, String token, String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) throws IOException {
        return addMemberBan(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, noticeChannelId, reason);
    }

    /**
     * ���÷����Ա
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param noticeChannelId ֪ͨƵ��ID
     * @param reason ������ɣ����ɲ��ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addMemberBan(String authorization, String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/ban/add";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
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
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberBan(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return removeMemberBan(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * ȡ����Ա���÷��
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeMemberBan(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/ban/remove";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
