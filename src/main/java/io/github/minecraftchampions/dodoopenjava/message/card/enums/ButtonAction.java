package io.github.minecraftchampions.dodoopenjava.message.card.enums;

import lombok.Getter;

/**
 * 按钮的相关枚举
 */

@Getter
public enum ButtonAction {
    /**
     * 跳转URL
     */
    link_url("link_url"),
    /**
     * 回传参数
     */
    call_back("call_back"),
    /**
     * 复制文本
     */
    copy_content("copy_content"),
    /**
     * 回传表单
     */
    form("form");

    ButtonAction(String type) {
        this.type = type;
    }

    /**
     * 行数
     * -- GETTER --
     * 获取类型
     */
    private final String type;

}
