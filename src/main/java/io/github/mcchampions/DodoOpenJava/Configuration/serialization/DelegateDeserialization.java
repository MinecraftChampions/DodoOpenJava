package io.github.mcchampions.DodoOpenJava.Configuration.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 应用于将所有反序列化委托给另一个ConfigurationSerializable的ConfigurationSerializable。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DelegateDeserialization {
    /**
     * 哪个类应用作此类反序列化的代理
     *
     * @return 代理类
     */
    public Class<? extends ConfigurationSerializable> value();
}
