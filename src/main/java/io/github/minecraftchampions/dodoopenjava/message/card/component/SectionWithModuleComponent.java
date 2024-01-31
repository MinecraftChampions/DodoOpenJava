package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.Accessory;
import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.AlignType;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.TextType;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

/**
 * 文字+模块组件
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SectionWithModuleComponent extends SectionComponent {
    private AlignType align;

    @NonNull
    private Accessory accessory;

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = super.toJSONObject();
        jsonObject.put("accessory", accessory.toJSONObject());
        if (align != null) {
            jsonObject.put("align", align.toString());
        }
        return jsonObject;
    }

    public static SectionWithModuleComponent of(@NonNull TextElement textElement,
                                                @NonNull Accessory accessory) {
        SectionWithModuleComponent component = new SectionWithModuleComponent();
        component.text = textElement;
        component.accessory = accessory;
        return component;
    }


    public static SectionWithModuleComponent of(@NonNull String content, @NonNull TextType type,
                                                @NonNull Accessory accessory) {
        return of(TextElement.newNormalText(content, type), accessory);
    }

    public static SectionWithModuleComponent of(@NonNull String content, @NonNull Accessory accessory) {
        return of(TextElement.newNormalText(content), accessory);
    }

    public static SectionWithModuleComponent of(@NonNull Accessory accessory,
                                                @NonNull TextElement.NormalText... texts) {
        return of(TextElement.newParagraphText(texts), accessory);
    }

    public static SectionWithModuleComponent of(@NonNull Accessory accessory, @NonNull String... texts) {
        TextElement.NormalText[] normalTexts = new TextElement.NormalText[texts.length];
        for (int i = 0; i < texts.length; i++) {
            normalTexts[i] = TextElement.newNormalText(texts[i]);
        }
        return of(accessory, normalTexts);
    }
}
