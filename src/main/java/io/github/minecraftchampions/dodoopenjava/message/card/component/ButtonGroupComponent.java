package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.ButtonElement;
import io.github.minecraftchampions.dodoopenjava.message.card.element.AbstractElement;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 按钮组组件
 *
 * @author qscbm187531
 */
@Data
@NoArgsConstructor(staticName = "of")
public class ButtonGroupComponent implements CardComponent {
    @Getter(AccessLevel.NONE)
    private final List<ButtonElement> elementList = new ArrayList<>();

    private final String type = "button-group";

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", type, "elements", new JSONArray()));
        synchronized (elementList) {
            for (AbstractElement element : elementList) {
                jsonObject.getJSONArray("elements").put(element.toJsonObject());
            }
        }
        return jsonObject;
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

    public static ButtonGroupComponent of(@NonNull ButtonElement... elements) {
        ButtonGroupComponent component = ButtonGroupComponent.of();
        component.elementList.addAll(List.of(elements));
        return component;
    }

    public ButtonGroupComponent button(@NonNull ButtonElement buttonElement) {
        return append(buttonElement);
    }
}