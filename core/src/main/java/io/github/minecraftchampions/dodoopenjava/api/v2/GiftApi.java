package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ����ϵͳApi
 */
public class GiftApi {
    public static String url, param;

    /**
     * ��ȡȺ����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftAccount(String clientId, String token, String islandSourceId) throws IOException {
        return getGiftAccount(BaseUtil.Authorization(clientId,token), islandSourceId);
    }

    /**
     * ��ȡȺ����
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftAccount(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/gift/account/info";
        param = new JSONObject().put("islandSourceId",islandSourceId).toString();
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա�ֳɹ���
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftShareRatioInfo(String clientId, String token, String islandSourceId) throws IOException {
        return getGiftShareRatioInfo(BaseUtil.Authorization(clientId,token), islandSourceId);
    }

    /**
     * ��ȡ��Ա�ֳɹ���
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftShareRatioInfo(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/gift/share/ratio/info";
        param = new JSONObject().put("islandSourceId",islandSourceId).toString();
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ���������б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param targetId ����ID
     * @param targetType �������ͣ�1����Ϣ��2������
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftList(String clientId, String token, String targetId, int targetType) throws IOException {
        return getGiftList(BaseUtil.Authorization(clientId,token), targetId, targetType);
    }

    /**
     * ��ȡ���������б�
     *
     * @param authorization authorization
     * @param targetId ����ID
     * @param targetType �������ͣ�1����Ϣ��2������
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftList(String authorization, String targetId, int targetType) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/gift/list";
        param = new JSONObject().put("targetId",targetId).put("targetType",targetType).toString();
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ���������ڳ�Ա�б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param targetId ����ID
     * @param targetType �������ͣ�1����Ϣ��2������
     * @param giftId ����ID
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftMemberList(String clientId, String token, String targetId, int targetType, String giftId, int pageSize, long maxId) throws IOException {
        return getGiftMemberList(BaseUtil.Authorization(clientId,token), targetId, targetType, giftId, pageSize, maxId);
    }

    /**
     * ��ȡ���������ڳ�Ա�б�
     *
     * @param authorization authorization
     * @param targetId ����ID
     * @param targetType �������ͣ�1����Ϣ��2������
     * @param giftId ����ID
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftMemberList(String authorization, String targetId, int targetType, String giftId, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/gift/member/list";
        param = new JSONObject().put("targetId",targetId).put("targetType",targetType).put("giftId",giftId).put("pageSize",pageSize).put("maxId",maxId).toString();
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ���ڻ�ȡָ�����ݵ�������ֵ�б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param targetId ����ID
     * @param targetType �������ͣ�1����Ϣ��2������
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftGrossValueList(String clientId, String token, String targetId, int targetType, int pageSize, long maxId) throws IOException {
        return getGiftGrossValueList(BaseUtil.Authorization(clientId,token), targetId, targetType, pageSize, maxId);
    }

    /**
     * ���ڻ�ȡָ�����ݵ�������ֵ�б�
     *
     * @param authorization authorization
     * @param targetId ����ID
     * @param targetType �������ͣ�1����Ϣ��2������
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getGiftGrossValueList(String authorization, String targetId, int targetType, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/gift/gross/value/list";
        param = new JSONObject().put("targetId",targetId).put("targetType",targetType).put("pageSize",pageSize).put("maxId",maxId).toString();
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
