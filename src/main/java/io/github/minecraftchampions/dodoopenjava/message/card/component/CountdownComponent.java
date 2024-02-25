package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.enums.CountdownStyle;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.Map;

/**
 * 倒计时组件
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountdownComponent implements CardComponent {
    private final String type = "countdown";

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", getType(),
                "style", getStyle().toString(), "endTime", getEndTime()));
        if (title != null) {
            jsonObject.put("title", getTitle());
        }
        return jsonObject;
    }

    private String title;

    @NonNull
    private CountdownStyle style;

    @NonNull
    private long endTime;
}