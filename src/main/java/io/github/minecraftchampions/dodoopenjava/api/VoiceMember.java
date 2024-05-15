package io.github.minecraftchampions.dodoopenjava.api;

public interface VoiceMember extends User {
    /**
     * 获取语音频道id
     *
     * @return id
     */
    String getChannelId();

    /**
     * 麦克风是否开启
     *
     * @return true/false
     */
    default boolean isSpeaking() {
        return getMicStatus() == 1;
    }

    /**
     * 获取语音输出状态
     *
     * @return 状态
     */
    int getSpkStatus();

    /**
     * 获取语音输入状态
     *
     * @return 状态
     */
    int getMicStatus();


    /**
     * 扬声器是否开启
     *
     * @return true/false
     */
    default boolean isListening() {
        return getSpkStatus() == 1;
    }

    /**
     * 获取麦序模式状态
     *
     * @return 状态
     */
    int getMicSortStatus();
}
