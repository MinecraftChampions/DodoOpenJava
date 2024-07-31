package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

/**
 * 帖子频道API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class ChannelArticleApi {
    @NonNull
    private Bot bot;

    /**
     * 发布帖子
     *
     * @param channelId 频道ID
     * @param title     Dodo号
     * @param content   内容，10000个字符限制，支持菱形语法，内容和图片链接必填一个，剩下一个填null
     * @param imageUrl  图片链接，必须是官方的链接，通过上传资源图片接口可获得图片链接，内容和图片链接必填一个，剩下一个填null
     * @return result
     */
    public Result addChannelArticle(String channelId, String title, String content,
                                    String imageUrl) {
        String url = DodoOpenJava.BASEURL + "channel/article/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title)
                .put("channelId", channelId);
        if (content != null) {
            jsonObject.put("content", content);
        }
        if (imageUrl != null) {
            jsonObject.put("imageUrl", imageUrl);
        }
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }


    /**
     * 删除帖子评论回复
     *
     * @param type      业务类型，1：帖子，2：帖子评论，3：帖子评论回复
     * @param id        业务ID，业务类型为1时，代表帖子ID；类型为2时，代表帖子评论ID；类型为3时，代表帖子评论回复ID
     * @param channelId 频道号
     * @return result
     */
    public Result removeChannelArticle(int type, String id, String channelId) {
        String url = DodoOpenJava.BASEURL + "channel/article/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id)
                .put("channelId", channelId)
                .put("type", type);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

}
