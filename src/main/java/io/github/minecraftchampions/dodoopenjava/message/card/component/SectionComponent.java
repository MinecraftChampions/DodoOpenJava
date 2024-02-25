package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.TextType;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

/**
 * 文本组件
 *
 * @author qscbm187531
 */
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Accessors(chain = true)
@Data
public class SectionComponent implements CardComponent {
    @NonNull
    protected TextElement text;

    protected final String type = "section";

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", getType());
        jsonObject.put("text", text.toJsonObject());
        return jsonObject;
    }

    public static SectionComponent of(@NonNull String content, @NonNull TextType type) {
        return of(TextElement.newNormalText(content, type));
    }

    public static SectionComponent of(@NonNull String content) {
        return of(TextElement.newNormalText(content));
    }

    public static SectionComponent of(@NonNull TextElement.NormalText... texts) {
        return of(TextElement.newParagraphText(texts));
    }

    public static SectionComponent of(@NonNull String... texts) {
        TextElement.NormalText[] normalTexts = new TextElement.NormalText[texts.length];
        for (int i = 0; i < texts.length; i++) {
            normalTexts[i] = TextElement.newNormalText(texts[i]);
        }
        return of(normalTexts);
    }

    public SectionComponent append(@NonNull TextElement.NormalText text) {
        if (this.text instanceof TextElement.NormalText normalText) {
            this.text = TextElement.newParagraphText(normalText, text);
        } else {
            TextElement.ParagraphText paragraphText = (TextElement.ParagraphText) this.text;
            paragraphText.append(text);
        }
        return this;
    }

    public SectionComponent prepend(@NonNull TextElement.NormalText text) {
        if (this.text instanceof TextElement.NormalText normalText) {
            this.text = TextElement.newParagraphText(text, normalText);
        } else {
            TextElement.ParagraphText paragraphText = (TextElement.ParagraphText) this.text;
            paragraphText.prepend(text);
            this.text = paragraphText;
        }
        return this;
    }
}