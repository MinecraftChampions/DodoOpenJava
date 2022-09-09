package io.github.mcchampions.DodoOpenJava.Configuration.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示ConfigurationSerializable可存储为的“别名”。如果ConfigurationSerializable类上不存在此项，则它将使用该类的完全限定名。
 *
 * 此值将存储在配置中，以便配置反序列化可以确定其类型。
 *
 * 在ConfigurationSerializable以外的任何其他类上使用此注释将无效。
 *
 * @see ConfigurationSerialization#registerClass(Class, String)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SerializableAs {
    public String value();
}
