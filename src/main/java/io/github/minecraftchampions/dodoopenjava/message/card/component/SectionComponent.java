package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.AbstractTextElement;
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
    protected AbstractTextElement text;

    protected final String type = "section";

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", getType());
        jsonObject.put("text", text.toJsonObject());
        return jsonObject;
    }

    public static SectionComponent of(@NonNull String content, @NonNull TextType type) {
        return of(AbstractTextElement.newNormalText(content, type));
    }

    public static SectionComponent of(@NonNull String content) {
        return of(AbstractTextElement.newNormalText(content));
    }

    public static SectionComponent of(@NonNull AbstractTextElement.NormalText... texts) {
        return of(AbstractTextElement.newParagraphText(texts));
    }

    public static SectionComponent of(@NonNull String... texts) {
        AbstractTextElement.NormalText[] normalTexts = new AbstractTextElement.NormalText[texts.length];
        for (int i = 0; i < texts.length; i++) {
            normalTexts[i] = AbstractTextElement.newNormalText(texts[i]);
        }
        return of(normalTexts);
    }

    public SectionComponent append(@NonNull AbstractTextElement.NormalText text) {
        if (this.text instanceof AbstractTextElement.NormalText normalText) {
            this.text = AbstractTextElement.newParagraphText(normalText, text);
        } else {
            AbstractTextElement.ParagraphText paragraphText = (AbstractTextElement.ParagraphText) this.text;
            paragraphText.append(text);
        }
        return this;
    }

    public SectionComponent prepend(@NonNull AbstractTextElement.NormalText text) {
        if (this.text instanceof AbstractTextElement.NormalText normalText) {
            this.text = AbstractTextElement.newParagraphText(text, normalText);
        } else {
            AbstractTextElement.ParagraphText paragraphText = (AbstractTextElement.ParagraphText) this.text;
            paragraphText.prepend(text);
            this.text = paragraphText;
        }
        return this;
    }
}
