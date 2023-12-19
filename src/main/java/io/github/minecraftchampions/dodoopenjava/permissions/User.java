package io.github.minecraftchampions.dodoopenjava.permissions;

import lombok.NonNull;

/**
 * 用户
 */
public class User extends DataUnit {

    private String group;

    public User(String name) {
        super(name);
        this.group = GroupManager.getDefaultGroup().getName();
    }

    public Group getGroup() {
        return new Group(group);
    }

    public String getGroupName() {
        return group;
    }

    public void setGroup(@NonNull Group group) {
        this.group = group.getName();
        setChanged(true);
    }

    public boolean hasPerm(String perm) {
        return UserManager.hasPerm(this, perm);
    }
}