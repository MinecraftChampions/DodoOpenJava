package io.github.minecraftchampions.dodoopenjava.permissions;

import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������
 */
public class Util {

    /**
     * �ж�Ȩ���Ƿ����
     * @param userPerm �û���Ȩ��
     * @param perm �жϵ�Ȩ��
     * @return ture/false
     */
    public static boolean comparePermissionString(String userPerm, String perm) {
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
     * jsonתToml
     * ���µĲ���ת����
     * 1��
     * {
     *   array: [
     *     {
     *         "key":"value"
     *     }
     *   ]
     * }
     * ����������JsonObject
     * 2��
     * {
     *   "\"": "a"
     * }
     * ����Ҫ�������ַ�
     * @param s json
     * @return toml
     */
    public static String toToml(String s) {
        Map<String,JSONObject> map = changeMapForEach(new JSONObject(s));
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger i = new AtomicInteger();
        map.forEach((key, value) -> {
            i.getAndIncrement();
            String a = value.toString().replaceAll(",","\n")
                    .replaceAll("\\{","").replaceAll("}","\n")
                    .replaceAll("\\[","[\n    ").replaceAll("]","\n]\n")
                    .replaceAll("\"([^\"]+)\":","$1 = ")
                    .replaceAll("\"\n\"","\",\n    \"");
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
        return String.valueOf(stringBuilder).replaceAll("\n\n","\n");

    }

    public static Map<String,JSONObject> changeMapForEach(JSONObject jsonObject) {
        return changeMapForEach(jsonObject,null);
    }

    /**
     * ��jsonObjectת��Ϊ����mapʵ��
     * [path/path]: {}
     * [path/p]: {}
     * @param jsonObject jsonObject
     * @param key ����
     * @return Map
     */
    public static Map<String,JSONObject> changeMapForEach(JSONObject jsonObject, String key) {
        HashMap<String, JSONObject> map = new HashMap<>();
        Set<String> keys = jsonObject.keySet();//��ȡ�����б�
        if (key == null) {
            map.put("\"", jsonObject);
        }
        for (String s : keys) {
            Object object = jsonObject.get(s);
            //�ж��Ƿ���ת��JSONObject
            if (object instanceof JSONObject json) {
                //���û�и�key�Ļ�
                if (key != null) {
                    if (!Objects.equals(key, "\"")) {
                        map.put(key + "." + s, json);
                        map.putAll(changeMapForEach(json, key + "." + s));//��������Ѱ��
                    }
                } else {
                    map.put(s, json);
                    map.putAll(changeMapForEach(json, s));//��������Ѱ��
                }
            }
        }
        //���û�и���
        //�жϸ���jsonObject�Ƿ��ظ�
        if (key == null) {
            //��¡mapѭ��
            ((HashMap<String, JSONObject>)map.clone()).forEach((k, value) -> {
                Set<String> keySet = value.keySet();//��ȡ��json�б�
                List<String> list = new ArrayList<>();
                HashMap<String, JSONObject> m =  (HashMap<String, JSONObject>)map.clone();//��¡
                m.remove(k);//�Ƴ�
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
