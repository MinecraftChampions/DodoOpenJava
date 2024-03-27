package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.message.Message;

/**
 * 频道
 */
public interface Channel {
    /**
     * 编辑频道名
     *
     * @param name 名
     */
    Result editChannelName(String name);

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
    String getIslandId();

    /**
     * 获取类型
     *
     * @return type
     */
    int getChannelType();


    /**
     * 获取默认访问频道标识，0：否，1：是
     *
     * @return type
     */
    int getDefaultFlag();

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
     * 创建频道
     */
    Result create();

    /**
     * 删除频道
     */
    Result delete();
}
