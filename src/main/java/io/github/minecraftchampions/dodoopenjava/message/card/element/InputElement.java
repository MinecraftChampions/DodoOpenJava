package io.github.minecraftchampions.dodoopenjava.message.card.element;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import lombok.*;
import org.json.JSONObject;

import java.util.Map;

@Getter
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputElement extends Element.InteractiveElement {
    @Setter
    @NonNull
    private String title;

    @NonNull
    @Setter
    private String key;

    @NonNull
    private int rows;

    @NonNull
    private int minChar;

    @NonNull
    private int maxChar;

    @Setter
    private String placeholder;

    public void setRows(int rows) {
        if (rows < 1 || rows > 4) {
            DodoOpenJava.LOGGER.warn("输入框高度范围限制在1~4");
            return;
        }
        this.rows = rows;
    }

    public void setMinChar(int minChar) {
        if (minChar < 0 || minChar > 4000) {
            DodoOpenJava.LOGGER.warn("最小字符数限制在0~4000");
            return;
        }
        this.minChar = minChar;
    }

    public void setMaxChar(int maxChar) {
        if (maxChar < 1 || maxChar > 4000) {
            DodoOpenJava.LOGGER.warn("最大字符数限制在1~4000");
            return;
        }
        this.maxChar = maxChar;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", "input", "key", getKey(),
                "title", getTitle(), "rows", getRows(), "minChar", getMaxChar(),
                "maxChar", getMaxChar()));
        if (placeholder != null) {
            jsonObject.put("placeholder", getPlaceholder());
        }
        return jsonObject;
    }
}
