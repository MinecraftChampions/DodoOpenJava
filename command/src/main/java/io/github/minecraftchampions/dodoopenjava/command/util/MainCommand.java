package io.github.minecraftchampions.dodoopenjava.command.util;

import java.lang.annotation.*;

/**
 * 主命令注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MainCommand {
    String command();
    String permission() default "";
}
