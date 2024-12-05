package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.Map;

/**
 * 视频组件
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class VideoComponent implements CardComponent {
    private final String type = "video";

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", type,
                "cover", cover, "src", link));
        if (title != null) {
            jsonObject.put("title", title);
        }
        return jsonObject;
    }

    private String title;

    @NonNull
    private String cover;

    @NonNull
    private String link;
}