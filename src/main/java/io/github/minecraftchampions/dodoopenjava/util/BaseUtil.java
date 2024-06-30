package io.github.minecraftchampions.dodoopenjava.util;

import io.github.minecraftchampions.dodoopenjava.debug.Result;
import lombok.NonNull;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * 一些常用的方法
 *
 * @author qscbm187531
 */
public class BaseUtil {
    /**
     * 拼接 Authorization
     *
     * @param clientId 机器人唯一标示
     * @param token    机器人鉴权Token
     * @return 返回拼接后的文本
     */
    public static String generateAuthorization(@NonNull String clientId, @NonNull String token) {
        return "Bot " + clientId + "." + token;
    }

    /**
     * 判断返回码的意义
     *
     * @param status 返回码
     * @return 意思
     */
    public static String getStatus(int status) {
        return Result.STATUS_CODE.getString(String.valueOf(status));
    }

    /**
     * Object集合转String集合
     *
     * @param list Object集合
     * @return String集合
     */
    public static List<String> toStringList(@NonNull List<Object> list) {
        List<String> returnList = new ArrayList<>();
        for (Object o : list) {
            returnList.add(o.toString());
        }
        return returnList;
    }

    /**
     * 替换特殊字符
     *
     * @param text 要替换的字符
     * @return str
     */
    public static String replaceXmlSpecialCharacters(@NonNull String text) {
        return text.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
    }

    /**
     * 获取一个节点里的所有文本节点
     *
     * @param node 节点
     * @return 文本节点集合
     */
    public static List<Node> getAllTextNodes(@NonNull Node node) {
        NodeList nodeList = node.getChildNodes();
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            if ("#text".equals(child.getNodeName())) {
                result.add(child);
            } else {
                List<Node> childTextNodes = getAllTextNodes(child);
                result.addAll(childTextNodes);
            }
        }
        return result;
    }

    /**
     * 转Object转为List
     *
     * @param obj   对象
     * @param clazz List包含的对象的类
     * @param <T>   泛型
     * @return List<T>
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}