package io.github.mcchampions.DodoOpenJava.Card;

import org.json.JSONArray;
import org.json.JSONObject;
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
    public JSONObject JsonCard;

    /**
     * 是否不存在
     * @return true/false
     */
    public Boolean isEmpty() {
        return JsonCard.isEmpty();
    }

    /**
     * 是否不存在
     * @param card Card
     * @return true/false
     */
    public Boolean isEmpty(Card card) {
        return card.toJSONObject().isEmpty();
    }

    /**
     * 转换为JSON对象
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonCard;
    }

    /**
     * 初始化卡片
     * @param card JSON
     * @return true
     */
    public Boolean initCard(JSONObject card) {
        JsonCard = card;
        return true;
    }

    /**
     * 初始化卡片
     * @return true/false
     */
    public Boolean initCard() {
        if (JsonCard.isEmpty()) {
            JsonCard = new JSONObject("""
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
    public Boolean editTheme(Theme theme) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").put("theme", StrUtil.toLowerCase(theme.toString()));
        return true;
    }

    /**
     * 编辑标题
     * @param title 标题
     * @return 成功
     */
    public Boolean editTitle(String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").put("title", title);
        return true;
    }

    /**
     * 编辑文本
     * @param content 文本
     * @return 成功
     */
    public Boolean editContent(String content) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.put("content", content);
        return true;
    }

    /**
     * 移除文本
     * @return true/false
     */
    public Boolean removeContent() {
        if (JsonCard.isEmpty()) initCard();
        if (!JsonCard.keySet().contains("content")) return false;
        JsonCard.remove("content");
        return true;
    }

    /**
     * 增加标题组件
     * @param type 文本类型
     * @param title 标题
     * @return 成功
     */
    public Boolean addHeaderComponent(TextType type, String title) {
        if (JsonCard.isEmpty()) initCard();
        String Type;
        if (Objects.equals(type.toString(), "Markdown")) Type = "dodo-md"; else Type = "plain-text";
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"header\",\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + title + "\"}}"));
        return true;
    }

    /**
     * 增加文字组件
     * @param section 组件对象
     * @return 成功
     */
    public Boolean addSectionComponent(Section section) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(section.toJSONObject());
        return true;
    }


    /**
     * 增加备注组件
     * @param text 组件，为了方便读取用Map存储，前一个代表组件类型类型，后面代表值（如果为图片就是连接，为文本就是内容）（参数名取这个是因为不想改了。。。）
     * @return 成功
     */
    public Boolean addTextRemarkComponent(Map<RemarkType,String> text) {
        if (JsonCard.isEmpty()) initCard();

        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"remark\",\"elements\": []}"));
        for(int i = 0; i < MapUtil.ergodicMaps(text).size();i++) {
            String Type;
            String type = MapUtil.ergodicMaps(text).get(i).get(0).toString();
            if (Objects.equals(type, "Markdown")) Type = "dodo-md"; else if(Objects.equals(type, "PlainText")) Type = "plain-text"; else Type = "image";
            if (Type.equals("image")) {
                JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").toList().size() - 1).getJSONArray("elements").put(new JSONObject("{\"text\": { \"type\": \"" + Type + "\", \"src\": \"" + MapUtil.ergodicMaps(text).get(i).get(1) + "\"}}"));
            } else {
                JsonCard.getJSONObject("card").getJSONArray("components").getJSONObject(JsonCard.getJSONObject("card").getJSONArray("components").toList().size() - 1).getJSONArray("elements").put(new JSONObject("{\"text\": { \"type\": \"" + Type + "\", \"content\": \"" + MapUtil.ergodicMaps(text).get(i).get(1) + "\"}}"));
            }
        }
        return true;
    }

    /**
     * 增加图片组件
     * @param url 连接
     * @return 成功
     */
    public Boolean addImageComponent(String url) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"image\", \"src\": \"" + url + "\"}"));
        return true;
    }

    /**
     * 增加多图组件
     * @param urls 图片的连接
     * @return 成功
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
     * 增加视频组件
     * @param videoUrl 视频连接
     * @param coverUrl 封面连接
     * @param title 标题
     * @return 成功
     */
    public Boolean addVideoComponent(String videoUrl, String coverUrl, String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"video\", \"src\": \"" + videoUrl + "\", \"cover\": \"" + coverUrl + "\",\"title\": \"" + title + "\"}"));
        return true;
    }

    /**
     * 增加视频组件
     * @param videoUrl 视频连接
     * @param coverUrl 封面连接
     * @return 成功
     */
    public Boolean addVideoComponent(String videoUrl, String coverUrl) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"video\", \"src\": \"" + videoUrl + "\", \"cover\": \"" + coverUrl + "\"}"));
        return true;
    }

    /**
     * 增加倒计时组件
     * @param style 显示样式
     * @param endTime 结束时间戳
     * @param title 标题
     * @return 成功
     */
    public Boolean addCountdownComponent(Style style, Long endTime, String title) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"countdown\", \"endTime\": " + endTime + ", \"style\": \"" + style.toString() + "\",\"title\": \"" + title + "\"}"));
        return true;
    }
    /**
     * 增加倒计时组件
     * @param style 显示样式
     * @param endTime 结束时间戳
     * @return 成功
     */
    public Boolean addCountdownComponent(Style style, Long endTime) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"countdown\", \"endTime\": " + endTime + ", \"style\": \"" + style.toString() + "\"}"));
        return true;
    }

    /**
     * 添加分割线
     * @return 成功
     */
    public Boolean addDividerComponent() {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").put(new JSONObject("{\"type\": \"divider\"}"));
        return true;
    }

    /**
     * 删除一个组件
     * @param count 第几个组件（从1开始）
     * @return 成功
     */
    public Boolean removeComponent(int count) {
        if (JsonCard.isEmpty()) initCard();
        JsonCard.getJSONObject("card").getJSONArray("components").remove(count + 1);
        return true;
    }

    /**
     * 增加一个交互按钮组件
     * @param button 按钮颜色
     * @return 成功
     */
    public Boolean addButton(Button button) {
        JsonCard.getJSONObject("card").getJSONArray("components").put(button.toJSONObject());
        return true;
    }

    /**
     * 增加列表选择器组件
     * <p>
     * 不需要参数的请填写null
     * @param interactCustomId 自定义交互id
     * @param placeholder 输入框提示
     * @param min 最少选中个数
     * @param max 最多选中个数
     * @param element 数据，为了方便用Map存储，前面为选项名。后面为选项描述，不需要请填写null
     * @return 成功
     */
    public Boolean addListSelector(String interactCustomId, String placeholder, int min, int max, Map<String,String> element) {
        if (JsonCard.isEmpty()) initCard();

        JSONObject json1 = new JSONObject();
        json1.put("type", "list-selector");
        json1.put("interactCustomId", interactCustomId);
        json1.put("placeholder", placeholder);
        json1.put("min", min);
        json1.put("max", max);
        JSONArray arr = new JSONArray();
        json1.put("elements", arr);

        for(int i = 0; i < MapUtil.ergodicMaps(element).size();i++) {
            JSONObject json2 = new JSONObject();

            json2.put("name", MapUtil.ergodicMaps(element).get(i).get(0));
            json2.put("desc", MapUtil.ergodicMaps(element).get(i).get(1));

            json1.getJSONArray("elements").put(json2);
        }

        JsonCard.getJSONObject("card").getJSONArray("components").put(json1);
        return true;
    }

    /**
     * 增加 文字与模块 交互组件
     * @param align 对齐方式，left：左对齐，right：右对齐
     * @param section 文字
     * @param button 按钮
     * @return
     */
    public Boolean addSection(Align align,Section section,Button button) {
        if (JsonCard.isEmpty()) initCard();

        JSONObject json1 = new JSONObject();
        json1.put("type", "section");
        json1.put("text", section);
        json1.put("align", align.toString());
        json1.put("accessory", button.toJSONObject());
        JsonCard.getJSONObject("card").getJSONArray("components").put(json1);
        return true;
    }

    /**
     * 转换为String（重写了Object的toString方法）
     * @return 字符串
     */
    public String toString() {
        return JsonCard.toString();
    }
}
