package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.ButtonAction;
import io.github.minecraftchampions.dodoopenjava.card.enums.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

/**
 * 按钮交互组件
 */
public class ButtonGroupComponent extends Component {
    /**
     * 初始化
     */
    public ButtonGroupComponent() {
        jsonCard.put("type","button-group");
        jsonCard.put("elements",new JSONArray());
    }

    /**
     * 增加交互按钮组件
     * @param buttonColor 按钮颜色
     * @param buttonName 按钮名称
     * @param interactCustomId 自定义按钮ID
     * @param action 按钮点击动作类型
     * @param object 按钮点击动作的值，不是表单就是String类型，是表单就传入Form
     */
    public void addButton(Color buttonColor, String buttonName, String interactCustomId, ButtonAction action, Object object) {
        FormComponent formComponent = new FormComponent();
        String value = "";
        boolean isForm = false;

        if (!Objects.equals(action.getType(), "form")) {
            if (object instanceof String) {
                value = (String) object;
            } else {
                return;
            }
        } else {
            if (object instanceof FormComponent) {
                formComponent = (FormComponent) object;
                isForm = true;
            } else {
                return;
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
            json1.put("form", formComponent.getJsonCard());
        }

        jsonCard.getJSONArray("elements").put(json1);
    }

    /**
     * 增加交互按钮组件
     * @param ButtonColor 按钮颜色
     * @param ButtonName 按钮名称
     * @param action 按钮点击动作类型
     * @param object 按钮点击动作的值，不是表单就是String类型，是表单就传入Form
     */
    public void addButton(Color ButtonColor, String ButtonName, ButtonAction action, Object object) {
        FormComponent formComponent = new FormComponent();
        String value = "";
        boolean isForm = false;

        if (!Objects.equals(action.getType(), "form")) {
            if (object instanceof String) {
                value = (String) object;
            } else {
                return;
            }
        } else {
            if (object instanceof FormComponent) {
                formComponent = (FormComponent) object;
                isForm = true;
            } else {
                return;
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
            json1.put("form", formComponent.getJsonCard());
        }

        jsonCard.getJSONArray("elements").put(json1);
    }

}
