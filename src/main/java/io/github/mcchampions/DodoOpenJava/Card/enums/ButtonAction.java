package io.github.mcchampions.DodoOpenJava.Card.enums;

/**
 * 按钮的相关枚举
 * @author Administrator
 */

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
     */
    private final String type;

    /**
     * 获取类型
     * @return 类型
     */
    public String getType() {
        return type;
    }
}
