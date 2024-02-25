package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.Map;

/**
 * 分割线组件
 *
 * @author qscbm187531
 */
@Data
@NoArgsConstructor(staticName = "of")
public class DividerComponent implements CardComponent {
    private final String type = "divider";

    @Override
    public JSONObject toJsonObject() {
        return new JSONObject(Map.of("type", getType()));
    }
}