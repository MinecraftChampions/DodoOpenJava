package io.github.minecraftchampions.dodoopenjava.message.card.element;

import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.Map;

/**
 * 图片元素
 *
 * @author qscbm187531
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageElement extends AbstractElement.AbstractDataElement implements Accessory {
    @NonNull
    private String link;

    @Override
    public JSONObject toJsonObject() {
        return new JSONObject(Map.of("type", "image", "src", getLink()));
    }
}
