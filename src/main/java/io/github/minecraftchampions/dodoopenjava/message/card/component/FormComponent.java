package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.enums.Rows;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 回传表单
 *
 * @author qscbm187531
 */
public class FormComponent extends CardComponent {
    /**
     * 初始化回传表单
     */
    public void initForm() {
        jsonCard.put("title", "");
        jsonCard.put("elements", new JSONArray());
    }

    /**
     * 编辑标题
     *
     * @param title 标题
     */
    public void editContent(@NonNull String title) {
        jsonCard.put("title", title);
    }

    /**
     * 删除一个数据
     *
     * @param count index
     */
    public void removeElement(int count) {
        jsonCard.getJSONArray("elements").remove(count);
    }

    /**
     * 增加一个数据
     *
     * @param key     选项自定义ID
     * @param title   名称
     * @param rows    输入框高度，填 one 表示单行，最多4行
     * @param minChar 最小字符数，介于0~4000
     * @param maxChar 最大字符数，介于1~4000，且最大字符数不得小于最小字符数
     */
    public void addElement(@NonNull String key, @NonNull String title, @NonNull Rows rows,
                           int minChar, int maxChar) {
        JSONObject json = new JSONObject();
        if (minChar < 0) return;
        if (minChar > 4000) return;
        if (maxChar > 4000) return;
        if (minChar > maxChar) return;
        if (maxChar < 1) return;

        int Row = rows.getRow();

        json.put("type", "input");
        json.put("key", key);
        json.put("title", title);
        json.put("rows", Row);
        json.put("minChar", minChar);
        json.put("maxChar", maxChar);
        jsonCard.getJSONArray("elements").put(json);
    }

    /**
     * 增加一个数据
     *
     * @param key         选项自定义ID
     * @param placeholder 输入框提示
     * @param title       名称
     * @param rows        输入框高度，填 one 表示单行，最多4行
     * @param minChar     最小字符数，介于0~4000
     * @param maxChar     最大字符数，介于1~4000，且最大字符数不得小于最小字符数
     */
    public void addElement(@NonNull String key, @NonNull String placeholder,
                           @NonNull String title, @NonNull Rows rows, int minChar,
                           int maxChar) {
        JSONObject json = new JSONObject();
        if (minChar < 0) return;
        if (minChar > 4000) return;
        if (maxChar > 4000) return;
        if (minChar > maxChar) return;
        if (maxChar < 1) return;

        int Row = rows.getRow();

        json.put("type", "input");
        json.put("key", key);
        json.put("title", title);
        json.put("rows", Row);
        json.put("minChar", minChar);
        json.put("maxChar", maxChar);
        json.put("placeholder", placeholder);
        jsonCard.getJSONArray("elements").put(json);
    }
}
