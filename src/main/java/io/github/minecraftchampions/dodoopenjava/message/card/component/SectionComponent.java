package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.TextType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

@RequiredArgsConstructor(staticName = "of")
public class SectionComponent implements CardComponent {
    @NonNull
    private TextElement text;

    @Getter
    private final String type = "section";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", getType());
        jsonObject.put("text", text.toJSONObject());
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

    public SectionComponent append(@NonNull TextElement.NormalText text) {
        if (this.text instanceof TextElement.NormalText normalText) {
            this.text = TextElement.newParagraphText(normalText,text);
        } else {
            TextElement.ParagraphText paragraphText = (TextElement.ParagraphText) this.text;
            paragraphText.append(text);
        }
        return this;
    }

    public SectionComponent prepend(@NonNull TextElement.NormalText text) {
        if (this.text instanceof TextElement.NormalText normalText) {
            this.text = TextElement.newParagraphText(text,normalText);
        } else {
            TextElement.ParagraphText paragraphText = (TextElement.ParagraphText) this.text;
            paragraphText.prepend(text);
            this.text = paragraphText;
        }
        return this;
    }
}
