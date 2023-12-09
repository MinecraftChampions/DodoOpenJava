package io.github.minecraftchampions.dodoopenjava.card;

import io.github.minecraftchampions.dodoopenjava.card.component.Component;
import io.github.minecraftchampions.dodoopenjava.card.enums.Theme;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡片消息
 */
public class Card {
    private JSONObject JsonCard = new JSONObject();

    /**
     * 是否不存在
     *
     * @return true/false
     */
    public boolean isEmpty() {
        return JsonCard.isEmpty();
    }

    /**
     * 是否不存在
     *
     * @param card Card
     * @return true/false
     */
    public static boolean isEmpty(Card card) {
        return card.toJSONObject().isEmpty();
    }

    /**
     * 转换为JSON对象
     *
     * @return true
     */
    public JSONObject toJSONObject() {
        return JsonCard;
    }

    /**
     * 初始化卡片
     *
     * @param card JSON
     */
    public Card(JSONObject card) {
        JsonCard = card;
    }

    /**
     * 初始化卡片
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
     * 编辑风格
     *
     * @param theme 分割
     */
    public void editTheme(Theme theme) {
        JsonCard.getJSONObject("card").put("theme", theme.getType());
    }

    /**
     * 编辑标题
     *
     * @param title 标题
     */
    public void editTitle(String title) {
        JsonCard.getJSONObject("card").put("title", title);
    }

    /**
     * 编辑文本
     *
     * @param content 文本
     */
    public void editContent(String content) {
        JsonCard.put("content", content);
    }

    /**
     * 移除文本
     */
    public void removeContent() {
        if (!JsonCard.keySet().contains("content")) return;
        JsonCard.remove("content");
    }

    /**
     * 增加组件
     *
     * @param component 组件
     */
    public void addComponent(Component component) {
        JsonCard.getJSONObject("card").getJSONArray("components").put(component.getJsonCard());
    }

    /**
     * 移除组件，如果有多个相同的则全部移除
     *
     * @param component 组件
     */
    public void removeComponent(Component component) {
        List<Object> list = JsonCard.getJSONObject("card").getJSONArray("components").toList();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            if (object instanceof JSONObject jsonObject) {
                if (component.getJsonCard() == jsonObject) {
                    integerList.add(i);
                }
            }
        }
        for (int i = 0; i < integerList.size(); i++) {
            removeComponent(integerList.get(i) - i);
        }
    }


    /**
     * 删除一个组件
     *
     * @param index index
     */
    public void removeComponent(int index) {
        JsonCard.getJSONObject("card").getJSONArray("components").remove(index);
    }
}
