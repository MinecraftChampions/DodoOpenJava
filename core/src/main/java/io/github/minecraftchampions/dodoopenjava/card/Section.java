package io.github.minecraftchampions.dodoopenjava.card;

import io.github.minecraftchampions.dodoopenjava.card.enums.Cols;
import io.github.minecraftchampions.dodoopenjava.card.enums.SectionType;
import io.github.minecraftchampions.dodoopenjava.card.enums.TextType;
import org.json.JSONObject;

import java.util.Objects;

/**
 * ����������һ��
 * @author qscbm187531
 */
public class Section {
    public JSONObject JsonText = new JSONObject();

    public Boolean isParagraph;

    /**
     * �Ƿ񲻴���
     * @return true/false
     */
    public Boolean isEmpty() {
        return JsonText.isEmpty();
    }

    /**
     * �Ƿ񲻴���
     * @param text Text
     * @return true/false
     */
    public Boolean isEmpty(Section text) {
        return text.toJSONObject().isEmpty();
    }

    /**
     * ת��ΪJSON����
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonText;
    }

    /**
     * ��ʼ��Section
     * @return true/false
     */
    public Boolean initText(SectionType type) {
        if (JsonText.isEmpty()) {
            if (!Objects.equals(type.getType(), "Paragraph")) {
                String Type = type.getType();
                JsonText = new JSONObject("{\n" +
                        "                        \"type\": \"section\",\n" +
                        "                        \"text\": {\n" +
                        "                                \"type\": \"" + Type + "\",\n" +
                        "                                \"content\": \"һ�����ı��ֺŵ��ı����ݣ�֧��Markdown�����֧���ַ���2000��\"\n" +
                        "                        }\n" +
                        "                    }");
                isParagraph = false;
            } else {
                isParagraph = true;
                JsonText = new JSONObject("""
                        {
                                                    "type": "section",
                                                     "text": {
                                                         "type": "paragraph",
                                                         "cols": 6,
                                                         "fields": [{
                                                                 "type": "dodo-md",
                                                                 "content": "��һ��\\n����"
                                                             }, {
                                                                 "type": "dodo-md",
                                                                 "content": "�ڶ���\\n����"
                                                             }, {
                                                                 "type": "dodo-md",
                                                                 "content": "������\\n����"
                                                             }, {
                                                                 "type": "dodo-md",
                                                                 "content": "������\\n����"
                                                             }, {
                                                                 "type": "dodo-md",
                                                                 "content": "������\\n����"
                                                             }, {
                                                                 "type": "dodo-md",
                                                                 "content": "������\\n����"
                                                             }
                                                         ]
                                                     }
                                                }""");
            }
            return true;
        } else return false;
    }

    /**
     * �༭�ı����Ƕ�����
     * @param content �ı�
     * @return �ɹ�
     */
    public Boolean editContent(String content) {
        if (JsonText.isEmpty()) initText(SectionType.Markdown);
        JsonText.getJSONObject("text").put("content", content);
        return true;
    }

    /**
     * �༭�ı����
     * @param type ���
     * @return �ɹ�
     */
    public Boolean editContentType(TextType type) {
        if (JsonText.isEmpty()) initText(SectionType.Markdown);
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        JsonText.getJSONObject("text").put("type", Type);
        return true;
    }

    /**
     * �༭�ı���������
     * @param content �ı�
     * @param count �ڼ�������1��ʼ��
     * @return �ɹ�
     */
    public Boolean editParagraphContent(String content,int count) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        JsonText.getJSONObject("text").getJSONArray("fields").getJSONObject(count - 1).put("content", content);
        return true;
    }

    /**
     * �༭�ı���𣨶�����
     * @param type ���
     * @param count �ڼ�������1��ʼ��
     * @return �ɹ�
     */
    public Boolean editParagraphContentType(TextType type, int count) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        JsonText.getJSONObject("text").getJSONArray("fields").getJSONObject(count - 1).put("type", Type);
        return true;
    }

    /**
     * �༭�����ı�����
     * @param col ����
     * @return �ɹ�
     */
    public Boolean editParagraphContentCols(Cols col) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        int Col = col.getCol();
        JsonText.getJSONObject("text").put("cols", Col);
        return true;
    }

    /**
     * �Ƴ������ı���һ���ı�
     * @param count �ڼ�������1��ʼ��
     * @return �ɹ�
     */
    public Boolean editParagraphContentType(int count) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        JsonText.getJSONObject("text").getJSONArray("fields").remove(count - 1);
        return true;
    }

    /**
     * ת��ΪString����д��Object��toString������
     * @return �ַ���
     */
    public String toString() {
        return JsonText.toString();
    }
}
