package io.github.minecraftchampions.dodoopenjava.utils;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 资源锁
 * @author qscbm187531
 */
public class LockUtil {
    private static final ConcurrentHashMap<String, AtomicInteger> STRING_LOCK_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<UUID, AtomicInteger> UUID_LOCK_MAP = new ConcurrentHashMap<>();

    /**
     * 加锁
     *
     * @param key 锁key
     * @return AtomicInteger
     */
    public static AtomicInteger lock(String key) {
        if (STRING_LOCK_MAP.get(key) == null) {
            STRING_LOCK_MAP.putIfAbsent(key, new AtomicInteger(0));
        }
        STRING_LOCK_MAP.get(key).incrementAndGet();
        return STRING_LOCK_MAP.get(key);
    }

    /**
     * 释放锁
     *
     * @param key key
     */
    public static void unLock(String key) {
        if (STRING_LOCK_MAP.get(key) != null) {
            int source = STRING_LOCK_MAP.get(key).decrementAndGet();
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
        if (UUID_LOCK_MAP.get(key) == null) {
            UUID_LOCK_MAP.putIfAbsent(key, new AtomicInteger(0));
        }
        UUID_LOCK_MAP.get(key).incrementAndGet();
        return UUID_LOCK_MAP.get(key);
    }

    /**
     * 释放锁
     *
     * @param key key
     */
    public static void unLock(UUID key) {
        if (UUID_LOCK_MAP.get(key) != null) {
            int source = UUID_LOCK_MAP.get(key).decrementAndGet();
            if (source <= 0) {
                UUID_LOCK_MAP.remove(key);
            }
        }
    }
}
