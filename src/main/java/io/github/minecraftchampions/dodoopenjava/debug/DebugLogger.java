package io.github.minecraftchampions.dodoopenjava.debug;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Slf4j
@Getter
public class DebugLogger {
    @NonNull
    private final String botAuthorization;

    /**
     * 记录api执行结果
     *
     * @param result result
     */
    public void log(@NonNull Result result) {
        log("{} 调用api结果: {}", botAuthorization, result);
    }

    /**
     * 记录事件
     *
     * @param event event
     */
    public void log(@NonNull Event event) {
        log("{} 监听事件: {}", botAuthorization, event);
    }

    public static void log(@NonNull String str, Object... params) {
        log.debug(str,params);
    }

    public static void log(@NonNull String str) {
        log.debug(str);
    }
}
