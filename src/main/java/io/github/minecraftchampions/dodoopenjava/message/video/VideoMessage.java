package io.github.minecraftchampions.dodoopenjava.message.video;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

/**
 * 视频消息组件
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class VideoMessage extends Message {
    @NonNull
    private String url;

    private String coverUrl;

    private Long duration;

    private Long size;

    /**
     * 设置视频时长
     *
     * @param duration 时长
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * 设置视频大小
     *
     * @param size 大小
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 设置视频链接
     *
     * @param url 链接
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    /**
     * 设置视频大小
     *
     * @param coverUrl 大小
     */
    public void setCover(@NonNull String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public JSONObject toMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        if (coverUrl != null) {
            jsonObject.put("coverUrl", coverUrl);
        }
        if (size != null) {
            jsonObject.put("size", size);
        }
        if (duration != null) {
            jsonObject.put("duration", duration);
        }
        return jsonObject;
    }

    @Override
    public int getType() {
        return 3;
    }
}
