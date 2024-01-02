package io.github.minecraftchampions.dodoopenjava.permissions.data;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

/**
 * 存储实例
 */
@Getter
public abstract class PermData {
    public File User;
    public File Group;

    public abstract void saveToFile() throws IOException;
}
