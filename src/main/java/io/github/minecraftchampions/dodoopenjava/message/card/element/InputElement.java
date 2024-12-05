package io.github.minecraftchampions.dodoopenjava.message.card.element;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Map;

/**
 * 输入框元素
 *
 * @author qscbm187531
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class InputElement extends AbstractElement.AbstractInteractiveElement {
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

    public static final int MAX_ROWS = 4;

    public static final int MIN_ROWS = 1;

    public static final int MAX_CHAR_VALUE_CEILING = 4000;

    public InputElement setRows(int rows) {
        if (rows < MIN_ROWS || rows > MAX_ROWS) {
            log.warn("输入框高度范围限制在1~4");
            return this;
        }
        this.rows = rows;
        return this;
    }

    public InputElement setMinChar(int minChar) {
        if (minChar < 0 || minChar > MAX_CHAR_VALUE_CEILING) {
            log.warn("最小字符数限制在0~4000");
            return this;
        }
        this.minChar = minChar;
        return this;
    }

    public InputElement setMaxChar(int maxChar) {
        if (maxChar < 1 || maxChar > MAX_CHAR_VALUE_CEILING) {
            log.warn("最大字符数限制在1~4000");
            return this;
        }
        this.maxChar = maxChar;
        return this;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", "input", "key", key,
                "title", title, "rows", rows, "minChar", minChar,
                "maxChar", maxChar));
        if (placeholder != null) {
            jsonObject.put("placeholder", placeholder);
        }
        return jsonObject;
    }
}