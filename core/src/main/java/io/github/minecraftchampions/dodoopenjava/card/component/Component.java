package io.github.minecraftchampions.dodoopenjava.card.component;

import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 组件
 */
@Getter
public abstract class Component implements Serializable {
    protected JSONObject jsonCard = new JSONObject();

    /**
     * 初始化
     */
    public Component() {
    }

}
