package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.Map;

@Getter
@NoArgsConstructor(staticName = "of")
public class DividerComponent implements CardComponent{
    private final String type = "divider";

    @Override
    public JSONObject toJSONObject() {
        return new JSONObject(Map.of("type",getType()));
    }
}
