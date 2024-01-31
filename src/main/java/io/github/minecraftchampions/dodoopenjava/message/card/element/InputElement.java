package io.github.minecraftchampions.dodoopenjava.message.card.element;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Map;

/**
 * 输入框元素
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class InputElement extends Element.InteractiveElement {
    @NonNull
    private String title;

    @NonNull
    private String key;

    @Setter(AccessLevel.NONE)
    @NonNull
    private int rows;

    @Setter(AccessLevel.NONE)
    @NonNull
    private int minChar;

    @Setter(AccessLevel.NONE)
    @NonNull
    private int maxChar;

    private String placeholder;

    public InputElement setRows(int rows) {
        if (rows < 1 || rows > 4) {
            log.warn("输入框高度范围限制在1~4");
            return this;
        }
        this.rows = rows;
        return this;
    }

    public InputElement setMinChar(int minChar) {
        if (minChar < 0 || minChar > 4000) {
            log.warn("最小字符数限制在0~4000");
            return this;
        }
        this.minChar = minChar;
        return this;
    }

    public InputElement setMaxChar(int maxChar) {
        if (maxChar < 1 || maxChar > 4000) {
            log.warn("最大字符数限制在1~4000");
            return this;
        }
        this.maxChar = maxChar;
        return this;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", "input", "key", getKey(),
                "title", getTitle(), "rows", getRows(), "minChar", getMinChar(),
                "maxChar", getMaxChar()));
        if (placeholder != null) {
            jsonObject.put("placeholder", getPlaceholder());
        }
        return jsonObject;
    }
}
