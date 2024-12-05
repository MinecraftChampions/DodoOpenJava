package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.Map;

/**
 * 图片组件
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageComponent implements CardComponent {
    private final String type = "image";

    @NonNull
    private ImageElement image;

    @Override
    public JSONObject toJsonObject() {
        return new JSONObject(Map.of("type", type, "src", image.getLink()));
    }

    public static ImageComponent of(@NonNull String link) {
        return of(ImageElement.of(link));
    }
}