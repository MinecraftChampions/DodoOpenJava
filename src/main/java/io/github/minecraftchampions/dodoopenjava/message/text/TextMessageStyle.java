package io.github.minecraftchampions.dodoopenjava.message.text;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public enum TextMessageStyle {
    bold("**%s**"),
    italic("*%s*"),
    underline("__%s__"),
    strikethrough("~~%s~~"),
    code("```%s```"),
    hide("||%s||"),
    cite("\n>%s\n"),
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
