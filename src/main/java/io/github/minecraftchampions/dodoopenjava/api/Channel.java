package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.Result;
import lombok.NonNull;

/**
 * 频道
 */
public interface Channel {
    /**
     * 编辑频道名
     *
     * @param name 名
     */
    Result editChannelName(@NonNull String name);

    /**
     * 获取频道名
     *
     * @return 名
     */
    String getChannelName();

    /**
     * 获取ID
     *
     * @return id
     */
    String getChannelId();

    /**
     * 获取群id
     *
     * @return id
     */
    String getIslandSourceId();

    /**
     * 获取类型
     *
     * @return type
     */
    ChannelType getChannelType();


    /**
     * 获取默认访问频道标识，0：否，1：是
     *
     * @return type
     */
    int getDefaultFlag();

    /**
     * 是否为默认访问频道
     *
     * @return type
     */
    default boolean isDefaultChannel() {
        return getDefaultFlag() == 1;
    }

    /**
     * 获取分组ID
     *
     * @return id
     */
    String getGroupId();

    /**
     * 获取分组名
     *
     * @return name
     */
    String getGroupName();

    /**
     * 删除频道
     */
    Result delete();

    /**
     * 获取超级群
     *
     * @return 超级群
     */
    Island getIsland();

    Bot getBot();
}
