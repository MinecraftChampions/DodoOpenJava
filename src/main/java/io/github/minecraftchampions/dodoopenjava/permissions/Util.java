package io.github.minecraftchampions.dodoopenjava.permissions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class Util {
    /**
     * 判断用户是否拥有权限(请传入前面不带横杠的权限)
     *
     * @param userPerm 用户权限
     * @param perm     权限
     * @return true/false
     */
    public static boolean hasPermission(String userPerm, String perm) {
        if (perm == null || perm.isEmpty()) {
            return true;
        }
        if (comparePermissionString(userPerm, perm)) {
            return !comparePermissionString(userPerm, perm);
        } else {
            return false;
        }
    }

    /**
     * 判断权限是否符合
     *
     * @param userPerm 用户的权限
     * @param perm     判断的权限
     * @return ture/false
     */
    private static boolean comparePermissionString(String userPerm, String perm) {
        if (userPerm.equals(perm)) {
            return true;
        }
        Pattern pattern = Pattern.compile(userPerm);
        Matcher matcher = pattern.matcher(perm);
        boolean has = false;
        while (matcher.find()) {
            int index = Integer.parseInt(String.format("%d", matcher.start()));
            if (index == 0) {
                has = true;
            }
            String str = matcher.group();
        }
        return has;
    }
}
