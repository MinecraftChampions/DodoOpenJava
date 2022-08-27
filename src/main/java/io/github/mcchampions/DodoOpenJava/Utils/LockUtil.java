package io.github.mcchampions.DodoOpenJava.Utils;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 资源锁
 */
public class LockUtil {
    /**
     * 初始化ConcurrentHashMap锁载体
     */
    private static final ConcurrentHashMap<String, AtomicInteger> STRING_LOCK_MAP = new ConcurrentHashMap<>();

    /**
     * 初始化ConcurrentHashMap锁载体
     */
    private static final ConcurrentHashMap<UUID, AtomicInteger> UUID_LOCK_MAP = new ConcurrentHashMap<>();

    /**
     * 加锁
     *
     * @param key 锁key
     * @return AtomicInteger
     */
    public static AtomicInteger lock(String key) {
        // 当实体ID锁资源为空,初始化锁
        if (STRING_LOCK_MAP.get(key) == null) {
            // 初始化一个竞争数为0的原子资源
            STRING_LOCK_MAP.putIfAbsent(key, new AtomicInteger(0));
        }
        // 线程得到该资源,原子性+1
        STRING_LOCK_MAP.get(key).incrementAndGet();
        // 返回该ID资源锁
        return STRING_LOCK_MAP.get(key);
    }

    /**
     * 释放锁
     *
     * @param key key
     */
    public static void unLock(String key) {
        // 当实体ID资源不为空,才可以操作锁,防止抛出空指针异常
        if (STRING_LOCK_MAP.get(key) != null) {
            // 线程释放该资源,原子性-1
            int source = STRING_LOCK_MAP.get(key).decrementAndGet();
            // 当资源没有线程竞争的时候，就删除掉该锁,防止内存溢出
            if (source <= 0) {
                STRING_LOCK_MAP.remove(key);
            }
        }
    }

    /**
     * 加锁
     *
     * @param key 锁key
     * @return AtomicInteger
     */
    public static AtomicInteger lock(UUID key) {
        // 当实体ID锁资源为空,初始化锁
        if (UUID_LOCK_MAP.get(key) == null) {
            // 初始化一个竞争数为0的原子资源
            UUID_LOCK_MAP.putIfAbsent(key, new AtomicInteger(0));
        }
        // 线程得到该资源,原子性+1
        UUID_LOCK_MAP.get(key).incrementAndGet();
        // 返回该ID资源锁
        return UUID_LOCK_MAP.get(key);
    }

    /**
     * 释放锁
     *
     * @param key key
     */
    public static void unLock(UUID key) {
        // 当实体ID资源不为空,才可以操作锁,防止抛出空指针异常
        if (UUID_LOCK_MAP.get(key) != null) {
            // 线程释放该资源,原子性-1
            int source = UUID_LOCK_MAP.get(key).decrementAndGet();
            // 当资源没有线程竞争的时候，就删除掉该锁,防止内存溢出
            if (source <= 0) {
                UUID_LOCK_MAP.remove(key);
            }
        }
    }
}
