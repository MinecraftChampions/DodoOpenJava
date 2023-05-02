package io.github.minecraftchampions.dodoopenjava.api.v1;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
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
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandId,String dodoId,String clientId, String token) throws IOException {
        return getChannelVoiceMemberStatus(islandId, dodoId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * ��ȡ��Ա����Ƶ��״̬
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandId,String dodoId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/status";
        param = "{" +
                "    \"islandId\": \""+ islandId +"\"," +
                "    \"dodoId\": \""+ dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }


    /**
     * �ƶ�����Ƶ����Ա
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject moveChannelVoiceMember(String islandId,String dodoId,String channelId,String clientId, String token) throws IOException {
        return moveChannelVoiceMember(islandId, dodoId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * �ƶ�����Ƶ����Ա
     * @param islandId Ⱥ��
     * @param dodoId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject moveChannelVoiceMember(String islandId,String dodoId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/move";
        param = "{" +
                "    \"islandId\": \""+ islandId +"\"," +
                "    \"dodoId\": \""+ dodoId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���������еĳ�Ա
     * @param operateType ִ�й�������ĵ���
     * @param dodoId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoId,String channelId,String clientId, String token) throws IOException {
        return editChannelVoiceMember(operateType, dodoId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * ���������еĳ�Ա
     * @param operateType ִ�й�������ĵ���
     * @param dodoId Dodo��
     * @param channelId �ƶ�����Ƶ����
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/edit";
        param = "{" +
                "    \"operateType\": "+ operateType +"," +
                "    \"dodoId\": \""+ dodoId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
