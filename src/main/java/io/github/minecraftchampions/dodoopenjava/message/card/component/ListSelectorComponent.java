package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 列表选择器
 */
public class ListSelectorComponent extends CardComponent {
    /**
     * 初始化
     * <p>
     * 不需要参数的请填写null
     *
     * @param interactCustomId 自定义交互id
     * @param placeholder      输入框提示
     * @param min              最少选中个数
     * @param max              最多选中个数
     * @param element          数据，为了方便用HashMap存储，前面为选项名。后面为选项描述
     */
    public ListSelectorComponent(String interactCustomId, String placeholder, int min,
                                 int max, HashMap<String, String> element) {
        jsonCard.put("type", "list-selector");
        jsonCard.put("interactCustomId", interactCustomId);
        jsonCard.put("placeholder", placeholder);
        jsonCard.put("min", min);
        jsonCard.put("max", max);
        jsonCard.put("elements", new JSONArray());
        element.forEach((name, desc) -> {
            JSONObject json2 = new JSONObject();

            json2.put("name", name);
            json2.put("desc", desc);

            jsonCard.getJSONArray("elements").put(json2);
        });
    }

    /**
     * 初始化
     * <p>
     * 不需要参数的请填写null
     *
     * @param interactCustomId 自定义交互id
     * @param placeholder      输入框提示
     * @param min              最少选中个数
     * @param max              最多选中个数
     */
    public ListSelectorComponent(String interactCustomId, String placeholder,
                                 int min, int max) {
        jsonCard.put("type", "list-selector");
        jsonCard.put("interactCustomId", interactCustomId);
        jsonCard.put("placeholder", placeholder);
        jsonCard.put("min", min);
        jsonCard.put("max", max);
        jsonCard.put("elements", new JSONArray());
    }

    /**
     * 增加选项
     *
     * @param data1 选项名
     * @param data2 描述
     */
    public void addElement(@NonNull String data1, @NonNull String data2) {
        JSONObject json = new JSONObject();
        json.put("name", data1);
        json.put("desc", data2);
        jsonCard.getJSONArray("elements").put(json);
    }
}
