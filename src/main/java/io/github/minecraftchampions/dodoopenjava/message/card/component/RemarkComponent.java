package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.AbstractElement;
import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 备注组件
 *
 * @author qscbm187531
 */
@Data
@NoArgsConstructor(staticName = "of")
public class RemarkComponent implements CardComponent {
    @Getter(AccessLevel.NONE)
    private final List<AbstractElement.AbstractDataElement> elementList = new ArrayList<>();

    private final String type = "remark";

    @Override
    public JSONObject toJsonObject() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject(Map.of("type", type, "elements", jsonArray));
        synchronized (elementList) {
            for (AbstractElement element : elementList) {
                if (element instanceof TextElement.ParagraphText paragraphText) {
                    paragraphText.forEach((text) -> jsonArray.put(text.toJsonObject()));
                } else {
                    jsonArray.put(element.toJsonObject());
                }
            }
        }
        return jsonObject;
    }

    public RemarkComponent append(@NonNull AbstractElement.AbstractDataElement element) {
        synchronized (elementList) {
            elementList.add(element);
            return this;
        }
    }

    public RemarkComponent insert(int index, @NonNull AbstractElement.AbstractDataElement element) {
        synchronized (elementList) {
            elementList.add(index, element);
            return this;
        }
    }

    public RemarkComponent prepend(int index, @NonNull AbstractElement.AbstractDataElement element) {
        synchronized (elementList) {
            elementList.add(0, element);
            return this;
        }
    }

    public AbstractElement.AbstractDataElement get(int index) {
        synchronized (elementList) {
            return elementList.get(index);
        }
    }

    public void remove(int index) {
        synchronized (elementList) {
            elementList.remove(index);
        }
    }

    public int size() {
        synchronized (elementList) {
            return elementList.size();
        }
    }

    public static RemarkComponent of(@NonNull AbstractElement.AbstractDataElement... elements) {
        RemarkComponent remarkComponent = RemarkComponent.of();
        remarkComponent.elementList.addAll(List.of(elements));
        return remarkComponent;
    }

    public RemarkComponent image(@NonNull ImageElement image) {
        return append(image);
    }

    public RemarkComponent image(@NonNull String link) {
        return append(ImageElement.of(link));
    }

    public RemarkComponent text(@NonNull TextElement text) {
        return append(text);
    }
}