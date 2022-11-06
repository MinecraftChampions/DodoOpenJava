package io.github.mcchampions.DodoOpenJava.Configuration.serialization;

import java.util.Map;

/**
 * �йط����л���һЩ����
 *
 * @see DelegateDeserialization
 * @see SerializableAs
 */
public interface ConfigurationSerializable {
    Map<String, Object> serialize();
}
