package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 帖子频道API
 * @author qscbm187531
 */
public class ChannelArticleApi {
    public static String url,param;

    /**
     * 发布帖子
     * @param channelId	频道ID
     * @param title	标题
     * @param content 内容，10000字符限制，支持菱形语法，内容和图片链接必填一个，剩下一个填null
     * @param imageUrl 图片链接，必须是官方的链接，通过上传资源图片接口可获得图片链接，内容和图片链接必填一个，剩下一个填null
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannelArticle(String channelId, String title,String imageUrl, String content,String clientId, String token) throws IOException {
        return addChannelArticle(channelId, title, content ,imageUrl, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 发布帖子
     * @param channelId 频道ID
     * @param title Dodo好
     * @param content 内容，10000字符限制，支持菱形语法，内容和图片链接必填一个，剩下一个填null
     * @param imageUrl 图片链接，必须是官方的链接，通过上传资源图片接口可获得图片链接，内容和图片链接必填一个，剩下一个填null
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannelArticle(String channelId,String title,String content,String imageUrl,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/article/add";
        param = "{" +
                "    \"channelId\": \""+ channelId +"\"," +
                "    \"title\": \""+ title + "\"," +
                "    \"content\": \""+ content + "\"," +
                "    \"imageUrl\": \""+ imageUrl + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }


    /**
     * 删除帖子评论回复
     * @param type 业务类型，1：帖子，2：帖子评论，3：帖子评论回复
     * @param id 业务ID，业务类型为1时，代表帖子ID；类型为2时，代表帖子评论ID；类型为3时，代表帖子评论回复ID
     * @param channelId 频道号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeChannelArticle(int type,String id,String channelId,String clientId, String token) throws IOException {
        return removeChannelArticle(type, id, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 删除帖子评论回复
     * @param type 业务类型，1：帖子，2：帖子评论，3：帖子评论回复
     * @param id 业务ID，业务类型为1时，代表帖子ID；类型为2时，代表帖子评论ID；类型为3时，代表帖子评论回复ID
     * @param channelId 频道号
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeChannelArticle(int type,String id,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/article/remove";
        param = "{" +
                "    \"id\": \""+ id +"\"," +
                "    \"type\": "+ type + "," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

}
