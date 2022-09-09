package io.github.mcchampions.DodoOpenJava.Configuration.serialization;

import java.util.Map;

/**
 * 有关反序列化的一些东西
 *
 * @see DelegateDeserialization
 * @see SerializableAs
 */
public interface ConfigurationSerializable {
    Map<String, Object> serialize();
}
