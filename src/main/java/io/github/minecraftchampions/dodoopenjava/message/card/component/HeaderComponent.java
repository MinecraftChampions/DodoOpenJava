package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class HeaderComponent extends CardComponent {
    @NonNull
    private String textType;

    @NonNull
    private TextType content;

    public String type = "header";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",getType());
        return null;
    }


}
