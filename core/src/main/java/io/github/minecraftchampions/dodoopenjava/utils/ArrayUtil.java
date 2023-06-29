package io.github.minecraftchampions.dodoopenjava.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数组工具类
 */
public class ArrayUtil {
    /**
     * 前置数组
     * @param arr 数组
     * @param element 前置的对象
     * @return 数组
     * @param <T> 泛型
     */
    public static <T> T[] prepend(T[] arr, T element) {
        int n = arr.length;
        arr = Arrays.copyOf(arr, n + 1);
        for (int i = arr.length - 1; i > 0; --i) {
            arr[i] = arr[i - 1];
        }
        arr[0] = element;
        return arr;
    }

    /**
     * 后置数组
     * @param arr 数组
     * @param element 后置的对象
     * @return 数组
     * @param <T> 泛型
     */
    public static <T> T[] append(T[] arr, T element) {
        int n = arr.length;
        arr = Arrays.copyOf(arr, n + 1);
        arr[n] = element;
        return arr;
    }

    /**
     * 前置数组
     * @param arr 数组
     * @param elements 前置的数组
     * @return 数组
     * @param <T> 泛型
     */
    public static <T> T[] prepend(T[] arr, T[] elements) {
        List<T> list = new ArrayList<>();
        T[] element = elements.clone();
        Collections.addAll(list, element);
        Collections.reverse(list); //颠倒数组

        list.toArray(element); //赋值
        for (T t : element) {
            arr = prepend(arr,t);
        }
        return arr;
    }

    /**
     * 后置数组
     * @param arr 数组
     * @param elements 后置的数组
     * @return 数组
     * @param <T> 泛型
     */
    public static <T> T[] append(T[] arr, T[] elements) {
        for (T t : elements) {
            System.out.println(t);
            arr = append(arr,t);
        }
        return arr;
    }
}
