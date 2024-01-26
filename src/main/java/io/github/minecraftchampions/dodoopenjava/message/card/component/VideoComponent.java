package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.*;
import org.json.JSONObject;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VideoComponent implements CardComponent {
    private final String type = "video";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type",getType(),
                "cover",getCover(),"src",getLink()));
        if (title != null) {
            jsonObject.put("title",getTitle());
        }
        return jsonObject;
    }

    private String title;

    @NonNull
    private String cover;

    @NonNull
    private String link;
}
