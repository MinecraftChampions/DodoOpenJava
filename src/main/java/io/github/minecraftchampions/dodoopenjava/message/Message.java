package io.github.minecraftchampions.dodoopenjava.message;

import org.json.JSONObject;

/**
 * 消息接口
 *
 * @author qscbm187531
 */
public interface Message {
    /**
     * 转换为JSONObject
     *
     * @return jsonObject
     */
    JSONObject toMessage();

    /**
     * 获取消息类型
     *
     * @return 数字类型
     */
    int getType();
}