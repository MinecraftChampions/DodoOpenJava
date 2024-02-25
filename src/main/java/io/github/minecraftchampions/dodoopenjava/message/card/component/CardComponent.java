package io.github.minecraftchampions.dodoopenjava.message.card.component;

import org.json.JSONObject;

/**
 * 组件
 *
 * @author qscbm187531
 */
public interface CardComponent {
    /**
     * 获取类型
     *
     * @return type
     */
    String getType();

    /**
     * 转换为json
     *
     * @return json
     */
    JSONObject toJsonObject();
}