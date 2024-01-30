package io.github.minecraftchampions.dodoopenjava.message.card;

import io.github.minecraftchampions.dodoopenjava.message.card.element.InputElement;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
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

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("title",title,"elements",new JSONArray()));
        for (InputElement inputElement : list) {
            jsonObject.getJSONArray("elements").put(inputElement.toJSONObject());
        }
        return jsonObject;
    }
}
