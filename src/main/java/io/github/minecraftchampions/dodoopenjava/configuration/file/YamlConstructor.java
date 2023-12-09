package io.github.minecraftchampions.dodoopenjava.configuration.file;

import io.github.minecraftchampions.dodoopenjava.configuration.serialization.ConfigurationSerialization;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.LinkedHashMap;
import java.util.Map;

public class YamlConstructor extends SafeConstructor {

    public YamlConstructor() {
        super(new LoaderOptions());
        this.yamlConstructors.put(Tag.MAP, new ConstructCustomObject());
    }

    private class ConstructCustomObject extends ConstructYamlMap {
        @Override
        public Object construct(Node node) {
            if (node.isTwoStepsConstruction()) {
                throw new YAMLException("意外的结构. 节点: " + node);
            }

            Map<?, ?> raw = (Map<?, ?>) super.construct(node);

            if (raw.containsKey(ConfigurationSerialization.SERIALIZED_TYPE_KEY)) {
                Map<String, Object> typed = new LinkedHashMap<>(raw.size());
                for (Map.Entry<?, ?> entry : raw.entrySet()) {
                    typed.put(entry.getKey().toString(), entry.getValue());
                }

                try {
                    return ConfigurationSerialization.deserializeObject(typed);
                } catch (IllegalArgumentException ex) {
                    throw new YAMLException("无法反序列化对象", ex);
                }
            }

            return raw;
        }

        @Override
        public void construct2ndStep(Node node, Object object) {
            throw new YAMLException("意外的结构.  节点: " + node);
        }
    }
}
