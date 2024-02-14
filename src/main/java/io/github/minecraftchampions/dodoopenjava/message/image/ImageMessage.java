package io.github.minecraftchampions.dodoopenjava.message.image;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.*;
import org.json.JSONObject;

/**
 * 图片消息组件
 *
 * @author qscbm187531
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageMessage implements Message {
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
