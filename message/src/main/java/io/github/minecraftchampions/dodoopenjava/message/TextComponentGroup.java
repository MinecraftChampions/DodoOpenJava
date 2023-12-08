package io.github.minecraftchampions.dodoopenjava.message;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * markDown组件组
 */
public class TextComponentGroup {
    /**
     * 粗体
     */
    public static TextComponent boldComponent = new TextComponent("bold", "**${content}**", true, null);

    /**
     * 斜体
     */
    public static TextComponent italicComponent = new TextComponent("italic", "*${content}*", true, null);

    /**
     * 下划线
     */
    public static TextComponent underlineComponent = new TextComponent("underline", "__${content}__", true, null);

    /**
     * 删除线
     */
    public static TextComponent strikethroughComponent = new TextComponent("strikethrough", "~~${content}~~", true, null);

    /**
     * 代码块
     */
    public static TextComponent codeComponent = new TextComponent("code", "```${content}```", false, null);

    /**
     * 隐藏
     */
    public static TextComponent antispoilerComponent = new TextComponent("antispoiler", "||${content}||", true, null);

    /**
     * 引用, 头尾皆换行
     */
    public static TextComponent citeComponent = new TextComponent("cite", "\n> ${content}\n", true, null);

    /**
     * 链接
     */
    public static TextComponent linkComponent = new TextComponent("link", "[${content}](${attrvalue})", false, "url");

    /**
     * 字符串
     */
    public static TextComponent contentComponent = new TextComponent("content", "${content}", false, null);

    @Getter
    private static final Map<String, TextComponent> htmlReplaceMap = new HashMap<>(Map.of("p", contentComponent, "strong", boldComponent, "blockquote", citeComponent, "a", linkComponent, "em", italicComponent, "code", codeComponent));

    private static final List<TextComponent> componentList = new ArrayList<>();

    private static final Set<Integer> notAllowNestIndexList = new HashSet<>();

    static {
        Class<TextComponentGroup> clazz = TextComponentGroup.class;
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (field.getName().matches(".*Component\\d?")) {
                try {
                    TextComponent text = (TextComponent) field.get(null);
                    componentList.add(text);
                    if (!text.isAllowNest()) {
                        notAllowNestIndexList.add(componentList.indexOf(text));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 获取不允许嵌套的组件
     *
     * @return Set
     */
    public static Set<TextComponent> getNotAllowNestTextComponentList() {
        Set<TextComponent> textComponentSet = new HashSet<>();
        for (int i : notAllowNestIndexList) {
            textComponentSet.add(componentList.get(i));
        }
        return textComponentSet;
    }


    /**
     * 获取所有组件
     *
     * @return List
     */
    public static List<TextComponent> getTextComponentList() {
        return componentList;
    }
}
