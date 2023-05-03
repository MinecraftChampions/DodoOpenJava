package io.github.minecraftchampions.dodoopenjava.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataUnit {
    private String lastName = "";

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.equalsIgnoreCase(this.lastName)) {
            this.lastName = lastName;
            changed = true;
        }
    }

    /**
     * �Ƴ�Ȩ��
     * @param permission Ȩ��
     * @return true/false
     */
    public boolean removePermission(String permission) {
        changed = true;
        return permissions.remove(permission);
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    private boolean changed;

    private final List<String> permissions = new ArrayList<>();

    DataUnit(String name) {
        setLastName(name);
    }

    /**
     * �ж��Ƿ���һ����Ȩ��
     * @param permission Ȩ��
     * @return true/false
     */
    public boolean hasSamePermission(String permission) {
        return permissions.contains(permission);
    }

    /**
     * ���Ȩ��
     *
     * @param permission Ȩ��
     */
    public void addPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
            changed = true;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof DataUnit go) {
            if (this.getLastName().equalsIgnoreCase(go.getLastName())) {
                return Objects.equals(this.lastName, go.getLastName()) &&
                        Objects.equals(this.changed, go.isChanged()) &&
                        Objects.equals(this.permissions, go.getPermissions());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.lastName != null ? this.lastName.toLowerCase().hashCode() : 0);
        return hash;
    }
}
