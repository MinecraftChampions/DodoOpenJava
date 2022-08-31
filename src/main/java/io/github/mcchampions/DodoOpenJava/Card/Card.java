package io.github.mcchampions.DodoOpenJava.Card;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Card.Enums.*;
import io.github.mcchampions.DodoOpenJava.Utils.MapUtil;
import io.github.mcchampions.DodoOpenJava.Utils.StrUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 卡片消息
 */
public class Card {
    public static JSONObject JsonCard;

    /**
     * 是否不存在
     * @return true/false
     */
    public static Boolean isEmpty() {
        return JsonCard.isEmpty();
    }

    /**
     * 是否不存在
     * @param card Card
     * @return true/false
     */
    public static Boolean isEmpty(Card card) {
        return card.toJSONObject().isEmpty();
    }

    /**
     * 转换为JSON对象
     * @return true
     */
    public static JSONObject toJSONObject() {
        return JsonCard;
    }

    /**
     * 初始化卡片
     * @param card JSON
     * @return true
     */
    public static Boolean initCard(JSONObject card) {
        JsonCard = card;
        return true;
    }

    /**
     * 初始化卡片
     * @return true/false
     */
    public static Boolean initCard() {
        if (JsonCard.isEmpty()) {
            JsonCard = JSONObject.parseObject("""
                    {
                      "content": "这是一段卡片外的文字消息，可以附带Markdown语法、@用户、#频道等菱形语法功能，在卡片编辑器中不会实时预览。",
                      "card": {
                        "type": "card",
                        "components": [],
                        "theme": "default",
                        "title": "这是一个卡片标题"
                      }
                    }""");
            return true;
        } else return false;
    }

