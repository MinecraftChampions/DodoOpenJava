package io.github.minecraftchampions.dodoopenjava.message.card.element;

import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.Map;

/**
 * 选项元素
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class OptionElement extends Element.InteractiveElement {
    @NonNull
    private String name;

    private String desc;

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("name", name));
        if (desc != null) {
            jsonObject.put("desc", desc);
        }
        return jsonObject;
    }
}
