package io.github.mcchampions.DodoOpenJava.Utils.Command;

import java.lang.annotation.*;

/**
 * ������ע��
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MainCommand {
    String command();
    String permission() default "";
}
