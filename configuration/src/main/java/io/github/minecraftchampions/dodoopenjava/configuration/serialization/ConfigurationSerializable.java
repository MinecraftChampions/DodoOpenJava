package io.github.minecraftchampions.dodoopenjava.configuration.serialization;

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
