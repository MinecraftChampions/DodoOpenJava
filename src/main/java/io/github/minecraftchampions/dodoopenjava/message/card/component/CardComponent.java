package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 组件
 */
@Getter
public abstract class CardComponent implements Serializable {
    protected JSONObject jsonCard = new JSONObject();

    /**
     * 初始化
     */
    public CardComponent() {
    }

}
