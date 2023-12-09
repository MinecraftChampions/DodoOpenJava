package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.TextType;
import org.json.JSONObject;

/**
 * 标题组件
 */
public class HeaderComponent extends Component {
    /**
     * 初始化
     *
     * @param type  文本类型
     * @param title 内容
     */
    public HeaderComponent(TextType type, String title) {
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
    public void editTextType(TextType type) {
        jsonCard.getJSONObject("text").put("type", type.getType());
    }

    /**
     * 编辑文本
     *
     * @param context 文本
     */
    public void editContent(String context) {
        jsonCard.getJSONObject("text").put("content", context);
    }
}