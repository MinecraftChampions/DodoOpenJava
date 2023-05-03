package io.github.minecraftchampions.dodoopenjava.permissions;

import java.util.LinkedList;
import java.util.List;

/**
 * È¨ÏÞ×é
 * @author qscbm187531
 */
public class Group extends DataUnit implements Cloneable {
    private List<String> inherits = new LinkedList<>();

    public Group(String name) {
        super(name);
    }

    public String getName() {
        return this.getLastName();
    }

    @Override
    public Group clone() {
        Group clone = new Group(this.getName());

        for (String perm : this.getPermissions()) {
            clone.addPermission(perm);
        }

        return clone;
    }

    public List<String> getInherits() {
        return inherits;
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
        return GroupManager.hasPerm(this,perm);
    }
}
