package io.github.minecraftchampions.dodoopenjava.message.video;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.*;
import org.json.JSONObject;

/**
 * 视频消息组件
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VideoMessage implements Message {
    @NonNull
    private String url;

    private String coverUrl;

    private Long duration;

    private Long size;

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
