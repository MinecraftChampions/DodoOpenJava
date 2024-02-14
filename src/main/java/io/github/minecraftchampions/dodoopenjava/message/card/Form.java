package io.github.minecraftchampions.dodoopenjava.message.card;

import io.github.minecraftchampions.dodoopenjava.message.card.element.InputElement;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 表单
 *
 * @author qscbm187531
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Form {
    @NonNull
    private String title;

    @Getter(AccessLevel.NONE)
    private final List<InputElement> list = new ArrayList<>();

    public Form append(@NonNull InputElement inputElement) {
        list.add(inputElement);
        return this;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject(Map.of("title", title, "elements", new JSONArray()));
        for (InputElement inputElement : list) {
            jsonObject.getJSONArray("elements").put(inputElement.toJsonObject());
        }
        return jsonObject;
    }
}
