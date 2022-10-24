package io.github.mcchampions.DodoOpenJava.Card;

import io.github.mcchampions.DodoOpenJava.Card.Enums.ButtonAction;
import io.github.mcchampions.DodoOpenJava.Card.Enums.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

/**
 * 按钮组这个对象
 * @author qscbm187531
 */
public class ButtonGroup {
    JSONObject jsonButton = new JSONObject();

    /**
     * 是否不存在
     * @return true/false
     */
    public Boolean isEmpty() {
        return jsonButton.isEmpty();
    }

    /**
     * 转换为JSON对象
     * @return true
     */
    public JSONObject toJsonObject() {
        return jsonButton;
    }

    /**
     * 增加交互按钮组件
     * @param buttonColor 按钮颜色
     * @param buttonName 按钮名称
     * @param interactCustomId 自定义按钮ID
     * @param action 按钮点击动作类型
     * @param object 按钮点击动作的值，不是表单就是String类型，是表单就传入Form
     * @return true/false
     */
    public Boolean addButton(Color buttonColor, String buttonName, String interactCustomId, ButtonAction action, Object object) {
        Form form = new Form();
        String value = "";
        Object o = null;
        boolean isForm = false;

        if (!Objects.equals(action.getType(), "form")) {
            if (object instanceof String) {
                value = (String) object;
            } else {
                return false;
            }
        } else {
            if (object instanceof Form) {
                form = (Form) object;
                isForm = true;
            } else {
                return false;
            }
        }

        JSONObject json1 = new JSONObject();

        json1.put("type", "button");
        json1.put("interactCustomId", interactCustomId);
        json1.put("color", buttonColor.getType());
        json1.put("name", buttonName);

        JSONObject json2 = new JSONObject();

        json2.put("value", value);
        json2.put("action", action.getType());

        json1.put("click", json2);

        if (isForm) {
            json1.put("form", form.JsonForm);
        }

        jsonButton.getJSONArray("elements").put(json1);
        return true;
    }

    /**
     * 增加交互按钮组件
     * @param ButtonColor 按钮颜色
     * @param ButtonName 按钮名称
     * @param action 按钮点击动作类型
     * @param object 按钮点击动作的值，不是表单就是String类型，是表单就传入Form
     * @return true/false
     */
    public Boolean addButton(Color ButtonColor, String ButtonName, ButtonAction action,Object object) {
        Form form = new Form();
        String value = "";
        boolean isForm = false;

        if (!Objects.equals(action.getType(), "form")) {
            if (object instanceof String) {
                value = (String) object;
            } else {
                return false;
            }
        } else {
            if (object instanceof Form) {
                form = (Form) object;
                isForm = true;
            } else {
                return false;
            }
        }

        JSONObject json1 = new JSONObject();

        json1.put("type", "button");
        json1.put("color", ButtonColor.getType());
        json1.put("name", ButtonName);

        JSONObject json2 = new JSONObject();

        json2.put("value", value);
        json2.put("action", action.getType());

        json1.put("click", json2);

        if (isForm) {
            json1.put("form", form.JsonForm);
        }

        jsonButton.getJSONArray("elements").put(json1);
        return true;
    }

    /**
     * 初始化按钮组
     */
    public void initButtonGroup() {
        jsonButton.put("type","button-group");
        jsonButton.put("elements",new JSONArray());
    }

    /**
     * 转换为String（重写了Object的toString方法）
     * @return 字符串
     */
    public String toString() {
        return jsonButton.toString();
    }
}
