package io.github.minecraftchampions.dodoopenjava.permissions;

/**
 * �û�
 */
public class User extends DataUnit implements Cloneable {

    private String group;

    public User(String name) {
        super(name);
        this.group = GroupManager.getDefaultGroup().getName();
    }

    @Override
    public User clone() {
        User clone = new User(this.getLastName());
        clone.group = this.group;

        for (String perm : this.getPermissions()) {
            clone.addPermission(perm);
        }
        return clone;
    }

    public Group getGroup() {
        return new Group(group);
    }

    public String getGroupName() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group.getName();
        setChanged(true);
    }

    public boolean hasPerm(String perm) {
        return UserManager.hasPerm(this,perm);
    }
}