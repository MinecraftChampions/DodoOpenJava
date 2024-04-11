package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.ApiResultsLogger;
import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Island;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.command.CommandManager;
import io.github.minecraftchampions.dodoopenjava.event.AbstractEventTrigger;
import io.github.minecraftchampions.dodoopenjava.event.EventManager;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.WebSocketEventTrigger;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.message.card.CardMessage;
import io.github.minecraftchampions.dodoopenjava.message.text.TextMessage;
import io.github.minecraftchampions.dodoopenjava.permission.Permission;
import io.github.minecraftchampions.dodoopenjava.util.BaseUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 机器人实例
 *
 * @author qscbm187531
 */
@Getter
@ToString
@RequiredArgsConstructor
@Slf4j
public class BotImpl implements Bot {
    /**
     * 机器人唯一标识
     */
    @NonNull
    private final String clientId;

    /**
     * 机器人鉴权Token
     */
    @NonNull
    private final String token;

    @Override
    public boolean equals(Object object) {
        if (object instanceof Bot bot) {
            return this == bot || Objects.equals(bot.getAuthorization(), this.getAuthorization());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return clientId.hashCode() + token.hashCode();
    }

    /**
     * 事件管理器
     */
    private final EventManager eventManager = new EventManager();

    private final Api api = new Api(this);

    /**
     * 获取authorization
     *
     * @return authorization
     */
    @Override
    public String getAuthorization() {
        return BaseUtil.generateAuthorization(clientId, token);
    }

    /**
     * 启用日志记录器
     */
    @Override
    public void enableApiResultsLogger() {
        DodoOpenJava.enableApiResultsLogger(this);
    }

    /**
     * 卸载日志记录器
     */
    @Override
    public void disableApiResultsLogger() {
        DodoOpenJava.disableApiResultsLogger(this);
    }

    /**
     * 注册事件监听器
     *
     * @param listener 监听器
     */
    @Override
    public synchronized void registerListener(@NonNull Listener listener) {
        if (eventTrigger == null) {
            initEventListenSystem(new WebSocketEventTrigger(this));
        }
        if (!eventTrigger.isConnect()) {
            eventTrigger.start();
        }
        getEventManager().registerListener(listener);
    }

    /**
     * 移除事件监听器
     */
    @Override
    public synchronized void removeEventTrigger() {
        if (eventTrigger != null) {
            eventTrigger.close();
            eventTrigger = null;
        }
    }

    @Override
    public Island getIsland(@NonNull String islandSourceId) {
        Result result = getApi().V2.getIslandApi().getIslandList();
        if (result.isFailure()) {
            log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return null;
        }
        if (result.getJSONObjectData().toString().contains("\"islandSourceId\": \"" + islandSourceId + "\"")) {
            return new IslandImpl(islandSourceId, this);
        }
        return null;
    }

    /**
     * 注册事件监听器
     *
     * @param listeners 监听器
     */
    @Override
    public void registerListeners(@NonNull Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }

    /**
     * 注册命令
     *
     * @param commandExecutor 命令处理器
     */
    @Override
    public void registerCommand(@NonNull CommandExecutor commandExecutor) {
        commandManager.registerCommand(commandExecutor);
    }

    /**
     * 获取Api请求记录器
     *
     * @return ApiResultsLogger
     */
    @Override
    public ApiResultsLogger getApiResultsLogger() {
        ApiResultsLogger apiResultsLogger = DodoOpenJava.LOGGER_MAP.get(this.getAuthorization());
        if (apiResultsLogger != null) {
            return apiResultsLogger;
        } else {
            log.error("没有启用日志记录系统就调用Bot#getApiResultsLogger方法,bot:" + getAuthorization());
            return null;
        }
    }

    /**
     * 注册命令
     *
     * @param commandExecutors 命令处理器
     */
    @Override
    public void registerCommands(@NonNull CommandExecutor... commandExecutors) {
        for (CommandExecutor commandExecutor : commandExecutors) {
            registerCommand(commandExecutor);
        }
    }

    private final CommandManager commandManager = new CommandManager(this);

    private AbstractEventTrigger eventTrigger;

    /**
     * 传入一个EventTrigger(要先进行初始化)
     * 如果不传入默认WebSocket
     *
     * @param t EventTrigger
     */
    @Override
    public synchronized void initEventListenSystem(@NonNull AbstractEventTrigger t) {
        if (t.getBot() != this) {
            return;
        }
        if (eventTrigger != null) {
            eventTrigger.close();
        }
        eventTrigger = t;
    }

    /**
     * 卸载这个bot
     */
    @Override
    public void disable() {
        DodoOpenJava.disableBot(this);
    }

    /**
     * 获取机器人名字
     *
     * @return 名字
     */
    @Override
    public String getName() {
        return this.getApi().V2.botApi.getBotInfo().getJSONObjectData().getString("nickName");
    }

    /**
     * 获取机器人DodoId
     *
     * @return dodoId
     */
    @Override
    public String getDodoSourceId() {
        return this.getApi().V2.botApi.getBotInfo().getJSONObjectData().getString("dodoSourceId");
    }

    /**
     * 获取机器人头像URL
     *
     * @return url
     */
    @Override
    public String getAvatarUrl() {
        JSONObject jsonObject = new JSONObject();
        return this.getApi().V2.botApi.getBotInfo().getJSONObjectData().getString("avatarUrl");
    }

    /**
     * Api类
     */
    public static class Api {
        Api(BotImpl bot) {
            V2 = new V2(bot);
        }

        public final V2 V2;

        @Getter
        public static class V2 {
            private final Bot bot;

            V2(Bot bot) {
                this.bot = bot;
            }

            public final BotApi botApi = new BotApi();
            public final ChannelApi channelApi = new ChannelApi();
            public final ChannelArticleApi channelArticleApi = new ChannelArticleApi();
            public final ChannelMessageApi channelMessageApi = new ChannelMessageApi();
            public final ChannelVoiceApi channelVoiceApi = new ChannelVoiceApi();
            public final GiftApi giftApi = new GiftApi();
            public final IntegralApi integralApi = new IntegralApi();
            public final IslandApi islandApi = new IslandApi();
            public final MemberApi memberApi = new MemberApi();
            public final NtfApi ntfApi = new NtfApi();
            public final PersonalApi personalApi = new PersonalApi();
            public final ResourceApi resourceApi = new ResourceApi();
            public final RoleApi roleApi = new RoleApi();
            public final EventApi eventApi = new EventApi();


            public class BotApi {
                @SneakyThrows
                public Result getBotInfo() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.getBotInfo(bot.getAuthorization());
                }

                @SneakyThrows
                public Result addBotInvite(String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.addBotInvite(bot.getAuthorization(), dodoSourceId);
                }

                @SneakyThrows
                public Result getBotInviteList(int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.getBotInviteList(bot.getAuthorization(), pageSize, maxId);
                }

                @SneakyThrows
                public Result removeBotInvite(String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.removeBotInvite(bot.getAuthorization(), dodoSourceId);
                }

                @SneakyThrows
                public Result setBotIslandLeave(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.setBotIslandLeave(bot.getAuthorization(), islandSourceId);
                }
            }

            public class ChannelApi {
                @SneakyThrows
                public Result editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public Result addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public Result getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public Result getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public Result deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }
            }

            public class ChannelArticleApi {
                @SneakyThrows
                public Result removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public Result addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }
            }