    /**
     * 编辑风格
     * @param theme 分割
     * @return true
     */
    public static Boolean editTheme(Theme theme) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").replace("theme", StrUtil.toLowerCase(theme.toString()));
        return true;
    }

    /**
     * 编辑标题
     * @param title 标题
     * @return 成功
     */
    public static Boolean editTitle(String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").replace("title", title);
        return true;
    }

    /**
     * 编辑文本
     * @param content 文本
     * @return 成功
     */
    public static Boolean editContent(String content) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.replace("content", content);
        return true;
    }

    /**
     * 移除文本
     * @return true/false
     */
    public static Boolean removeContent() {
        if (JsonCard.isEmpty()) initCard();
        if (!JsonCard.containsKey("content")) return false;
        JsonCard.remove("content");
        return true;
    }

    /**
     * 增加标题组件
     * @param type 文本类型
     * @param title 标题
     * @return 成功
     */
    public static Boolean addHeaderComponent(TextType type, String title) {
        if (JsonCard.isEmpty()) initCard();
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"header\",\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + title + "\"}}"));
        return true;
    }

    /**
     * 增加文字组件
     * @param type 文本类型
     * @param content 文本
     * @return 成功
     */
    public static Boolean addSectionComponent(TextType type, String content) {
        if (JsonCard.isEmpty()) initCard();
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"section\",\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + content + "\"}}"));
        return true;
    }

    /**
     * 增加多栏文字组件
     * @param col 栏数
     * @param text 文本，为了方便读取用Map存储，前一个代表文本类型，后面代表文本
     * @return 成功
     */
    public static Boolean addColsSectionComponent(Cols col, Map<TextType,String> text) {
        if (JsonCard.isEmpty()) initCard();
        int Col = switch (col.toString()) {
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            default -> 6;
        };
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"section\",\"text\": { \"type\": \"paragraph\", \"cols\": " + Col + ",\"fields\": []}}"));
        for(int i = 0; i < MapUtil.ergodicMaps(text).size();i++) {
            String Type;
            if (Objects.equals(MapUtil.ergodicMaps(text).get(i).get(0).toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
            JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").size() - 1).getJSONObject("text").getJSONArray("fields").add(JSONObject.parseObject("{\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + MapUtil.ergodicMaps(text).get(i).get(1) + "\"}}"));
        }
        return true;
    }

    /**
     * 增加备注组件
     * @param text 组件，为了方便读取用Map存储，前一个代表组件类型类型，后面代表值（如果为图片就是连接，为文本就是内容）（参数名取这个是因为不想改了。。。）
     * @return 成功
     */
    public static Boolean addTextRemarkComponent(Map<RemarkType,String> text) {
        if (JsonCard.isEmpty()) initCard();

        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"remark\",\"elements\": []}"));
        for(int i = 0; i < MapUtil.ergodicMaps(text).size();i++) {
            String Type;
            String type = MapUtil.ergodicMaps(text).get(i).get(0).toString();
            if (Objects.equals(type, "Markdown")) Type = "dodo-md"; else if(Objects.equals(type, "PlainText")) Type = "plain-text"; else Type = "image";
            if (Type.equals("image")) {
                JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").size() - 1).getJSONArray("elements").add(JSONObject.parseObject("{\"text\": { \"type\": \"" + Type + "\", \"src\": \"" + MapUtil.ergodicMaps(text).get(i).get(1) + "\"}}"));
            } else {
                JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").size() - 1).getJSONArray("elements").add(JSONObject.parseObject("{\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + MapUtil.ergodicMaps(text).get(i).get(1) + "\"}}"));
            }
        }
        return true;
    }

    /**
     * 增加图片组件
     * @param url 连接
     * @return 成功
     */
    public static Boolean addImageComponent(String url) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"image\", \"src\": \"" + url + "\"}"));
        return true;
    }

    /**
     * 增加多图组件
     * @param urls 图片的连接
     * @return 成功
     */
    public static Boolean addImageGroupComponent(List<String> urls) {
        if (JsonCard.isEmpty()) initCard();

        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"image-group\", \"elements\": []}"));
        if (urls.size() > 9) return false;
        for (int i = 0; i < urls.size();i ++) {
            JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").size() - 1).getJSONArray("elements").add(JSONObject.parseObject("{\"type\": \"image\", \"src\": \"" + urls.get(1) + "\"}"));
        }
        return true;
    }

    /**
     * 增加视频组件
     * @param videoUrl 视频连接
     * @param coverUrl 封面连接
     * @param title 标题
     * @return 成功
     */
    public static Boolean addVideoComponent(String videoUrl, String coverUrl, String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"video\", \"src\": \"" + videoUrl + "\", \"cover\": \"" + coverUrl + "\",\"title\": \"" + title + "\"}"));
        return true;
    }

    /**
     * 增加视频组件
     * @param videoUrl 视频连接
     * @param coverUrl 封面连接
     * @return 成功
     */
    public static Boolean addVideoComponent(String videoUrl, String coverUrl) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"video\", \"src\": \"" + videoUrl + "\", \"cover\": \"" + coverUrl + "\"}"));
        return true;
    }

    /**
     * 怎级啊倒计时组件
     * @param style 显示样式
     * @param endTime 结束时间戳
     * @param title 标题
     * @return 成功
     */
    public static Boolean addCountdownComponent(Style style, Long endTime, String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"countdown\", \"endTime\": " + endTime + ", \"style\": \"" + style.toString() + "\",\"title\": \"" + title + "\"}"));
        return true;
    }
    /**
     * 怎级啊倒计时组件
     * @param style 显示样式
     * @param endTime 结束时间戳
     * @return 成功
     */
    public static Boolean addCountdownComponent(Style style, Long endTime) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"countdown\", \"endTime\": " + endTime + ", \"style\": \"" + style.toString() + "\"}"));
        return true;
    }

    /**
     * 添加分割线
     * @return 成功
     */
    public static Boolean addDividerComponent() {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").add(JSONObject.parseObject("{\"type\": \"divider\"}"));
        return true;
    }

    /**
     * 删除一个组件
     * @param count 第几个组件（从1开始）
     * @return 成功
     */
    public static Boolean removeComponent(int count) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").remove(count + 1);
        return true;
    }
}
