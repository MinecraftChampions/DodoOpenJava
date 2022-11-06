package io.github.mcchampions.DodoOpenJava.Card;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Card.enums.Rows;

/**
 * �ش���
 * @author qscbm187531
 */
public class Form {
    public JSONObject JsonForm = new JSONObject();

    /**
     * �Ƿ񲻴���
     * @return true/false
     */
    public Boolean isEmpty() {
        return JsonForm.isEmpty();
    }

    /**
     * �Ƿ񲻴���
     * @param card Card
     * @return true/false
     */
    public Boolean isEmpty(Card card) {
        return card.toJSONObject().isEmpty();
    }

    /**
     * ת��ΪJSON����
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonForm;
    }

    /**
     * ��ʼ���ش���
     * @param Form JSON
     * @return true
     */
    public Boolean initForm(JSONObject Form) {
        JsonForm = Form;
        return true;
    }

    /**
     * ��ʼ���ش���
     * @return true/false
     */
    public Boolean initForm() {
        if (JsonForm.isEmpty()) {
            JsonForm = new JSONObject("""
                    {
                        "title": "������",
                        "elements": []
                    }""");
            return true;
        } else return false;
    }

    /**
     * �༭����
     * @param title ����
     * @return �ɹ�
     */
    public Boolean editContent(String title) {
        if (JsonForm.isEmpty()) initForm();
        JsonForm.put("title", title);
        return true;
    }

    /**
     * ɾ��һ������
     * @param count �ڼ������ݣ���1��ʼ��
     * @return �ɹ�
     */
    public Boolean removeElement(int count) {
        if (JsonForm.isEmpty()) initForm();
        JsonForm.getJSONArray("elements").remove(count + 1);
        return true;
    }

    /**
     * ����һ������
     * @param key ѡ���Զ���ID
     * @param title ����
     * @param rows �����߶ȣ��� one ��ʾ���У����4��
     * @param minChar ��С�ַ���������0~4000
     * @param maxChar ����ַ���������1~4000��������ַ�������С����С�ַ���
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
     * ����һ������
     * @param key ѡ���Զ���ID
     * @param placeholder �������ʾ
     * @param title ����
     * @param rows �����߶ȣ��� one ��ʾ���У����4��
     * @param minChar ��С�ַ���������0~4000
     * @param maxChar ����ַ���������1~4000��������ַ�������С����С�ַ���
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
     * ת��ΪString����д��Object��toString������
     * @return �ַ���
     */
    public String toString() {
        return JsonForm.toString();
    }
}
