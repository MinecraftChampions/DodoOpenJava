package io.github.minecraftchampions.dodoopenjava.message;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextComponentGroup {
    public static TextComponent boldComponent = new TextComponent("bold","**${content}**",true,null);

    public static TextComponent italicComponent = new TextComponent("italic","*${content}*",true,null);

    public static TextComponent underlineComponent = new TextComponent("underline","__${content}__",true,null);

    public static TextComponent strikethroughComponent = new TextComponent("strikethrough","~~${content}~~",true,null);

    public static TextComponent codeComponent = new TextComponent("code","```${content}```",false,null);

    public static TextComponent antispoilerComponent = new TextComponent("antispoiler","||${content}||",true,null);

    public static TextComponent citeComponent = new TextComponent("cite","> ${content}",true,null);

    public static TextComponent linkComponent = new TextComponent("link","[${content}](${attrvalue})",false,"url");

    public static TextComponent contentComponent = new TextComponent("content","${content}",false,null);

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

    public static Set<TextComponent> getNotAllowNestTextComponentList() {
        Set<TextComponent> textComponentSet = new HashSet<>();
        for (int i : notAllowNestIndexList) {
            textComponentSet.add(componentList.get(i));
        }
        return textComponentSet;
    }

    public static List<TextComponent> getTextComponentList() {
        return componentList;
    }
}
