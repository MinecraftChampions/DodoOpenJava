package io.github.minecraftchampions.dodoopenjava.permission;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    private int value;

    /**
     * 计算权限
     *
     * @param permissions 权限列表
     * @return 权限
     */
    public static Optional<Permission> calculatePermission(@NonNull Permission... permissions) {
        int length = permissions.length;
        if (length == 0) {
            return Optional.empty();
        }
        int permissionValue = permissions[0].value;
        for (int i = 1; i < length; i++) {
            permissionValue |= permissions[i].value;
        }
        return Optional.of(new Permission(permissionValue));
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
        return (permission.value & target.value) == target.value;
    }

    /**
     * 从最终权限中倒推所有权限
     *
     * @param target 权限
     * @return 权限列表
     */
    public static List<DodoPermission> resolvePermissionList(@NonNull Permission target) {
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
    public List<DodoPermission> resolvePermissionList() {
        return resolvePermissionList(this);
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