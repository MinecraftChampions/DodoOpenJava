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

/**
 * 文本元素
 *
 * @author qscbm187531
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextElement extends AbstractElement.AbstractDataElement {
    public static NormalText newNormalText(@NonNull String content, @NonNull TextType type) {
        return new NormalText(content, type);
    }

    public static NormalText newNormalText(@NonNull String content) {
        return new NormalText(content, TextType.PlainText);
    }


    public static ParagraphText newParagraphText(@NonNull NormalText... texts) {
        if (texts.length < ParagraphText.MIN_ROWS || texts.length > ParagraphText.MAX_ROWS) {
            log.error("多栏文本的栏数必须在2~6之间", new Throwable());
            return null;
        }
        ParagraphText paragraphText = new ParagraphText();
        for (NormalText text : texts) {
            paragraphText.append(text);
        }
        return paragraphText;
    }

    @Override
    public JSONObject toJsonObject() {
        return null;
    }

    /**
     * 普通文本
     */
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
        public JSONObject toJsonObject() {
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
            return toJsonObject().toString();
        }
    }

    /**
     * 多栏文本
     */
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ParagraphText extends TextElement {
        public static final int MAX_ROWS = 6;

        public static final int MIN_ROWS = 2;
        private final List<NormalText> textList = new ArrayList<>();

        @Override
        public JSONObject toJsonObject() {
            synchronized (textList) {
                JSONObject jsonObject = new JSONObject(Map.of("type", "paragraph", "cols", textList.size()));
                JSONArray jsonArray = new JSONArray();
                for (NormalText normalText : textList) {
                    jsonArray.put(normalText.toJsonObject());
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
                if (textList.size() == MAX_ROWS) {
                    log.warn("多栏文本栏数已达上限", new Throwable());
                    return this;
                }
                textList.add(normalText);
                return this;
            }
        }

        public ParagraphText prepend(@NonNull NormalText normalText) {
            synchronized (textList) {
                if (textList.size() == MAX_ROWS) {
                    log.warn("多栏文本栏数已达上限", new Throwable());
                    return this;
                }
                textList.add(0, normalText);
                return this;
            }
        }

        public ParagraphText insert(int index, @NonNull NormalText normalText) {
            synchronized (textList) {
                if (textList.size() == MAX_ROWS) {
                    log.warn("多栏文本栏数已达上限", new Throwable());
                    return this;
                }
                textList.add(index, normalText);
                return this;
            }
        }

        public void remove(int index) {
            synchronized (textList) {
                if (size() == MIN_ROWS) {
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
            return toJsonObject().toString();
        }

        public void forEach(@NonNull Consumer<NormalText> consumer) {
            for (int i = 0; i < size(); i++) {
                NormalText text = get(i);
                consumer.accept(text);
            }
        }
    }
}