            public class ChannelMessageApi {
                @SneakyThrows
                public Result setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public Result sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public Result sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public Result getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public Result sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public Result sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public Result sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public Result sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public Result sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public Result withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public Result withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public Result addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public Result editChannelCardMessage(String messageId, CardMessage messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public Result removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public Result removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public Result editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public Result sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public Result sendTextMessage(String channelId, TextMessage textMessage) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, textMessage);
                }

                @SneakyThrows
                public Result referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public Result sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public Result sendCardMessage(String channelId, CardMessage messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public Result getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public Result sendMessage(String channelId, Message messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendMessage(bot.getAuthorization(), channelId, messageBody);
                }
            }

            public class ChannelVoiceApi {
                @SneakyThrows
                public Result getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);

                }

                @SneakyThrows
                public Result moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);

                }

                @SneakyThrows
                public Result editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);

                }
            }

            public class EventApi {
                @SneakyThrows
                public Result getWebSocketConnection() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.EventApi.getWebSocketConnection(bot.getAuthorization());
                }
            }

            public class GiftApi {
                @SneakyThrows
                public Result getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public Result getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public Result getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public Result getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public Result getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }
            }

            public class IntegralApi {
                @SneakyThrows
                public Result getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public Result setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }
            }

            public class IslandApi {
                @SneakyThrows
                public Result getIslandLevelRankList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandLevelRankList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public Result getIslandInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public Result getIslandList() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandList(bot.getAuthorization());
                }

                @SneakyThrows
                public Result getIslandMuteList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandMuteList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public Result getIslandBanList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandBanList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }
            }

            public class MemberApi {
                @SneakyThrows
                public Result getMemberInvitationInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInvitationInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public Result addMemberReasonMute(String islandSourceId, String dodoSourceId, int duration, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration, reason);
                }

                @SneakyThrows
                public Result addMemberChannelBan(String islandSourceId, String dodoSourceId, String noticeChannelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberChannelBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId);
                }

                @SneakyThrows
                public Result removeMemberMute(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public Result addMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public Result addMemberBan(String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId, reason);
                }

                @SneakyThrows
                public Result getMemberInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public User getUser(String islandSourceId, String dodoSourceId) {
                    return new DodoUserImpl(dodoSourceId, islandSourceId, bot);
                }

                @SneakyThrows
                public Result editMemberNickName(String islandSourceId, String dodoSourceId, String nickName) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.editMemberNickName(bot.getAuthorization(), islandSourceId, dodoSourceId, nickName);
                }

                @SneakyThrows
                public Result getMemberRoleList(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberRoleList(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public Result addMemberMute(String islandSourceId, String dodoSourceId, int duration) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration);
                }

                @SneakyThrows
                public Result getMemberList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public Result removeMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public Result addMemberReasonBan(String islandSourceId, String dodoSourceId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonBan(bot.getAuthorization(), islandSourceId, dodoSourceId, reason);
                }

                @SneakyThrows
                public Result addMemberReasonChannelBan(String islandSourceId, String dodoSourceId, String reason, String noticeChannelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonChannelBan(bot.getAuthorization(), islandSourceId, dodoSourceId, reason, noticeChannelId);
                }


                @SneakyThrows
                public Result getMemberDodoIdMapList(List<String> strList) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberDodoIdMapList(bot.getAuthorization(), strList);
                }
            }

            public class NtfApi {
                @SneakyThrows
                public Result getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform, String issuer, String series) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NtfApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform, issuer, series);
                }


                @SneakyThrows
                public Result getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NtfApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform);
                }
            }

            public class ResourceApi {
                @SneakyThrows
                public Result uploadResource(String path) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ResourceApi.uploadResource(bot.getAuthorization(), path);
                }
            }

            public class PersonalApi {
                @SneakyThrows
                public Result sendPersonalMessage(String islandSourceId, String dodoSourceId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendPersonalMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, message);
                }

                @SneakyThrows
                public Result sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height);
                }

                @SneakyThrows
                public Result sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height, isOriginal);
                }

                @SneakyThrows
                public Result sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, coverUrl, duration, size);
                }

                @SneakyThrows
                public Result sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u);
                }

                @SneakyThrows
                public Result sendMessage(String islandSourceId, String dodoSourceId, Message message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, message);
                }
            }

            public class RoleApi {
                @SneakyThrows
                public Result editRole(String islandSourceId, String roleId, String roleName, String roleColor, int position, String permission) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.editRole(bot.getAuthorization(), islandSourceId, roleId, roleName, roleColor, position, permission);
                }

                @SneakyThrows
                public Result editRole(String islandSourceId, String roleId, String roleName, String roleColor, int position, Permission permission) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.editRole(bot.getAuthorization(), islandSourceId, roleId, roleName, roleColor, position, permission);
                }

                @SneakyThrows
                public Result addRoleMember(String islandSourceId, String dodoSourceId, String roleId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.addRoleMember(bot.getAuthorization(), islandSourceId, dodoSourceId, roleId);
                }

                @SneakyThrows
                public Result getRoleList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.getRoleList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public Result removeRoleMember(String islandSourceId, String dodoSourceId, String roleId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.removeRoleMember(bot.getAuthorization(), islandSourceId, dodoSourceId, roleId);
                }

                @SneakyThrows
                public Result addRole(String islandSourceId, String roleName, String roleColor, int position, String permission) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.addRole(bot.getAuthorization(), islandSourceId, roleName, roleColor, position, permission);
                }

                @SneakyThrows
                public Result addRole(String islandSourceId, String roleName, String roleColor, int position, Permission permission) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.addRole(bot.getAuthorization(), islandSourceId, roleName, roleColor, position, permission);
                }

                @SneakyThrows
                public Result deleteRole(String islandSourceId, String roleId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.deleteRole(bot.getAuthorization(), islandSourceId, roleId);
                }

                @SneakyThrows
                public Result getMemberList(String islandSourceId, String roleId, int pageSize, int maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.getMemberList(bot.getAuthorization(), islandSourceId, roleId, pageSize, maxId);
                }
            }
        }
    }
}