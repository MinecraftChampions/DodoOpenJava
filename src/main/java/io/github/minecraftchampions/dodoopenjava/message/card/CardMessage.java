package io.github.minecraftchampions.dodoopenjava.message.card;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.message.card.component.*;
import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.TextType;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.Theme;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 卡片消息
 *
 * @author qscbm187531
 * @author zimzaza4
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

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Builder {
        @Getter(AccessLevel.NONE)
        private final List<CardComponent> components = new ArrayList<>();

        private String content;

        private String title;

        private Theme theme = Theme.Default;

        public CardMessage.Builder theme(@NonNull Theme theme) {
            this.theme = theme;
            return this;
        }

        public CardMessage.Builder title(@NonNull String title) {
            this.title = title;
            return this;
        }

        public CardMessage.Builder content(@NonNull String content) {
            this.content = content;
            return this;
        }

        public CardMessage.Builder append(@NonNull CardComponent component) {
            synchronized (this.components) {
                this.components.add(component);
            }
            return this;
        }

        public CardMessage.Builder text(@NonNull SectionComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder text(@NonNull TextElement text) {
            append(SectionComponent.of(text));
            return this;
        }

        public CardMessage.Builder text(@NonNull String content, @NonNull TextType type) {
            append(SectionComponent.of(content, type));
            return this;
        }

        public CardMessage.Builder texts(@NonNull TextElement.NormalText text) {
            append(SectionComponent.of(text));
            return this;
        }

        public CardMessage.Builder section(@NonNull SectionComponent sectionComponent) {
            append(sectionComponent);
            return this;
        }

        public CardMessage.Builder button(@NonNull ButtonGroupComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder countdown(@NonNull CountdownComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder divider() {
            append(DividerComponent.of());
            return this;
        }

        public CardMessage.Builder header(@NonNull HeaderComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder header(@NonNull TextElement.NormalText text) {
            append(HeaderComponent.of(text));
            return this;
        }

        public CardMessage.Builder header(@NonNull String content, @NonNull TextType type) {
            append(HeaderComponent.of(content, type));
            return this;
        }

        public CardMessage.Builder image(@NonNull ImageComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder image(@NonNull ImageElement image) {
            append(ImageComponent.of(image));
            return this;
        }

        public CardMessage.Builder image(@NonNull String src) {
            append(ImageComponent.of(src));
            return this;
        }

        public CardMessage.Builder images(@NonNull ImageElement... images) {
            append(ImageGroupComponent.of(images));
            return this;
        }

        public CardMessage.Builder images(@NonNull ImageGroupComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder remark(@NonNull RemarkComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder listSelector(@NonNull ListSelectorComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder video(@NonNull VideoComponent component) {
            append(component);
            return this;
        }

        public CardMessage.Builder append(@NonNull CardComponent... components) {
            synchronized (this.components) {
                this.components.addAll(List.of(components));
            }
            return this;
        }

        public CardMessage.Builder append(@NonNull List<CardComponent> components) {
            synchronized (this.components) {
                this.components.addAll(components);
            }
            return this;
        }

        public CardMessage.Builder prepend(@NonNull CardComponent component) {
            synchronized (this.components) {
                this.components.add(0, component);
            }
            return this;
        }

        public CardMessage.Builder insert(int index, @NonNull CardComponent component) {
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
                components.forEach(component -> json.getJSONArray("components").put(component.toJsonObject()));
            }
            JSONObject jsonObject = new JSONObject(Map.of("card", json));
            if (content != null) {
                jsonObject.put("content", content);
            }
            return new CardMessage(jsonObject);
        }
    }
}