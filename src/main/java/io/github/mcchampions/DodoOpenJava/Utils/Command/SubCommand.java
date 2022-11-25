package io.github.mcchampions.DodoOpenJava.Utils.Command;

import java.lang.annotation.*;

/**
 * ×ÓÃüÁî×¢½â
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubCommand {
    String mainCommand();

    String subCommand();

    String permission() default "";
}
