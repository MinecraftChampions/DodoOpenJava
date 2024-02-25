package io.github.minecraftchampions.dodoopenjava.message.text;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * 文本消息样式
 *
 * @author qscbm187531
 */
@Getter
public enum TextMessageStyle {
    /**
     * 粗体
     */
    bold("**%s**"),
    /**
     * 斜体
     */
    italic("*%s*"),
    /**
     * 下划线
     */
    underline("__%s__"),
    /**
     * 删除线
     */
    strikethrough("~~%s~~"),
    /**
     * 代码块
     */
    code("```%s```"),
    /**
     * 隐藏
     */
    hide("||%s||"),
    /**
     * 引用
     */
    cite("\n>%s\n"),
    /**
     * 正常
     */
    normal("%s");

    private final String regex;

    TextMessageStyle(String regex) {
        this.regex = regex;
    }

    public static Set<String> getStyles() {
        Set<String> set = new HashSet<>();
        set.add("bold");
        set.add("italic");
        set.add("underline");
        set.add("strikethrough");
        set.add("code");
        set.add("hide");
        set.add("cite");
        set.add("normal");
        set.add("link");
        set.add("#text");
        return set;
    }
}