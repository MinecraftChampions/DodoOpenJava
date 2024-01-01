package io.github.minecraftchampions.dodoopenjava;

import io.github.minecraftchampions.dodoopenjava.utils.DateUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 记录API调用的日志
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResultsLogger {
    @NonNull
    private final String botAuthorization;

    private final ConcurrentSkipListMap<Long, LinkedHashSet<Result>> resultsLogMap = new ConcurrentSkipListMap<>();

    public void addResult(@NonNull Result result) {
        long timestamp = result.getTimestamp();
        Set<Result> results = resultsLogMap.get(timestamp);
        if (results == null) {
            resultsLogMap.put(timestamp, new LinkedHashSet<>(Set.of(result)));
            return;
        }
        synchronized (results) {
            results.add(result);
        }
    }

    public ConcurrentSkipListMap<Long, LinkedHashSet<Result>> getAllLogs() {
        return getLogs(null, null);
    }

    public ConcurrentSkipListMap<Long, LinkedHashSet<Result>> getLogs(Date startDate, Date endDate) {
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
        return getLogs(startTimestamp,endTimestamp);
    }

    public ConcurrentSkipListMap<Long, LinkedHashSet<Result>> getLogs(long startTimestamp, long endTimestamp) {
        Map<Long, LinkedHashSet<Result>> subMap = resultsLogMap.subMap(startTimestamp, true, endTimestamp, true);
        ConcurrentSkipListMap<Long, LinkedHashSet<Result>> deepCopiedMap = new ConcurrentSkipListMap<>();
        for (Map.Entry<Long, LinkedHashSet<Result>> entry : subMap.entrySet()) {
            deepCopiedMap.put(entry.getKey(), (LinkedHashSet<Result>)entry.getValue().clone());
        }
        return deepCopiedMap;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        resultsLogMap.forEach((timestamp, results) -> {
            String date = DateUtil.format(DateUtil.timestampToDate(timestamp), DateUtil.Format_Three);
            results.forEach((result -> sb.append("[").append(date).append("]").append(" ").append(result.getData()).append("\n")));
        });
        return sb.toString().trim();
    }
}
