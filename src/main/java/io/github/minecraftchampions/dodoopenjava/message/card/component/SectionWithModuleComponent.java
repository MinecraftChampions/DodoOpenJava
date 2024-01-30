package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.Accessory;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.AlignType;
import lombok.*;
import org.json.JSONObject;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
public class SectionWithModuleComponent extends SectionComponent {
    private AlignType align;

    @NonNull
    private Accessory accessory;

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = super.toJSONObject();
        jsonObject.put("accessory", accessory.toJSONObject());
        if (align != null) {
            jsonObject.put("align", align.toString());
        }
        return jsonObject;
    }
}
