package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import lombok.*;
import org.json.JSONObject;

import java.util.Map;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageComponent implements CardComponent{
    private final String type = "image";

    @NonNull
    private ImageElement image;

    @Override
    public JSONObject toJSONObject() {
        return new JSONObject(Map.of("type",getType(),"src", image.getLink()));
    }

    public static ImageComponent of(@NonNull String link) {
        return of(ImageElement.of(link));
    }
}
