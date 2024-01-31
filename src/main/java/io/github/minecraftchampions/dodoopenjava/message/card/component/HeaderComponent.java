package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.TextType;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class HeaderComponent implements CardComponent {
    @NonNull
    private TextElement.NormalText text;

    private final String type = "header";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", getType());
        jsonObject.put("text", text.toJSONObject());
        return jsonObject;
    }

    public static HeaderComponent of(@NonNull String content, @NonNull TextType type) {
        return of(TextElement.newNormalText(content, type));
    }

    public static HeaderComponent of(@NonNull String content) {
        return of(TextElement.newNormalText(content));
    }
}
