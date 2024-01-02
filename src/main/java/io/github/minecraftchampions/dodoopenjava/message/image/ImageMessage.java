package io.github.minecraftchampions.dodoopenjava.message.image;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

/**
 * 图片消息组件
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageMessage extends Message {
    @NonNull
    private String url;

    @NonNull
    private Integer width;

    @NonNull
    private Integer height;

    private Integer isOriginal;

    @Override
    public JSONObject toMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        jsonObject.put("width", width);
        jsonObject.put("height", height);
        if (isOriginal != null) {
            jsonObject.put("isOriginal", isOriginal);
        }
        return jsonObject;
    }

    @Override
    public int getType() {
        return 2;
    }

    /**
     * 设置图片链接
     *
     * @param url 图片
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    /**
     * 设置宽度
     *
     * @param width 宽度
     */
    public void setWidth(@NonNull Integer width) {
        this.width = width;
    }

    /**
     * 设置高度
     *
     * @param height 高度
     */
    public void setHeight(@NonNull Integer height) {
        this.height = height;
    }

    /**
     * 设置是否原图
     *
     * @param isOriginal 0否1是
     */
    public void setIsOriginal(@NonNull Integer isOriginal) {
        this.isOriginal = isOriginal;
    }

    /**
     * 设置是否原图
     *
     * @param isOriginal ture/false
     */
    public void setIsOriginal(boolean isOriginal) {
        if (isOriginal) {
            this.isOriginal = 1;
        } else {
            this.isOriginal = 0;
        }
    }
}
