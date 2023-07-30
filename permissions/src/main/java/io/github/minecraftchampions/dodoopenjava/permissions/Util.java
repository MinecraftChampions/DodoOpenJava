package io.github.minecraftchampions.dodoopenjava.permissions;

import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
            if (!comparePermissionString(userPerm, perm)) {
                return true;
            } else {
                return false;
            }
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
            int index = Integer.parseInt(String.format("%d", matcher.start(), matcher.group()));
            if (index == 0) {
                has = true;
            }
        }
        return has;
    }

    /**
     * json转Toml
     * 以下的不能转换好
     * 1：
     * {
     * array: [
     * {
     * "key":"value"
     * }
     * ]
     * }
     * 数组里面别放JsonObject
     * 2：
     * {
     * "\"": "a"
     * }
     * 键不要有特殊字符
     *
     * @param s json
     * @return toml
     */
    public static String toToml(String s) {

        Map<String, JSONObject> map = changeMapForEach(new JSONObject(s));
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger i = new AtomicInteger();
        map.forEach((key, value) -> {
            i.getAndIncrement();
            String a = value.toString().replaceAll(",", "\n")
                    .replaceAll("\\{", "").replaceAll("}", "\n")
                    .replaceAll("\\[", "[\n    ").replaceAll("]", "\n]\n")
                    .replaceAll("\"([^\"]+)\":", "$1 = ")
                    .replaceAll("\"\n\"", "\",\n    \"");
            String toml = "[" + key + "]" + "\n" + a;
            if (Objects.equals(key, "\"")) {
                toml = a;
            }
            if (!toml.equals("{}")) {
                if (i.get() == map.size()) {
                    stringBuilder.append(toml);
                } else {
                    stringBuilder.append(toml).append("\n");
                }
            }
        });
        return String.valueOf(stringBuilder).replaceAll("\n\n", "\n");

    }

    public static Map<String, JSONObject> changeMapForEach(JSONObject jsonObject) {
        return changeMapForEach(jsonObject, null);
    }

    /**
     * 将jsonObject转换为以下map实例
     * [path/path]: {}
     * [path/p]: {}
     *
     * @param jsonObject jsonObject
     * @param key        父键
     * @return Map
     */
    public static Map<String, JSONObject> changeMapForEach(JSONObject jsonObject, String key) {
        HashMap<String, JSONObject> map = new HashMap<>();
        Set<String> keys = jsonObject.keySet();//获取键的列表
        if (key == null) {
            map.put("\"", jsonObject);
        }
        for (String s : keys) {
            Object object = jsonObject.get(s);
            //判断是否能转成JSONObject
            if (object instanceof JSONObject json) {
                //如果没有父key的话
                if (key != null) {
                    if (!Objects.equals(key, "\"")) {
                        map.put(key + "." + s, json);
                        map.putAll(changeMapForEach(json, key + "." + s));//继续遍历寻找
                    }
                } else {
                    map.put(s, json);
                    map.putAll(changeMapForEach(json, s));//继续遍历寻找
                }
            }
        }
        //如果没有父键
        //判断各个jsonObject是否重复
        if (key == null) {
            //克隆map循环
            ((HashMap<String, JSONObject>) map.clone()).forEach((k, value) -> {
                Set<String> keySet = value.keySet();//获取的json列表
                List<String> list = new ArrayList<>();
                HashMap<String, JSONObject> m = (HashMap<String, JSONObject>) map.clone();//克隆
                m.remove(k);//移除
                String[] strings = k.split("\\.");
                m.forEach((n, object) -> {
                    boolean contains;
                    String[] s = n.split("\\.");
                    if (s.length > strings.length) {
                        contains = true;
                        for (int i = 0; i < strings.length; i++) {
                            if (!Objects.equals(strings[i], s[i])) {
                                contains = false;
                                break;
                            }
                        }
                    } else {
                        return;
                    }
                    if (contains) {
                        list.add(n);
                    }
                    if (k.equals("\"")) {
                        list.add(n);
                    }
                });
                if (keySet.size() == list.size()) {
                    map.remove(k);
                } else {
                    JSONObject object = new JSONObject(value.toString());
                    if (k.equals("\"")) {
                        for (String var : list) {
                            String[] array = var.split("\\.");
                            object.remove(array[0]);
                        }
                    } else {
                        for (String var : list) {
                            String[] array = var.split("\\.");
                            object.remove(array[strings.length]);
                        }
                    }
                    map.put(k, object);
                }
            });
        }
        return map;
    }
}
