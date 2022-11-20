package io.github.mcchampions.DodoOpenJava.Card;

import io.github.mcchampions.DodoOpenJava.Card.enums.*;
import io.github.mcchampions.DodoOpenJava.Utils.MapUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.HashMap;

/**
 * ��Ƭ��Ϣ
 * @author qscbm187531
 */
public class Card {
    public JSONObject JsonCard = new JSONObject();

    /**
     * �Ƿ񲻴���
     * @return true/false
     */
    public Boolean isEmpty() {
        return JsonCard.isEmpty();
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
        return JsonCard;
    }

    /**
     * ��ʼ����Ƭ
     * @param card JSON
     * @return true
     */
    public Boolean initCard(JSONObject card) {
        JsonCard = card;
        return true;
    }

    /**
     * ��ʼ����Ƭ
     * @return true/false
     */
    public Boolean initCard() {
        if (JsonCard.isEmpty()) {
            JsonCard = new JSONObject("""
                    {
                      "content": "����һ�ο�Ƭ���������Ϣ�����Ը���Markdown�﷨��@�û���#Ƶ���������﷨���ܣ��ڿ�Ƭ�༭���в���ʵʱԤ����",
                      "card": {
                        "type": "card",
                        "components": [],
                        "theme": "default",
                        "title": "����һ����Ƭ����"
                      }
                    }""");
            return true;
        } else return false;
    }

