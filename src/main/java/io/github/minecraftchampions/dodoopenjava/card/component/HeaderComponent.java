package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.TextType;
import lombok.NonNull;
import org.json.JSONObject;

/**
 * 标题组件
 */
public class HeaderComponent extends CardComponent {
    /**
     * 初始化
     *
     * @param type  文本类型
     * @param title 内容
     */
    public HeaderComponent(@NonNull TextType type, @NonNull String title) {
        jsonCard.put("type", "header");
        JSONObject object = new JSONObject();
        object.put("type", type.getType());
        object.put("content", title);
        jsonCard.put("text", object);
    }

    /**
     * 编辑类型
     *
     * @param type 类型
     */
    public void editTextType(@NonNull TextType type) {
        jsonCard.getJSONObject("text").put("type", type.getType());
    }

    /**
     * 编辑文本
     *
     * @param context 文本
     */
    public void editContent(@NonNull String context) {
        jsonCard.getJSONObject("text").put("content", context);
    }
}