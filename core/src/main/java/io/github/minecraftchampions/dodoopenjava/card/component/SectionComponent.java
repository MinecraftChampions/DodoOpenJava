package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.Cols;
import io.github.minecraftchampions.dodoopenjava.card.enums.SectionType;
import io.github.minecraftchampions.dodoopenjava.card.enums.TextType;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * �������
 */
public class SectionComponent extends Component{

    public boolean isParagraph;

    /**
     * ��ʼ��Section
     */
    public SectionComponent(SectionType type) {
        if (jsonCard.isEmpty()) {
            if (!Objects.equals(type.getType(), "Paragraph")) {
                String Type = type.getType();
                jsonCard = new JSONObject("{\n" +
                        "                        \"type\": \"sectionComponent\",\n" +
                        "                        \"text\": {\n" +
                        "                                \"type\": \"" + Type + "\",\n" +
                        "                                \"content\": \"һ�����ı��ֺŵ��ı����ݣ�֧��Markdown�����֧���ַ���2000��\"\n" +
                        "                        }\n" +
                        "                    }");
                isParagraph = false;
            } else {
                isParagraph = true;
                jsonCard = new JSONObject("""
                        {
                                                    "type": "sectionComponent",
                                                     "text": {
                                                         "type": "paragraph",
                                                         "cols": 0,
                                                         "fields": []
                                                     }
                                                }""");
            }
        } 
    }

    /**
     * �༭�����ı�
     * @param stringList string�б�
     */
    public void editParagraphContent(List<String> stringList) {
        int count = stringList.size();
        Cols cols = Cols.six;
        switch (count) {
            case 2 -> cols = Cols.two;
            case 3 -> cols = Cols.three;
            case 4 -> cols = Cols.four;
            case 5 -> cols = Cols.five;
        }
        editParagraphContentCols(cols);
    }

    /**
     * �༭�ı����Ƕ�����
     * @param content �ı�
     */
    public void editContent(String content) {
        jsonCard.getJSONObject("text").put("content", content);
    }

    /**
     * �༭�ı����
     * @param type ���
     */
    public void editContentType(TextType type) {
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        jsonCard.getJSONObject("text").put("type", Type);
    }

    /**
     * �༭�ı���������
     * @param content �ı�
     * @param count �ڼ���
     */
    public void editParagraphContent(String content,int count) {
        jsonCard.getJSONObject("text").getJSONArray("fields").getJSONObject(count).put("content", content);
    }

    /**
     * �༭�ı���𣨶�����
     * @param type ���
     * @param count index
     */
    public void editParagraphContentType(TextType type, int count) {
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        jsonCard.getJSONObject("text").getJSONArray("fields").getJSONObject(count).put("type", Type);
    }

    /**
     * �༭�����ı�����
     * @param col ����
     */
    public void editParagraphContentCols(Cols col) {
        int Col = col.getCol();
        jsonCard.getJSONObject("text").put("cols", Col);
    }

    /**
     * �Ƴ������ı���һ���ı�
     * @param count index
     */
    public void editParagraphContentType(int count) {
        jsonCard.getJSONObject("text").getJSONArray("fields").remove(count);
    }
}
