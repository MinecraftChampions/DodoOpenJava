package io.github.minecraftchampions.dodoopenjava.permission;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dodo权限
 *
 * @author qscbm187531
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Permission {
    @NonNull
    private int value;

    /**
     * 计算权限
     *
     * @param permissions 权限列表
     * @return 权限
     */
    public static Permission calculatePermission(@NonNull Permission... permissions) {
        int length = permissions.length;
        if (length == 1) {
            return permissions[0];
        }
        int permissionValue = permissions[0].getValue();
        for (int i = 1; i < length; i++) {
            permissionValue |= permissions[i].getValue();
        }
        return new Permission(permissionValue);
    }

    /**
     * 从16进制权限值解析一个权限
     *
     * @param hexString 权限值
     * @return 权限
     */
    public static Permission parseHexString(@NonNull String hexString) {
        return new Permission(Integer.parseInt(hexString, 16));
    }

    /**
     * 检查target是否存在permission中
     *
     * @param permission 权限
     * @param target     目标
     * @return 是否存在
     */
    public static boolean checkPermissionExist(@NonNull Permission permission, @NonNull Permission target) {
        return (permission.getValue() & target.getValue()) == target.getValue();
    }

    /**
     * 从最终权限中倒推所有权限
     *
     * @param target 权限
     * @return 权限列表
     */
    public static List<DodoPermission> getPermissionList(@NonNull Permission target) {
        DodoPermission[] detectedPermissionList = DodoPermission.values();
        return Arrays.stream(detectedPermissionList)
                .filter(permission -> checkPermissionExist(target, permission.getPermission()))
                .collect(Collectors.toList());
    }

    /**
     * 从最终权限中倒推所有权限
     *
     * @return 权限列表
     */
    public List<DodoPermission> getPermissionList() {
        DodoPermission[] detectedPermissionList = DodoPermission.values();
        return Arrays.stream(detectedPermissionList)
                .filter(permission -> checkPermissionExist(this, permission.getPermission()))
                .collect(Collectors.toList());
    }

    /**
     * 转换为16进制
     *
     * @return 权限
     */
    public String toHexString() {
        return Integer.toHexString(value);
    }
}
