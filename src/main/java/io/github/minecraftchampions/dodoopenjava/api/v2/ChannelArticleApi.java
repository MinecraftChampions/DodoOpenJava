package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 帖子频道API
 */
public class ChannelArticleApi {
    /**
     * 发布帖子
     *
     * @param clientId  clientId
     * @param token     token
     * @param channelId 频道ID
     * @param title     标题
     * @param content   内容，10000字符限制，支持菱形语法，内容和图片链接必填一个，剩下一个填null
     * @param imageUrl  图片链接，必须是官方的链接，通过上传资源图片接口可获得图片链接，内容和图片链接必填一个，剩下一个填null
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannelArticle(String clientId, String token, String channelId, String title, String imageUrl, String content) throws IOException {
        return addChannelArticle(BaseUtil.Authorization(clientId, token), channelId, title, content, imageUrl);
    }

    /**
     * 发布帖子
     *
     * @param authorization authorization
     * @param channelId     频道ID
     * @param title         Dodo号
     * @param content       内容，10000字符限制，支持菱形语法，内容和图片链接必填一个，剩下一个填null
     * @param imageUrl      图片链接，必须是官方的链接，通过上传资源图片接口可获得图片链接，内容和图片链接必填一个，剩下一个填null
     * @return JSON对象
     * @throws IOException 失败后抛出
     */

    public static JSONObject addChannelArticle(String authorization, String channelId, String title, String content, String imageUrl) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/article/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title)
                .put("channelId", channelId)
                .put("content", content)
                .put("imageUrl", imageUrl);
        return new JSONObject(NetUtil.sendRequest(jsonObject.toString(), url, authorization));
    }


    /**
     * 删除帖子评论回复
     *
     * @param clientId  clientId
     * @param token     token
     * @param type      业务类型，1：帖子，2：帖子评论，3：帖子评论回复
     * @param id        业务ID，业务类型为1时，代表帖子ID；类型为2时，代表帖子评论ID；类型为3时，代表帖子评论回复ID
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeChannelArticle(String clientId, String token, int type, String id, String channelId) throws IOException {
        return removeChannelArticle(BaseUtil.Authorization(clientId, token), type, id, channelId);
    }

    /**
     * 删除帖子评论回复
     *
     * @param authorization authorization
     * @param type          业务类型，1：帖子，2：帖子评论，3：帖子评论回复
     * @param id            业务ID，业务类型为1时，代表帖子ID；类型为2时，代表帖子评论ID；类型为3时，代表帖子评论回复ID
     * @param channelId     频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */

    public static JSONObject removeChannelArticle(String authorization, int type, String id, String channelId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/article/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id)
                .put("channelId", channelId)
                .put("type", type);
        return new JSONObject(NetUtil.sendRequest(jsonObject.toString(), url, authorization));
    }

}
