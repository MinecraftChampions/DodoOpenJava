package io.github.mcchampions.DodoOpenJava.Card;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Card.enums.Rows;

/**
 * 回传表单
 * @author qscbm187531
 */
public class Form {
    public JSONObject JsonForm = new JSONObject();

    /**
     * 是否不存在
     * @return true/false
     */
    public Boolean isEmpty() {
        return JsonForm.isEmpty();
    }

    /**
     * 是否不存在
     * @param card Card
     * @return true/false
     */
    public Boolean isEmpty(Card card) {
        return card.toJSONObject().isEmpty();
    }

    /**
     * 转换为JSON对象
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonForm;
    }

    /**
     * 初始化回传表单
     * @param Form JSON
     * @return true
     */
    public Boolean initForm(JSONObject Form) {
        JsonForm = Form;
        return true;
    }

    /**
     * 初始化回传表单
     * @return true/false
     */
    public Boolean initForm() {
        if (JsonForm.isEmpty()) {
            JsonForm = new JSONObject("""
                    {
                        "title": "表单标题",
                        "elements": []
                    }""");
            return true;
        } else return false;
    }

    /**
     * 编辑标题
     * @param title 标题
     * @return 成功
     */
    public Boolean editContent(String title) {
        if (JsonForm.isEmpty()) initForm();
        JsonForm.put("title", title);
        return true;
    }

    /**
     * 删除一个数据
     * @param count 第几个数据（从1开始）
     * @return 成功
     */
    public Boolean removeElement(int count) {
        if (JsonForm.isEmpty()) initForm();
        JsonForm.getJSONArray("elements").remove(count + 1);
        return true;
    }

    /**
     * 增加一个数据
     * @param key 选项自定义ID
     * @param title 名称
     * @param rows 输入框高度，填 one 表示单行，最多4行
     * @param minChar 最小字符数，介于0~4000
     * @param maxChar 最大字符数，介于1~4000，且最大字符数不得小于最小字符数
     * @return true/false
     */
    public Boolean addElement(String key, String title, Rows rows, int minChar, int maxChar) {
        JSONObject json = new JSONObject();
        if (minChar < 0) return false;
        if (minChar > 4000) return false;
        if (maxChar > 4000) return false;
        if (minChar > maxChar) return false;
        if (maxChar < 1) return false;

        int Row = rows.getRow();

        json.put("type", "input");
        json.put("key", key);
        json.put("title", title);
        json.put("rows", Row);
        json.put("minChar", minChar);
        json.put("maxChar", maxChar);
        JsonForm.getJSONArray("elements").put(json);
        return true;
    }

    /**
     * 增加一个数据
     * @param key 选项自定义ID
     * @param placeholder 输入框提示
     * @param title 名称
     * @param rows 输入框高度，填 one 表示单行，最多4行
     * @param minChar 最小字符数，介于0~4000
     * @param maxChar 最大字符数，介于1~4000，且最大字符数不得小于最小字符数
     * @return true/false
     */
    public Boolean addElement(String key, String placeholder, String title, Rows rows, int minChar, int maxChar) {
        JSONObject json = new JSONObject();
        if (minChar < 0) return false;
        if (minChar > 4000) return false;
        if (maxChar > 4000) return false;
        if (minChar > maxChar) return false;
        if (maxChar < 1) return false;

        int Row = rows.getRow();

        json.put("type", "input");
        json.put("key", key);
        json.put("title", title);
        json.put("rows", Row);
        json.put("minChar", minChar);
        json.put("maxChar", maxChar);
        json.put("placeholder", placeholder);
        JsonForm.getJSONArray("elements").put(json);
        return true;
    }

    /**
     * 转换为String（重写了Object的toString方法）
     * @return 字符串
     */
    public String toString() {
        return JsonForm.toString();
    }
}
