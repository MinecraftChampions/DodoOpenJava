package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.ButtonElement;
import io.github.minecraftchampions.dodoopenjava.message.card.element.Element;
import lombok.Getter;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ButtonGroupComponent implements CardComponent {
    private final List<ButtonElement> elementList = new ArrayList<>();

    @Getter
    private final String type = "button-group";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", getType(), "elements", new JSONArray()));
        synchronized (elementList) {
            for (Element element : elementList) {
                jsonObject.getJSONArray("elements").put(element.toJSONObject());
            }
        }
        return jsonObject;
    }

    private ButtonGroupComponent() {

    }

    public ButtonGroupComponent append(@NonNull ButtonElement element) {
        synchronized (elementList) {
            this.elementList.add(element);
            return this;
        }
    }

    public ButtonGroupComponent insert(int index, @NonNull ButtonElement element) {
        synchronized (elementList) {
            this.elementList.add(index, element);
            return this;
        }
    }

    public ButtonGroupComponent prepend(int index, @NonNull ButtonElement element) {
        synchronized (elementList) {
            this.elementList.add(0, element);
            return this;
        }
    }

    public ButtonElement get(int index) {
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

    public static ButtonGroupComponent of() {
        return new ButtonGroupComponent();
    }

    public static ButtonGroupComponent of(@NonNull ButtonElement... elements) {
        ButtonGroupComponent component = ButtonGroupComponent.of();
        for (ButtonElement element : elements) {
            component.append(element);
        }
        return component;
    }

    public ButtonGroupComponent button(ButtonElement buttonElement) {
        return append(buttonElement);
    }
}
