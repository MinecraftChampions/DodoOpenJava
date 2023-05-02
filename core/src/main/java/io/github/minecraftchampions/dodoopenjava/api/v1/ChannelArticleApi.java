package io.github.minecraftchampions.dodoopenjava.api.v1;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ����Ƶ��API
 * @author qscbm187531
 */
public class ChannelArticleApi {
    public static String url,param;

    /**
     * ��������
     * @param channelId	Ƶ��ID
     * @param title	����
     * @param content ���ݣ�10000�ַ����ƣ�֧�������﷨�����ݺ�ͼƬ���ӱ���һ����ʣ��һ����null
     * @param imageUrl ͼƬ���ӣ������ǹٷ������ӣ�ͨ���ϴ���ԴͼƬ�ӿڿɻ��ͼƬ���ӣ����ݺ�ͼƬ���ӱ���һ����ʣ��һ����null
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addChannelArticle(String channelId, String title,String imageUrl, String content,String clientId, String token) throws IOException {
        return addChannelArticle(channelId, title, content ,imageUrl, BaseUtil.Authorization(clientId, token));
    }

    /**
     * ��������
     * @param channelId Ƶ��ID
     * @param title Dodo��
     * @param content ���ݣ�10000�ַ����ƣ�֧�������﷨�����ݺ�ͼƬ���ӱ���һ����ʣ��һ����null
     * @param imageUrl ͼƬ���ӣ������ǹٷ������ӣ�ͨ���ϴ���ԴͼƬ�ӿڿɻ��ͼƬ���ӣ����ݺ�ͼƬ���ӱ���һ����ʣ��һ����null
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addChannelArticle(String channelId,String title,String content,String imageUrl,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/article/add";
        param = "{" +
                "    \"channelId\": \""+ channelId +"\"," +
                "    \"title\": \""+ title + "\"," +
                "    \"content\": \""+ content + "\"," +
                "    \"imageUrl\": \""+ imageUrl + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }


    /**
     * ɾ���������ۻظ�
     * @param type ҵ�����ͣ�1�����ӣ�2���������ۣ�3���������ۻظ�
     * @param id ҵ��ID��ҵ������Ϊ1ʱ����������ID������Ϊ2ʱ��������������ID������Ϊ3ʱ�������������ۻظ�ID
     * @param channelId Ƶ����
     * @param clientId clientId
     * @param token token
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeChannelArticle(int type,String id,String channelId,String clientId, String token) throws IOException {
        return removeChannelArticle(type, id, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * ɾ���������ۻظ�
     * @param type ҵ�����ͣ�1�����ӣ�2���������ۣ�3���������ۻظ�
     * @param id ҵ��ID��ҵ������Ϊ1ʱ����������ID������Ϊ2ʱ��������������ID������Ϊ3ʱ�������������ۻظ�ID
     * @param channelId Ƶ����
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeChannelArticle(int type,String id,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/article/remove";
        param = "{" +
                "    \"id\": \""+ id +"\"," +
                "    \"type\": "+ type + "," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

}
