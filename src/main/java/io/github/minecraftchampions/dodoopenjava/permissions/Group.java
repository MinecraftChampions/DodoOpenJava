package io.github.minecraftchampions.dodoopenjava.permissions;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * 权限组
 */
@Getter
public class Group extends DataUnit {
    private final List<String> inherits = new LinkedList<>();

    public Group(String name) {
        super(name);
    }

    public String getName() {
        return this.getLastName();
    }

    public boolean isDefault() {
        return GroupManager.getDefaultGroup() == this;
    }

    public void addInherits(Group inherit) {
        if (!GroupManager.groupExists(inherit.getName())) {
            GroupManager.addGroup(inherit);
        }
        if (!inherits.contains(inherit.getName())) {
            inherits.add(inherit.getName());
        }
        setChanged(true);
    }

    public boolean removeInherits(String inherit) {
        if (this.inherits.contains(inherit.toLowerCase())) {
            setChanged(true);
            return inherits.remove(inherit.toLowerCase());
        }
        return false;
    }

    public boolean hasPerm(String perm) {
        return GroupManager.hasPerm(this, perm);
    }
}
