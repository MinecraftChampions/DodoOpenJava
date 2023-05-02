package io.github.minecraftchampions.dodoopenjava.command.util;

import java.lang.annotation.*;

/**
 * ������ע��
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubCommand {
    String mainCommand();

    String subCommand();

    String permission() default "";
}
