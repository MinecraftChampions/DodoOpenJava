package io.github.minecraftchampions.dodoopenjava.command.util;

import java.lang.annotation.*;

/**
 * Ö÷ÃüÁî×¢½â
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MainCommand {
    String command();
    String permission() default "";
}
