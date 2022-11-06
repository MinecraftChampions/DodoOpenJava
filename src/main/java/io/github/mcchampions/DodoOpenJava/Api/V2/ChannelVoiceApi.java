package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ����Ƶ��API
 * @author qscbm187531
 */
public class ChannelVoiceApi {
    public static String url,param;

    /**
     * ��ȡ��Ա����Ƶ��״̬
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandSourceId,String dodoSourceId,String clientId, String token) throws IOException {
        return getChannelVoiceMemberStatus(islandSourceId, dodoSourceId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * ��ȡ��Ա����Ƶ��״̬
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandSourceId,String dodoSourceId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/voice/member/status";
        param = "{" +
                "    \"islandSourceId\": \""+ islandSourceId +"\"," +
                "    \"dodoSourceId\": \""+ dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }


    /**
     * �ƶ�����Ƶ����Ա
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject moveChannelVoiceMember(String islandSourceId,String dodoSourceId,String channelId,String clientId, String token) throws IOException {
        return moveChannelVoiceMember(islandSourceId, dodoSourceId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * �ƶ�����Ƶ����Ա
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject moveChannelVoiceMember(String islandSourceId,String dodoSourceId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/voice/member/move";
        param = "{" +
                "    \"islandSourceId\": \""+ islandSourceId +"\"," +
                "    \"dodoSourceId\": \""+ dodoSourceId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���������еĳ�Ա
     * @param operateType ִ�й�������ĵ���
     * @param dodoSourceId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoSourceId,String channelId,String clientId, String token) throws IOException {
        return editChannelVoiceMember(operateType, dodoSourceId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * ���������еĳ�Ա
     * @param operateType ִ�й�������ĵ���
     * @param dodoSourceId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoSourceId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/voice/member/edit";
        param = "{" +
                "    \"operateType\": "+ operateType +"," +
                "    \"dodoSourceId\": \""+ dodoSourceId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
