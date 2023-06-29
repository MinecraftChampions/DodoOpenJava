package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.permissions.data.JsonData;
import io.github.minecraftchampions.dodoopenjava.permissions.data.TomlData;
import io.github.minecraftchampions.dodoopenjava.permissions.data.XmlData;
import io.github.minecraftchampions.dodoopenjava.permissions.data.YamlData;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * 文件监听
 */
public class FileListener extends FileAlterationListenerAdaptor {
    @Override
    public void onFileChange(File file) {
        switch (Permissions.type.getType()) {
            case "YAML" -> Permissions.permData = new YamlData();
            case "JSON" -> Permissions.permData = new JsonData();
            case "Xml" -> Permissions.permData = new XmlData();
            case "Toml" -> Permissions.permData = new TomlData();
            default -> System.err.println("错误的存储种类");
        }
    }

    @Override
    public void onFileDelete(File file) {
        switch (Permissions.type.getType()) {
            case "YAML" -> Permissions.permData = new YamlData();
            case "JSON" -> Permissions.permData = new JsonData();
            case "Xml" -> Permissions.permData = new XmlData();
            case "Toml" -> Permissions.permData = new TomlData();
            default -> System.err.println("错误的存储种类");
        }
    }
}
