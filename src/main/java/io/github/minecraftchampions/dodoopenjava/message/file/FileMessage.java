package io.github.minecraftchampions.dodoopenjava.message.file;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

/**
 * 文件消息组件
 */
@RequiredArgsConstructor
public class FileMessage extends Message {
    @NonNull
    private String url;

    @NonNull
    private String name;

    @NonNull
    private long size;

    @Override
    public JSONObject toMessage() {
        return null;
    }

    @Override
    public int getType() {
        return 5;
    }

    /**
     * 设置文件链接
     *
     * @param url 链接
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    public void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     * 设置文件大小
     *
     * @param size 文件大小
     */
    public void setSize(long size) {
        this.size = size;
    }
}
