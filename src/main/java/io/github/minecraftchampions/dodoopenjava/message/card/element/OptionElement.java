package io.github.minecraftchampions.dodoopenjava.message.card.element;

import lombok.*;
import org.json.JSONObject;

import java.util.Map;

@Getter
@Setter
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