    /**
     * �༭���
     * @param theme �ָ�
     * @return true
     */
    public Boolean editTheme(Theme theme) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").put("theme", theme.getType());
        return true;
    }

    /**
     * �༭����
     * @param title ����
     * @return �ɹ�
     */
    public Boolean editTitle(String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").put("title", title);
        return true;
    }

    /**
     * �༭�ı�
     * @param content �ı�
     * @return �ɹ�
     */
    public Boolean editContent(String content) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.put("content", content);
        return true;
    }

    /**
     * �Ƴ��ı�
     * @return true/false
     */
    public Boolean removeContent() {
        if (JsonCard.isEmpty()) initCard();
        if (!JsonCard.keySet().contains("content")) return false;
        JsonCard.remove("content");
        return true;
    }

    /**
     * ���ӱ������
     * @param type �ı�����
     * @param title ����
     * @return �ɹ�
     */
    public Boolean addHeaderComponent(TextType type, String title) {
        if (JsonCard.isEmpty()) initCard();
        String Type = type.getType();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"header\",\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + title + "\"}}"));
        return true;
    }

    /**
     * �����������
     * @param section �������
     * @return �ɹ�
     */
    public Boolean addSectionComponent(Section section) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(section.toJSONObject());
        return true;
    }


    /**
     * ���ӱ�ע���
     * @param text �����Ϊ�˷����ȡ��HashMap�洢��ǰһ����������������ͣ��������ֵ�����ΪͼƬ�������ӣ�Ϊ�ı��������ݣ���������ȡ�������Ϊ������ˡ�������
     * @return �ɹ�
     */
    public Boolean addTextRemarkComponent(HashMap<RemarkType,String> text) {
        if (JsonCard.isEmpty()) initCard();

        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"remark\",\"elements\": []}"));
        for(int i = 0; i < MapUtil.ergodicHashMaps(text).size();i++) {
            String Type = ((RemarkType)MapUtil.ergodicHashMaps(text).get(i).get(0)).getType();
            if (Type.equals("image")) {
                JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").toList().size() - 1).getJSONArray("elements").put(new JSONObject("{\"text\": { \"type\": \"" + Type + "\", \"src\": \"" + MapUtil.ergodicHashMaps(text).get(i).get(1) + "\"}}"));
            } else {
                JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").toList().size() - 1).getJSONArray("elements").put(new JSONObject("{\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + MapUtil.ergodicHashMaps(text).get(i).get(1) + "\"}}"));
            }
        }
        return true;
    }

    /**
     * ����ͼƬ���
     * @param url ����
     * @return �ɹ�
     */
    public Boolean addImageComponent(String url) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"image\", \"src\": \"" + url + "\"}"));
        return true;
    }

    /**
     * ���Ӷ�ͼ���
     * @param urls ͼƬ������
     * @return �ɹ�
     */
    public Boolean addImageGroupComponent(List<String> urls) {
        if (JsonCard.isEmpty()) initCard();

        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"image-group\", \"elements\": []}"));
        if (urls.size() > 9) return false;
        for (int i = 0; i < urls.size();i ++) {
            JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").toList().size() - 1).getJSONArray("elements").put(new JSONObject("{\"type\": \"image\", \"src\": \"" + urls.get(1) + "\"}"));
        }
        return true;
    }

    /**
     * ������Ƶ���
     * @param videoUrl ��Ƶ����
     * @param coverUrl ��������
     * @param title ����
     * @return �ɹ�
     */
    public Boolean addVideoComponent(String videoUrl, String coverUrl, String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"video\", \"src\": \"" + videoUrl + "\", \"cover\": \"" + coverUrl + "\",\"title\": \"" + title + "\"}"));
        return true;
    }

    /**
     * ������Ƶ���
     * @param videoUrl ��Ƶ����
     * @param coverUrl ��������
     * @return �ɹ�
     */
    public Boolean addVideoComponent(String videoUrl, String coverUrl) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"video\", \"src\": \"" + videoUrl + "\", \"cover\": \"" + coverUrl + "\"}"));
        return true;
    }

    /**
     * ���ӵ���ʱ���
     * @param style ��ʾ��ʽ
     * @param endTime ����ʱ���
     * @param title ����
     * @return �ɹ�
     */
    public Boolean addCountdownComponent(Style style, Long endTime, String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"countdown\", \"endTime\": " + endTime + ", \"style\": \"" + style.toString() + "\",\"title\": \"" + title + "\"}"));
        return true;
    }
    /**
     * ���ӵ���ʱ���
     * @param style ��ʾ��ʽ
     * @param endTime ����ʱ���
     * @return �ɹ�
     */
    public Boolean addCountdownComponent(Style style, Long endTime) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"countdown\", \"endTime\": " + endTime + ", \"style\": \"" + style.toString() + "\"}"));
        return true;
    }

    /**
     * ��ӷָ���
     * @return �ɹ�
     */
    public Boolean addDividerComponent() {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"divider\"}"));
        return true;
    }

    /**
     * ɾ��һ�����
     * @param count �ڼ����������1��ʼ��
     * @return �ɹ�
     */
    public Boolean removeComponent(int count) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").remove(count + 1);
        return true;
    }

    /**
     * ����һ��������ť���
     * @param buttonGroup ��ť��ɫ
     * @return �ɹ�
     */
    public Boolean addButtonGroup(ButtonGroup buttonGroup) {
        JsonCard.getJSONObject("card").getJSONArray("components").put(buttonGroup.toJsonObject());
        return true;
    }

    /**
     * �����б�ѡ�������
     * <p>
     * ����Ҫ����������дnull
     * @param interactCustomId �Զ��彻��id
     * @param placeholder �������ʾ
     * @param min ����ѡ�и���
     * @param max ���ѡ�и���
     * @param element ���ݣ�Ϊ�˷�����HashMap�洢��ǰ��Ϊѡ����������Ϊѡ������������Ҫ����дnull
     * @return �ɹ�
     */
    public Boolean addListSelector(String interactCustomId, String placeholder, int min, int max, HashMap<String,String> element) {
        if (JsonCard.isEmpty()) initCard();

        JSONObject json1 = new JSONObject();
        json1.put("type", "list-selector");
        json1.put("interactCustomId", interactCustomId);
        json1.put("placeholder", placeholder);
        json1.put("min", min);
        json1.put("max", max);
        JSONArray arr = new JSONArray();
        json1.put("elements", arr);

        for(int i = 0; i < MapUtil.ergodicHashMaps(element).size();i++) {
            JSONObject json2 = new JSONObject();

            json2.put("name", MapUtil.ergodicHashMaps(element).get(i).get(0));
            json2.put("desc", MapUtil.ergodicHashMaps(element).get(i).get(1));

            json1.getJSONArray("elements").put(json2);
        }

        JsonCard.getJSONObject("card").getJSONArray("components").put(json1);
        return true;
    }

    /**
     * ���� ������ģ�� �������
     * @param align ���뷽ʽ��left������룬right���Ҷ���
     * @param section ����
     * @param buttonGroup ��ť
     * @return �ɹ����
     */
    public Boolean addSection(Align align, Section section, ButtonGroup buttonGroup) {
        if (JsonCard.isEmpty()) initCard();

        JSONObject json1 = new JSONObject();
        json1.put("type", "section");
        json1.put("text", section);
        json1.put("align", align.getType());
        json1.put("accessory", buttonGroup.toJsonObject());
        JsonCard.getJSONObject("card").getJSONArray("components").put(json1);
        return true;
    }

    /**
     * ת��ΪString����д��Object��toString������
     * @return �ַ���
     */
    public String toString() {
        return JsonCard.toString();
    }
}
