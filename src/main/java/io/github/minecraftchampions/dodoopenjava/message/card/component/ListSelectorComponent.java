package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.OptionElement;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ListSelectorComponent implements CardComponent {
    private final String type = "list-selector";

    @Getter(AccessLevel.NONE)
    private final List<OptionElement> elementList = new ArrayList<>();

    @NonNull
    private int min;

    @NonNull
    private int max;

    private String interactCustomId;

    private String placeholder;

    public static ListSelectorComponent of(int min, int max, String interactCustomId) {
        ListSelectorComponent listSelectorComponent = of(min, max);
        listSelectorComponent.setInteractCustomId(interactCustomId);
        return listSelectorComponent;
    }

    public static ListSelectorComponent of(int min, int max, OptionElement... elements) {
        ListSelectorComponent listSelectorComponent = of(min, max);
        listSelectorComponent.elementList.addAll(List.of(elements));
        return listSelectorComponent;
    }

    public static ListSelectorComponent of(int min, int max, String interactCustomId,
                                           OptionElement... elements) {
        ListSelectorComponent listSelectorComponent = of(min, max, interactCustomId);
        listSelectorComponent.elementList.addAll(List.of(elements));
        return listSelectorComponent;
    }

    public static ListSelectorComponent of(int min, int max, String interactCustomId,
                                           String placeholder, OptionElement... elements) {
        ListSelectorComponent listSelectorComponent = of(min, max, interactCustomId, placeholder);
        listSelectorComponent.elementList.addAll(List.of(elements));
        return listSelectorComponent;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", getType(), "min", getMin(), "max", getMax(), "elements", new JSONArray()));
        if (placeholder != null) {
            jsonObject.put("placeholder", placeholder);
        }
        if (interactCustomId != null) {
            jsonObject.put("interactCustomId", interactCustomId);
        }
        for (OptionElement element : elementList) {
            jsonObject.getJSONArray("elements").put(element.toJSONObject());
        }
        return jsonObject;
    }

    public ListSelectorComponent append(@NonNull OptionElement element) {
        synchronized (elementList) {
            elementList.add(element);
            return this;
        }
    }

    public OptionElement get(int size) {
        synchronized (elementList) {
            return elementList.get(size);
        }
    }


    public ListSelectorComponent prepend(@NonNull OptionElement element) {
        synchronized (elementList) {
            elementList.add(0, element);
            return this;
        }
    }

    public ListSelectorComponent insert(int index, @NonNull OptionElement element) {
        synchronized (elementList) {
            elementList.add(index, element);
            return this;
        }
    }

    public void remove(int index) {
        synchronized (elementList) {
            elementList.remove(index);
        }
    }

    public int size() {
        return elementList.size();
    }
}
