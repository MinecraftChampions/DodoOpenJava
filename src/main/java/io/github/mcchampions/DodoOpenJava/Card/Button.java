package io.github.mcchampions.DodoOpenJava.Card;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Card.Enums.ButtonAction;
import io.github.mcchampions.DodoOpenJava.Card.Enums.Color;
import io.github.mcchampions.DodoOpenJava.Utils.StrUtil;

import java.util.Objects;

/**
 * 按钮这个对象
 */
public class Button {
    public JSONObject JsonButton;

    /**
     * 是否不存在
     * @return true/false
     */
    public Boolean isEmpty() {
        return JsonButton.isEmpty();
    }

    /**
     * 转换为JSON对象
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonButton;
    }
    /**
     * 初始化交互按钮组件
     * @param ButtonColor 按钮颜色
     * @param ButtonName 按钮名称
     * @param interactCustomId 自定义按钮ID
     * @param action 按钮点击动作类型
     * @param object 按钮点击动作的值，不是表单就是String类型，是表单就传入Form
     * @return true/false
     */
    public Boolean addButton(Color ButtonColor, String ButtonName, String interactCustomId, ButtonAction action, Object object) {
        Form form = new Form();
        String value = "";
        boolean isForm = false;

        if (!Objects.equals(action.toString(), "form")) {
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
        json1.put("color", StrUtil.toLowerCase(ButtonColor.toString()));
        json1.put("name", ButtonName);

        JSONObject json2 = new JSONObject();

        json2.put("value", value);
        json2.put("action", action.toString());

        json1.put("click", json2);

        if (isForm) {
            json1.put("form", form);
        }

        JsonButton = json1;
        return true;
    }

    /**
     * 初始化交互按钮组件
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

        if (!Objects.equals(action.toString(), "form")) {
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
        json1.put("color", StrUtil.toLowerCase(ButtonColor.toString()));
        json1.put("name", ButtonName);

        JSONObject json2 = new JSONObject();

        json2.put("value", value);
        json2.put("action", action.toString());

        json1.put("click", json2);

        if (isForm) {
            json1.put("form", form);
        }

        JsonButton = json1;
        return true;
    }
}
