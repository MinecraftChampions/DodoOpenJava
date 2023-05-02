package io.github.minecraftchampions.dodoopenjava.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �������Ҫ������¼���ע�͡�
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    /**
     * �����¼������ȼ���
     * <p>
     * �¼���ִ�е�˳���Ⱥ����У���
     * <ol>
     * <li>LOWEST</li>
     * <li>LOW</li>
     * <li>NORMAL</li>
     * <li>HIGH</li>
     * <li>HIGHEST</li>
     * <li>MONITOR</li>
     * </ol>
     * @return ���ȼ�
     */
    EventPriority priority() default EventPriority.NORMAL;
}
