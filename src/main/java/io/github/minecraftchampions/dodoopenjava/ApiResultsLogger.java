package io.github.minecraftchampions.dodoopenjava;

import io.github.minecraftchampions.dodoopenjava.util.DateUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 记录API调用的日志
 *
 * @author qscbm187531
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResultsLogger {
    @NonNull
    private final String botAuthorization;

    private final ConcurrentSkipListMap<Long, Set<Result>> resultsLogMap = new ConcurrentSkipListMap<>();

    /**
     * 添加API执行结果记录
     *
     * @param result result
     */
    public void addResult(@NonNull Result result) {
        CompletableFuture.runAsync(() -> {
            long timestamp = result.getTimestamp();
            Set<Result> results = resultsLogMap.computeIfAbsent(timestamp, k -> new ConcurrentSkipListSet<>());
            results.add(result);
        });
    }

    /**
     * 获取日志
     *
     * @return 日志
     */
    public CompletableFuture<Map<Long, Set<Result>>> getAllLogs() {
        return getLogs((Date) null, null);
    }

    /**
     * 获取指定时间段到另一个指定时间段的日志
     *
     * @param startDate 开始
     * @param endDate   结束
     * @return 日志
     */
    public CompletableFuture<Map<Long, Set<Result>>> getLogs(Date startDate, Date endDate) {
        long startTimestamp = startDate == null ? 0L : startDate.getTime();
        long endTimestamp = endDate == null ? Long.MAX_VALUE : endDate.getTime();
        return getLogs(startTimestamp, endTimestamp);
    }

    /**
     * 获取指定时间段到另一个指定时间段的日志
     *
     * @param start 开始(样式:yyyy-MM-dd HH:mm:ss)
     * @param end   结束(样式:yyyy-MM-dd HH:mm:ss)
     * @return 日志
     */
    public CompletableFuture<Map<Long, Set<Result>>> getLogs(@NonNull String start, @NonNull String end) {
        Date startDate = DateUtil.parse(start, DateUtil.FORMAT_THREE);
        Date endDate = DateUtil.parse(end, DateUtil.FORMAT_THREE);
        return getLogs(startDate, endDate);
    }

    /**
     * 获取指定时间段到另一个指定时间段的日志
     *
     * @param startTimestamp 开始
     * @param endTimestamp   结束
     * @return 日志
     */
    public CompletableFuture<Map<Long, Set<Result>>> getLogs(long startTimestamp, long endTimestamp) {
        return CompletableFuture.supplyAsync(() -> {
            Map<Long, Set<Result>> subMap = resultsLogMap.subMap(startTimestamp, true, endTimestamp, true);
            Map<Long, Set<Result>> deepCopiedMap = new ConcurrentSkipListMap<>();
            for (Map.Entry<Long, Set<Result>> entry : subMap.entrySet()) {
                deepCopiedMap.put(entry.getKey(), new ConcurrentSkipListSet<>(entry.getValue()));
            }
            return deepCopiedMap;
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        resultsLogMap.forEach((timestamp, results) -> {
            String date = DateUtil.format(DateUtil.timestampToDate(timestamp), DateUtil.FORMAT_THREE);
            results.forEach((result -> sb.append("[").append(date).append("]").append(" ").append(result).append("\n")));
        });
        return sb.toString().trim();
    }
}