package io.github.minecraftchampions.dodoopenjava.permissions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限实例
 */
@Getter
@EqualsAndHashCode
public class DataUnit {
    private String lastName = "";

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
        changed = true;
    }

    /**
     * 移除权限
     *
     * @param permission 权限
     * @return true/false
     */
    public boolean removePermission(@NonNull String permission) {
        changed = true;
        return permissions.remove(permission);
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    private boolean changed;

    private final List<String> permissions = new ArrayList<>();

    DataUnit(@NonNull String name) {
        setLastName(name);
    }

    /**
     * 判断是否有一样的权限
     *
     * @param permission 权限
     * @return true/false
     */
    public boolean hasSamePermission(@NonNull String permission) {
        return permissions.contains(permission);
    }

    /**
     * 添加权限
     *
     * @param permission 权限
     */
    public void addPermission(@NonNull String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
            changed = true;
        }
    }
}
