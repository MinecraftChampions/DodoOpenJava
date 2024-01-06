package io.github.minecraftchampions.dodoopenjava;

import io.github.minecraftchampions.dodoopenjava.utils.DateUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 记录API调用的日志
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResultsLogger {
    @NonNull
    private final String botAuthorization;

    private final ConcurrentSkipListMap<Long, LinkedHashSet<Result>> resultsLogMap = new ConcurrentSkipListMap<>();

    /**
     * 添加API执行结果记录
     *
     * @param result result
     */
    public void addResult(@NonNull Result result) {
        CompletableFuture.runAsync(() -> {
            long timestamp = result.getTimestamp();
            Set<Result> results = resultsLogMap.get(timestamp);
            if (results == null) {
                resultsLogMap.put(timestamp, new LinkedHashSet<>(Set.of(result)));
                return;
            }
            synchronized (results) {
                results.add(result);
            }
        });
    }

    /**
     * 获取日志
     *
     * @return 日志
     */
    public CompletableFuture<Map<Long, LinkedHashSet<Result>>> getAllLogs() {
        return getLogs(null, null);
    }

    /**
     * 获取指定时间段到另一个指定时间段的日志
     *
     * @param startDate 开始
     * @param endDate   结束
     * @return 日志
     */
    public CompletableFuture<Map<Long, LinkedHashSet<Result>>> getLogs(Date startDate, Date endDate) {
        long startTimestamp;
        long endTimestamp;
        if (startDate == null) {
            startTimestamp = 0L;
        } else {
            startTimestamp = startDate.getTime();
        }
        if (endDate == null) {
            endTimestamp = Long.MAX_VALUE;
        } else {
            endTimestamp = endDate.getTime();
        }
        return getLogs(startTimestamp, endTimestamp);
    }

    /**
     * 获取指定时间段到另一个指定时间段的日志
     *
     * @param startTimestamp 开始
     * @param endTimestamp   结束
     * @return 日志
     */
    public CompletableFuture<Map<Long, LinkedHashSet<Result>>> getLogs(long startTimestamp, long endTimestamp) {
        return CompletableFuture.supplyAsync(() -> {
            Map<Long, LinkedHashSet<Result>> subMap = resultsLogMap.subMap(startTimestamp, true, endTimestamp, true);
            ConcurrentSkipListMap<Long, LinkedHashSet<Result>> deepCopiedMap = new ConcurrentSkipListMap<>();
            for (Map.Entry<Long, LinkedHashSet<Result>> entry : subMap.entrySet()) {
                deepCopiedMap.put(entry.getKey(), (LinkedHashSet<Result>) entry.getValue().clone());
            }
            return deepCopiedMap;
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        resultsLogMap.forEach((timestamp, results) -> {
            String date = DateUtil.format(DateUtil.timestampToDate(timestamp), DateUtil.Format_Three);
            results.forEach((result -> sb.append("[").append(date).append("]").append(" ").append(result).append("\n")));
        });
        return sb.toString().trim();
    }
}
