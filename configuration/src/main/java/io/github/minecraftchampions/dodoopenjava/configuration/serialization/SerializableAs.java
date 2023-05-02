package io.github.minecraftchampions.dodoopenjava.configuration.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��ʾConfigurationSerializable�ɴ洢Ϊ�ġ������������ConfigurationSerializable���ϲ����ڴ��������ʹ�ø������ȫ�޶�����
 *
 * ��ֵ���洢�������У��Ա����÷����л�����ȷ�������͡�
 *
 * ��ConfigurationSerializable������κ���������ʹ�ô�ע�ͽ���Ч��
 *
 * @see ConfigurationSerialization#registerClass(Class, String)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SerializableAs {
    public String value();
}
