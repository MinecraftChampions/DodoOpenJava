package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.enums.CountdownStyle;
import lombok.*;
import org.json.JSONObject;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountdownComponent implements CardComponent {
    private final String type = "countdown";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type",getType(),
                "style",getStyle().toString(),"endTime",getEndTime()));
        if (title != null) {
            jsonObject.put("title",getTitle());
        }
        return jsonObject;
    }

    private String title;

    @NonNull
    private CountdownStyle style;

    @NonNull
    private long endTime;
}
