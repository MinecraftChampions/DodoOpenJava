package io.github.minecraftchampions.dodoopenjava.message.card.element;

import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.Map;

/**
 * 图片元素
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageElement extends Element.DataElement implements Accessory {
    @NonNull
    private String link;

    public JSONObject toJSONObject() {
        return new JSONObject(Map.of("type", "image", "src", getLink()));
    }
}
