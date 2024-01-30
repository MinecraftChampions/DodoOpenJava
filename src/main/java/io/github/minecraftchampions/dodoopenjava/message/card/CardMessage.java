package io.github.minecraftchampions.dodoopenjava.message.card;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.message.card.component.*;
import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.TextType;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.Theme;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 卡片消息
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CardMessage implements Message {
    private final JSONObject jsonObject;

    @Override
    public JSONObject toMessage() {
        return jsonObject;
    }

    @Override
    public int getType() {
        return 6;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Builder {
        @Getter(AccessLevel.NONE)
        private final List<CardComponent> components = new ArrayList<>();

        private String content;

        private String title;

        private Theme theme = Theme.Default;

        public CardMessage.Builder theme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public CardMessage.Builder title(String title ) {
            this.title = title;
            return this;
        }

        public CardMessage.Builder content(String content) {
            this.content = content;
            return this;
        }

        public CardMessage.Builder append(CardComponent component) {
            synchronized (this.components) {
                this.components.add(component);
            }
            return this;
        }

        public CardMessage.Builder text(SectionComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder text(TextElement text) {
            append(SectionComponent.of(text));
            return this;
        }

        public CardMessage.Builder text(String content, TextType type) {
            append(SectionComponent.of(content, type));
            return this;
        }

        public CardMessage.Builder texts(TextElement.NormalText text) {
            append(SectionComponent.of(text));
            return this;
        }

        public CardMessage.Builder section(SectionComponent sectionComponent) {
            append(sectionComponent);
            return this;
        }

        public CardMessage.Builder button(ButtonGroupComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder countdown(CountdownComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder divider() {
            append(DividerComponent.of());
            return this;
        }

        public CardMessage.Builder header(HeaderComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder header(TextElement.NormalText text) {
            append(HeaderComponent.of(text));
            return this;
        }

        public CardMessage.Builder header(String content,TextType type) {
            append(HeaderComponent.of(content,type));
            return this;
        }

        public CardMessage.Builder image(ImageComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder image(ImageElement image) {
            append(ImageComponent.of(image));
            return this;
        }

        public CardMessage.Builder image(String src) {
            append(ImageComponent.of(src));
            return this;
        }

        public CardMessage.Builder images(ImageElement... images) {
            append(ImageGroupComponent.of(images));
            return this;
        }

        public CardMessage.Builder images(ImageGroupComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder remark(RemarkComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder listSelector(ListSelectorComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder video(VideoComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder append(CardComponent... components) {
            synchronized (this.components) {
                this.components.addAll(List.of(components));
            }
            return this;
        }

        public CardMessage.Builder append(List<CardComponent> components) {
            synchronized (this.components) {
                this.components.addAll(components);
            }
            return this;
        }

        public CardMessage.Builder prepend(CardComponent component) {
            synchronized (this.components) {
                this.components.add(0, component);
            }
            return this;
        }

        public CardMessage.Builder insert(int index, CardComponent component) {
            synchronized (this.components) {
                this.components.add(index, component);
            }
            return this;
        }

        public CardMessage build() {
            JSONObject json = new JSONObject(Map.of("type", "card", "components", new JSONArray(),
                    "theme", theme.toString()));
            if (title != null) {
                json.put("title", title);
            }
            synchronized (this.components) {
                components.forEach(component -> json.getJSONArray("components").put(component.toJSONObject()));
            }
            JSONObject jsonObject = new JSONObject(Map.of("card", json));
            if (content != null) {
                jsonObject.put("content", content);
            }
            return new CardMessage(jsonObject);
        }
    }
}
