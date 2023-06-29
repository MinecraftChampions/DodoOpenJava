package io.github.minecraftchampions.dodoopenjava.card;

import io.github.minecraftchampions.dodoopenjava.card.component.Component;
import io.github.minecraftchampions.dodoopenjava.card.enums.Theme;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ƭ��Ϣ
 */
public class Card {
    private JSONObject JsonCard = new JSONObject();

    /**
     * �Ƿ񲻴���
     * @return true/false
     */
    public boolean isEmpty() {
        return JsonCard.isEmpty();
    }

    /**
     * �Ƿ񲻴���
     * @param card Card
     * @return true/false
     */
    public static boolean isEmpty(Card card) {
        return card.toJSONObject().isEmpty();
    }

    /**
     * ת��ΪJSON����
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonCard;
    }

    /**
     * ��ʼ����Ƭ
     * @param card JSON
     */
    public Card(JSONObject card) {
        JsonCard = card;
    }

    /**
     * ��ʼ����Ƭ
     */
    public Card() {
        JsonCard = new JSONObject("""
                {
                  "content": "",
                  "card": {
                    "type": "card",
                    "components": [],
                    "theme": "default",
                    "title": ""
                  }
                }""");
    }

    /**
     * �༭���
     * @param theme �ָ�
     */
    public void editTheme(Theme theme) {
        JsonCard.getJSONObject("card").put("theme", theme.getType());
    }

    /**
     * �༭����
     * @param title ����
     */
    public void editTitle(String title) {
        JsonCard.getJSONObject("card").put("title", title);
    }

    /**
     * �༭�ı�
     * @param content �ı�
     */
    public void editContent(String content) {
        JsonCard.put("content", content);
    }

    /**
     * �Ƴ��ı�
     */
    public void removeContent() {
        if (!JsonCard.keySet().contains("content")) return;
        JsonCard.remove("content");
    }

    /**
     * �������
     * @param component ���
     */
    public void addComponent(Component component) {
        JsonCard.getJSONObject("card").getJSONArray("components").put(component.getJsonCard());
    }

    /**
     * �Ƴ����������ж����ͬ����ȫ���Ƴ�
     * @param component ���
     */
    public void removeComponent(Component component) {
        List<Object> list = JsonCard.getJSONObject("card").getJSONArray("components").toList();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0;i < list.size();i++) {
            Object object = list.get(i);
            if (object instanceof JSONObject jsonObject) {
                if (component.getJsonCard() == jsonObject) {
                    integerList.add(i);
                }
            }
        }
        for (int i = 0;i < integerList.size();i++) {
            removeComponent(integerList.get(i) - i);
        }
    }


    /**
     * ɾ��һ�����
     * @param index index
     */
    public void removeComponent(int index) {
        JsonCard.getJSONObject("card").getJSONArray("components").remove(index);
    }
}
