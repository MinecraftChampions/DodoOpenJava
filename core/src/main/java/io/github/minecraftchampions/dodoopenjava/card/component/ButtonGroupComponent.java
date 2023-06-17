package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.ButtonAction;
import io.github.minecraftchampions.dodoopenjava.card.enums.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

/**
 * ��ť�������
 */
public class ButtonGroupComponent extends Component {
    /**
     * ��ʼ��
     */
    public ButtonGroupComponent() {
        jsonCard.put("type","button-group");
        jsonCard.put("elements",new JSONArray());
    }

    /**
     * ���ӽ�����ť���
     * @param buttonColor ��ť��ɫ
     * @param buttonName ��ť����
     * @param interactCustomId �Զ��尴ťID
     * @param action ��ť�����������
     * @param object ��ť���������ֵ�����Ǳ�����String���ͣ��Ǳ��ʹ���Form
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
     * ���ӽ�����ť���
     * @param ButtonColor ��ť��ɫ
     * @param ButtonName ��ť����
     * @param action ��ť�����������
     * @param object ��ť���������ֵ�����Ǳ�����String���ͣ��Ǳ��ʹ���Form
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
