package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.Element;
import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import io.github.minecraftchampions.dodoopenjava.message.card.element.TextElement;
import lombok.Getter;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemarkComponent implements CardComponent {
    private final List<Element.DataElement> elementList = new ArrayList<>();

    @Getter
    private final String type = "remark";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type",getType(),"elements",new JSONArray()));
        synchronized (elementList) {
            for (Element element : elementList) {
                if (element instanceof TextElement.ParagraphText paragraphText) {
                    paragraphText.forEach((text) -> jsonObject.getJSONArray("elements").put(text.toJSONObject()));
                } else {
                    jsonObject.getJSONArray("elements").put(element.toJSONObject());
                }
            }
        }
        return jsonObject;
    }

    private RemarkComponent() {

    }

    public RemarkComponent append(@NonNull Element.DataElement element) {
        synchronized (elementList) {
            this.elementList.add(element);
            return this;
        }
    }

    public RemarkComponent insert(int index, @NonNull Element.DataElement element) {
        synchronized (elementList) {
            this.elementList.add(index, element);
            return this;
        }
    }

    public RemarkComponent prepend(int index, @NonNull Element.DataElement element) {
        synchronized (elementList) {
            this.elementList.add(0, element);
            return this;
        }
    }

    public Element.DataElement get(int index) {
        synchronized (elementList) {
            return elementList.get(index);
        }
    }

    public Element.DataElement remove(int index) {
        synchronized (elementList) {
            return elementList.remove(index);
        }
    }

    public int size() {
        synchronized (elementList) {
            return elementList.size();
        }
    }

    public static RemarkComponent of() {
        return new RemarkComponent();
    }

    public static RemarkComponent of(@NonNull Element.DataElement... elements) {
        RemarkComponent remarkComponent = RemarkComponent.of();
        for (Element.DataElement element : elements) {
            remarkComponent.append(element);
        }
        return remarkComponent;
    }

    public RemarkComponent image(ImageElement image) {
        return append(image);
    }

    public RemarkComponent text(TextElement text) {
        return append(text);
    }
}
