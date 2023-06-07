package io.github.minecraftchampions.dodoopenjava.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ���鹤����
 */
public class ArrayUtil {
    /**
     * ǰ������
     * @param arr ����
     * @param element ǰ�õĶ���
     * @return ����
     * @param <T> ����
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
     * ��������
     * @param arr ����
     * @param element ���õĶ���
     * @return ����
     * @param <T> ����
     */
    public static <T> T[] append(T[] arr, T element) {
        int n = arr.length;
        arr = Arrays.copyOf(arr, n + 1);
        arr[n] = element;
        return arr;
    }

    /**
     * ǰ������
     * @param arr ����
     * @param elements ǰ�õ�����
     * @return ����
     * @param <T> ����
     */
    public static <T> T[] prepend(T[] arr, T[] elements) {
        List<T> list = new ArrayList<>();
        T[] element = elements.clone();
        Collections.addAll(list, element);
        Collections.reverse(list); //�ߵ�����

        list.toArray(element); //��ֵ
        for (T t : element) {
            arr = prepend(arr,t);
        }
        return arr;
    }

    /**
     * ��������
     * @param arr ����
     * @param elements ���õ�����
     * @return ����
     * @param <T> ����
     */
    public static <T> T[] append(T[] arr, T[] elements) {
        for (T t : elements) {
            System.out.println(t);
            arr = append(arr,t);
        }
        return arr;
    }
}
