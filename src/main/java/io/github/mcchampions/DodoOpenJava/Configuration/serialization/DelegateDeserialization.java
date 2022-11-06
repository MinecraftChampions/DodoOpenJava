package io.github.mcchampions.DodoOpenJava.Configuration.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ӧ���ڽ����з����л�ί�и���һ��ConfigurationSerializable��ConfigurationSerializable��
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DelegateDeserialization {
    /**
     * �ĸ���Ӧ�������෴���л��Ĵ���
     *
     * @return ������
     */
    public Class<? extends ConfigurationSerializable> value();
}
