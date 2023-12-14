package io.github.minecraftchampions.dodoopenjava.utils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 判断两个对象是否相等 等作用
 *
 * @param <T> 类型
 */
public class Equal<T> {
    /**
     * 对象
     */
    private final T value;

    /**
     * 是否相等
     */
    private Boolean equal = null;

    /**
     * 私有初始化
     *
     * @param value 对象
     */
    private Equal(T value) {
        this.value = value;
    }

    /**
     * 初始化
     *
     * @param value 对象
     * @return Equal
     * @throws NullPointerException 如果值是null
     */
    public static <T> Equal<T> of(T value) {
        return new Equal<>(Objects.requireNonNull(value));
    }

    /**
     * 获取对象
     * @return 存储的对象
     */
    public T get() {
        return this.value;
    }

    /**
     * 是否相等
     * @return 没进行判断就是null
     */
    public Boolean isEqual() {
        return equal;
    }

    /**
     * 判断
     * @param value 需要判断的对象
     * @return Equal
     */
    public Equal<T> equal(T value) {
        Equal<T> e = Equal.of(this.value);
        e.equal = Objects.equals(this.value, value);
        return e;
    }

    /**
     * 如果相等就运行
     * @param consumer consumer
     */
    public void ifEquals(Consumer<T> consumer) {
        if (equal == null) {
            throw new RuntimeException("没有进行判断就调用Equal#run");
        }
        if (equal) {
            consumer.accept(this.value);
        }
    }
}
