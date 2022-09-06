package io.github.mcchampions.DodoOpenJava.Card;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Card.Enums.Cols;
import io.github.mcchampions.DodoOpenJava.Card.Enums.SectionType;
import io.github.mcchampions.DodoOpenJava.Card.Enums.TextType;

import java.util.Objects;

/**
 * 关于文字这一类
 */
public class Section {
    public JSONObject JsonText;

    public Boolean isParagraph;

    /**
     * 是否不存在
     * @return true/false
     */
    public Boolean isEmpty() {
        return JsonText.isEmpty();
    }

    /**
     * 是否不存在
     * @param text Text
     * @return true/false
     */
    public Boolean isEmpty(Section text) {
        return text.toJSONObject().isEmpty();
    }

    /**
     * 转换为JSON对象
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonText;
    }

    /**
     * 初始化Section
     * @return true/false
     */
    public Boolean initText(SectionType type) {
        if (JsonText.isEmpty()) {
            if (!Objects.equals(type.toString(), "Paragraph")) {
                String Type = switch (type.toString()) {
                    case "Markdown" -> "dodo-md";
                    default -> "plain-text";
                };
                JsonText = new JSONObject("{\n" +
                        "                        \"type\": \"section\",\n" +
                        "                        \"text\": {\n" +
                        "                                \"type\": \"" + Type + "\",\n" +
                        "                                \"content\": \"一长段文本字号的文本内容，支持Markdown，最大支持字符数2000。\"\n" +
                        "                        }\n" +
                        "                    }");
                isParagraph = false;
            } else {
                isParagraph = true;
                JsonText = new JSONObject("{\n" +
                        "                            \"type\": \"section\",\n" +
                        "                             \"text\": {\n" +
                        "                                 \"type\": \"paragraph\",\n" +
                        "                                 \"cols\": 6,\n" +
                        "                                 \"fields\": [{\n" +
                        "                                         \"type\": \"dodo-md\",\n" +
                        "                                         \"content\": \"第一栏\\n内容\"\n" +
                        "                                     }, {\n" +
                        "                                         \"type\": \"dodo-md\",\n" +
                        "                                         \"content\": \"第二栏\\n内容\"\n" +
                        "                                     }, {\n" +
                        "                                         \"type\": \"dodo-md\",\n" +
                        "                                         \"content\": \"第三栏\\n内容\"\n" +
                        "                                     }, {\n" +
                        "                                         \"type\": \"dodo-md\",\n" +
                        "                                         \"content\": \"第四栏\\n内容\"\n" +
                        "                                     }, {\n" +
                        "                                         \"type\": \"dodo-md\",\n" +
                        "                                         \"content\": \"第五栏\\n内容\"\n" +
                        "                                     }, {\n" +
                        "                                         \"type\": \"dodo-md\",\n" +
                        "                                         \"content\": \"第六栏\\n内容\"\n" +
                        "                                     }\n" +
                        "                                 ]\n" +
                        "                             }\n" +
                        "                        }");
            }
            return true;
        } else return false;
    }

    /**
     * 编辑文本（非多栏）
     * @param content 文本
     * @return 成功
     */
    public Boolean editContent(String content) {
        if (JsonText.isEmpty()) initText(SectionType.Markdown);
        JsonText.getJSONObject("text").put("content", content);
        return true;
    }

    /**
     * 编辑文本类别
     * @param type 类别
     * @return 成功
     */
    public Boolean editContentType(TextType type) {
        if (JsonText.isEmpty()) initText(SectionType.Markdown);
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        JsonText.getJSONObject("text").put("type", Type);
        return true;
    }

    /**
     * 编辑文本（多栏）
     * @param content 文本
     * @param count 第几个（从1开始）
     * @return 成功
     */
    public Boolean editParagraphContent(String content,int count) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        JsonText.getJSONObject("text").getJSONArray("fields").getJSONObject(count - 1).put("content", content);
        return true;
    }

    /**
     * 编辑文本类别（多栏）
     * @param type 类别
     * @param count 第几个（从1开始）
     * @return 成功
     */
    public Boolean editParagraphContentType(TextType type, int count) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        JsonText.getJSONObject("text").getJSONArray("fields").getJSONObject(count - 1).put("type", Type);
        return true;
    }

    /**
     * 编辑多栏文本栏数
     * @param col 栏数
     * @return 成功
     */
    public Boolean editParagraphContentCols(Cols col) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        int Col = switch (col.toString()) {
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            default -> 6;
        };
        JsonText.getJSONObject("text").put("cols", Col);
        return true;
    }

    /**
     * 移除多栏文本的一端文本
     * @param count 第几个（从1开始）
     * @return 成功
     */
    public Boolean editParagraphContentType(int count) {
        if (JsonText.isEmpty()) initText(SectionType.Paragraph);
        JsonText.getJSONObject("text").getJSONArray("fields").remove(count - 1);
        return true;
    }
}
