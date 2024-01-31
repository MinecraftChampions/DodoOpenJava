package io.github.minecraftchampions.dodoopenjava.message.card.element;

import io.github.minecraftchampions.dodoopenjava.message.card.enums.TextType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public abstract class TextElement extends Element.DataElement {
    public static NormalText newNormalText(@NonNull String content, @NonNull TextType type) {
        return new NormalText(content, type);
    }

    public static NormalText newNormalText(@NonNull String content) {
        return new NormalText(content, TextType.PlainText);
    }


    public static ParagraphText newParagraphText(@NonNull NormalText... texts) {
        if (texts.length <= 1 || texts.length > 6) {
            log.error("多栏文本的栏数必须在2~6之间", new Throwable());
            return null;
        }
        ParagraphText paragraphText = new ParagraphText();
        for (NormalText text : texts) {
            paragraphText.append(text);
        }
        return paragraphText;
    }

    @EqualsAndHashCode(callSuper = true)
    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class NormalText extends TextElement {
        @NonNull
        private String content;
        @NonNull
        private TextType type;

        @Override
        public JSONObject toJSONObject() {
            return new JSONObject(Map.of("content", content, "type", type.getType()));
        }

        public static NormalText of(String content, TextType type) {
            return TextElement.newNormalText(content, type);
        }

        public static NormalText of(String content) {
            return TextElement.newNormalText(content, TextType.PlainText);
        }

        @Override
        public String toString() {
            return toJSONObject().toString();
        }
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ParagraphText extends TextElement {
        private final List<NormalText> textList = new ArrayList<>();

        @Override
        public JSONObject toJSONObject() {
            synchronized (textList) {
                JSONObject jsonObject = new JSONObject(Map.of("type", "paragraph", "cols", textList.size()));
                JSONArray jsonArray = new JSONArray();
                for (NormalText normalText : textList) {
                    jsonArray.put(normalText.toJSONObject());
                }
                jsonObject.put("fields", jsonArray);
                return jsonObject;
            }
        }

        public NormalText get(int size) {
            synchronized (textList) {
                return textList.get(size);
            }
        }

        public ParagraphText append(@NonNull NormalText normalText) {
            synchronized (textList) {
                if (textList.size() == 6) {
                    log.warn("多栏文本栏数已达上限", new Throwable());
                    return this;
                }
                textList.add(normalText);
                return this;
            }
        }

        public ParagraphText prepend(@NonNull NormalText normalText) {
            synchronized (textList) {
                if (textList.size() == 6) {
                    log.warn("多栏文本栏数已达上限", new Throwable());
                    return this;
                }
                textList.add(0, normalText);
                return this;
            }
        }

        public ParagraphText insert(int index, @NonNull NormalText normalText) {
            synchronized (textList) {
                if (textList.size() == 6) {
                    log.warn("多栏文本栏数已达上限", new Throwable());
                    return this;
                }
                textList.add(index, normalText);
                return this;
            }
        }

        public void remove(int index) {
            synchronized (textList) {
                if (size() == 2) {
                    log.warn("多栏文本栏数已达下限", new Throwable());
                    return;
                }
                textList.remove(index);
            }
        }

        public int size() {
            return textList.size();
        }

        @Override
        public String toString() {
            return toJSONObject().toString();
        }

        public void forEach(@NonNull Consumer<NormalText> consumer) {
            for (int i = 0; i < size(); i++) {
                NormalText text = get(i);
                consumer.accept(text);
            }
        }
    }
}
