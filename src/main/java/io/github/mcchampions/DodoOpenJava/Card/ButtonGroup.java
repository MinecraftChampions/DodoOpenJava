package io.github.mcchampions.DodoOpenJava.Card;

import io.github.mcchampions.DodoOpenJava.Card.enums.ButtonAction;
import io.github.mcchampions.DodoOpenJava.Card.enums.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

/**
 * ��ť���������
 * @author qscbm187531
 */
public class ButtonGroup {
    JSONObject jsonButton = new JSONObject();

    /**
     * �Ƿ񲻴���
     * @return true/false
     */
    public Boolean isEmpty() {
        return jsonButton.isEmpty();
    }

    /**
     * ת��ΪJSON����
     * @return true
     */
    public JSONObject toJsonObject() {
        return jsonButton;
    }

    /**
     * ���ӽ�����ť���
     * @param buttonColor ��ť��ɫ
     * @param buttonName ��ť����
     * @param interactCustomId �Զ��尴ťID
     * @param action ��ť�����������
     * @param object ��ť���������ֵ�����Ǳ�����String���ͣ��Ǳ��ʹ���Form
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
     * ���ӽ�����ť���
     * @param ButtonColor ��ť��ɫ
     * @param ButtonName ��ť����
     * @param action ��ť�����������
     * @param object ��ť���������ֵ�����Ǳ�����String���ͣ��Ǳ��ʹ���Form
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
     * ��ʼ����ť��
     */
    public void initButtonGroup() {
        jsonButton.put("type","button-group");
        jsonButton.put("elements",new JSONArray());
    }

    /**
     * ת��ΪString����д��Object��toString������
     * @return �ַ���
     */
    public String toString() {
        return jsonButton.toString();
    }
}
