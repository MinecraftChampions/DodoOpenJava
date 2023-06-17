package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.Rows;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * �ش���
 * @author qscbm187531
 */
public class FormComponent extends Component{
    /**
     * ��ʼ���ش���
     */
    public void initForm() {
        jsonCard.put("title","");
        jsonCard.put("elements",new JSONArray());
    }

    /**
     * �༭����
     * @param title ����
     */
    public void editContent(String title) {
        jsonCard.put("title", title);
    }

    /**
     * ɾ��һ������
     * @param count index
     */
    public void removeElement(int count) {
        jsonCard.getJSONArray("elements").remove(count);
    }

    /**
     * ����һ������
     * @param key ѡ���Զ���ID
     * @param title ����
     * @param rows �����߶ȣ��� one ��ʾ���У����4��
     * @param minChar ��С�ַ���������0~4000
     * @param maxChar ����ַ���������1~4000��������ַ�������С����С�ַ���
     */
    public void addElement(String key, String title, Rows rows, int minChar, int maxChar) {
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
     * ����һ������
     * @param key ѡ���Զ���ID
     * @param placeholder �������ʾ
     * @param title ����
     * @param rows �����߶ȣ��� one ��ʾ���У����4��
     * @param minChar ��С�ַ���������0~4000
     * @param maxChar ����ַ���������1~4000��������ַ�������С����С�ַ���
     */
    public void addElement(String key, String placeholder, String title, Rows rows, int minChar, int maxChar) {
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
