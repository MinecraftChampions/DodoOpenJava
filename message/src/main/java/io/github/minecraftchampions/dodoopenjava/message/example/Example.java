package io.github.minecraftchampions.dodoopenjava.message.example;

import io.github.minecraftchampions.dodoopenjava.message.MessageComponent;
import io.github.minecraftchampions.dodoopenjava.message.MessageComponent.MessageComponentBuilder;
import io.github.minecraftchampions.dodoopenjava.message.MessageUtil;
import io.github.minecraftchampions.dodoopenjava.message.MiniMessageParser;
import io.github.minecraftchampions.dodoopenjava.message.TextComponentGroup;

/**
 * 示例
 */
public class Example {
    public static void main(String... args) {
        String t1 = "<a><b><>c";
        t1 = MessageUtil.replaceXmlSpecialCharacters(t1);
        System.out.println(t1);
        String str = "<antispoiler>antispoiler</antispoiler>若<link url=\"github.com\">链接</link>";
        MessageComponent component = MiniMessageParser.parse(str);
        System.out.println(MiniMessageParser.toMarkdown(str));
        System.out.println(component);
        MessageComponentBuilder builder = MessageComponent.builder();
        builder.append(TextComponentGroup.codeComponent,"int i = 0;")
                .append(TextComponentGroup.boldComponent,"粗体")
                .append("\n")
                .append(TextComponentGroup.linkComponent,"跳转github","github.com")
                .append(TextComponentGroup.underlineComponent,"下划线:" + component)
                .append(TextComponentGroup.italicComponent,"斜体")
                .append(TextComponentGroup.citeComponent,"引用");
        MessageComponent messageComponent = builder.build();
        System.out.println(messageComponent);
        System.out.println(MiniMessageParser.toMarkdown(messageComponent.toString()));
    }
}
