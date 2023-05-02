package io.github.minecraftchampions.dodoopenjava.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记要处理的事件的注释。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    /**
     * 定义事件的优先级。
     * <p>
     * 事件被执行的顺序（先后排列）：
     * <ol>
     * <li>LOWEST</li>
     * <li>LOW</li>
     * <li>NORMAL</li>
     * <li>HIGH</li>
     * <li>HIGHEST</li>
     * <li>MONITOR</li>
     * </ol>
     * @return 优先级
     */
    EventPriority priority() default EventPriority.NORMAL;
}
