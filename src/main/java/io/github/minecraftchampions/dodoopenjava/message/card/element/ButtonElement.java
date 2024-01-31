package io.github.minecraftchampions.dodoopenjava.message.card.element;

import io.github.minecraftchampions.dodoopenjava.message.card.Form;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.ActionType;
import io.github.minecraftchampions.dodoopenjava.message.card.enums.ButtonColor;
import lombok.*;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 按钮元素
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class ButtonElement extends Element.InteractiveElement implements Accessory {
    @NonNull
    private ButtonColor color;

    @NonNull
    private String name;

    @NonNull
    private Action action;

    private String interactCustomId;

    @Override
    public JSONObject toJSONObject() {
        HashMap<String, Object> map1 = new HashMap<>(Map.of("type", "button", "color", color.toString(),
                "name", name));
        map1.putAll(action.toJSONObject().toMap());
        if (interactCustomId != null) {
            map1.put("interactCustomId", interactCustomId);
        }
        return new JSONObject(map1);
    }

    public ButtonElement of(@NonNull ButtonColor color, @NonNull String name,
                            @NonNull Action action, @NonNull String interactCustomId) {
        ButtonElement buttonElement = ButtonElement.of(color, name, action);
        buttonElement.setInteractCustomId(interactCustomId);
        return buttonElement;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Action {
        @NonNull
        private ActionType type;

        @NonNull
        private String value = "";

        public static FormAction form(@NonNull Form form) {
            return new FormAction(form);
        }

        public JSONObject toJSONObject() {
            return new JSONObject(Map.of("click", Map.of("action", type, "value", value)));
        }

        @Getter
        @Setter
        @RequiredArgsConstructor(staticName = "of")
        public static class FormAction extends Action {
            @NonNull
            private Form form;

            @Override
            public JSONObject toJSONObject() {
                return new JSONObject(Map.of("click", Map.of("action", "form", "value", getValue()),
                        "form", form.toJSONObject()));
            }
        }
    }
}
