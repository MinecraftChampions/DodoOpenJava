package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.Cols;
import io.github.minecraftchampions.dodoopenjava.card.enums.SectionType;
import io.github.minecraftchampions.dodoopenjava.card.enums.TextType;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * 文字组件
 */
public class SectionComponent extends Component {

    public boolean isParagraph;

    /**
     * 初始化Section
     */
    public SectionComponent(SectionType type) {
        if (jsonCard.isEmpty()) {
            if (!Objects.equals(type.getType(), "Paragraph")) {
                String Type = type.getType();
                jsonCard = new JSONObject("{\n" +
                        "                        \"type\": \"section\",\n" +
                        "                        \"text\": {\n" +
                        "                                \"type\": \"" + Type + "\",\n" +
                        "                                \"content\": \"一长段文本字号的文本内容，支持Markdown，最大支持字符数2000。\"\n" +
                        "                        }\n" +
                        "                    }");
                isParagraph = false;
            } else {
                isParagraph = true;
                jsonCard = new JSONObject("""
                        {
                                                    "type": "section",
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
     * 编辑多栏文本
     *
     * @param stringList string列表
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
     * 编辑文本（非多栏）
     *
     * @param content 文本
     */
    public void editContent(String content) {
        jsonCard.getJSONObject("text").put("content", content);
    }

    /**
     * 编辑文本类别
     *
     * @param type 类别
     */
    public void editContentType(TextType type) {
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md";
        else Type = "plain-text";
        jsonCard.getJSONObject("text").put("type", Type);
    }

    /**
     * 编辑文本（多栏）
     *
     * @param content 文本
     * @param count   第几个
     */
    public void editParagraphContent(String content, int count) {
        jsonCard.getJSONObject("text").getJSONArray("fields").getJSONObject(count).put("content", content);
    }

    /**
     * 编辑文本类别（多栏）
     *
     * @param type  类别
     * @param count index
     */
    public void editParagraphContentType(TextType type, int count) {
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md";
        else Type = "plain-text";
        jsonCard.getJSONObject("text").getJSONArray("fields").getJSONObject(count).put("type", Type);
    }

    /**
     * 编辑多栏文本栏数
     *
     * @param col 栏数
     */
    public void editParagraphContentCols(Cols col) {
        int Col = col.getCol();
        jsonCard.getJSONObject("text").put("cols", Col);
    }

    /**
     * 移除多栏文本的一端文本
     *
     * @param count index
     */
    public void editParagraphContentType(int count) {
        jsonCard.getJSONObject("text").getJSONArray("fields").remove(count);
    }
}
