package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.OptionElement;
import lombok.Getter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListSelector implements CardComponent {
    @Getter
    private final String type = "list-selector";

    private final List<OptionElement> elementList = new ArrayList<>();

    @Override
    public JSONObject toJSONObject() {
        return null;
    }
}
