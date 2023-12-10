package io.github.minecraftchampions.dodoopenjava;

import io.github.minecraftchampions.dodoopenjava.card.Card;
import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.command.CommandManager;
import io.github.minecraftchampions.dodoopenjava.event.EventManager;
import io.github.minecraftchampions.dodoopenjava.event.EventTrigger;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.WebSocketEventTrigger;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.File;

/**
 * 机器人实例
 */
@Getter
public class Bot {
    /**
     * 机器人唯一标识
     */
    private final String clientId;

    /**
     * 机器人鉴权Token
     */
    private final String token;

    /**
     * 事件管理器
     */
    private final EventManager eventManager = new EventManager();

    private final Api api = new Api(this);

    public Bot(String clientId, String token) {
        this.clientId = clientId;
        this.token = token;
    }

    /**
     * 获取authorization
     *
     * @return authorization
     */
    public String getAuthorization() {
        return BaseUtil.Authorization(clientId, token);
    }

    /**
     * 注册事件监听器
     *
     * @param listener 监听器
     */
    public void registerListener(Listener listener) {
        if (eventTrigger == null) {
            initEventListenSystem(new WebSocketEventTrigger(this));
        }
        if (!eventTrigger.isConnect()) {
            eventTrigger.start();
        }
        getEventManager().registerListener(listener);
    }

    /**
     * 注册事件监听器
     *
     * @param listeners 监听器
     */
    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }

    /**
     * 注册命令
     *
     * @param commandExecutor 命令处理器
     */
    public void registerCommand(CommandExecutor commandExecutor) {
        commandManager.registerCommand(commandExecutor);
    }

    /**
     * 注册命令
     *
     * @param commandExecutors 命令处理器
     */
    public void registerCommands(CommandExecutor... commandExecutors) {
        for (CommandExecutor commandExecutor : commandExecutors) {
            registerCommand(commandExecutor);
        }
    }

    private final CommandManager commandManager = new CommandManager(this);

    private EventTrigger eventTrigger;

    /**
     * 传入一个EventTrigger(要先进行初始化)
     * 如果不传入默认WebSocket
     *
     * @param t EventTrigger
     */
    public void initEventListenSystem(EventTrigger t) {
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
    public void disable() {
        commandManager.unregisterAllCommands();
        eventManager.unregisterAllListeners();
        if (eventTrigger != null) {
            eventTrigger.close();
        }
        eventTrigger = null;
        DodoOpenJava.getBots().remove(this);
    }

    public class Api {
        Api(Bot bot) {
            V2 = new V2(bot);
        }

        public final V2 V2;

        @Getter
        public class V2 {
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
            public final NTFApi ntfApi = new NTFApi();
            public final PersonalApi personalApi = new PersonalApi();
            public final ResourceApi resourceApi = new ResourceApi();
            public final RoleApi roleApi = new RoleApi();



            public class BotApi {
                @SneakyThrows
                public JSONObject getBotInfo() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.getBotInfo(bot.getAuthorization());
                }

                @SneakyThrows
                public JSONObject setBotIslandLeave(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.setBotIslandLeave(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getBotInviteList(int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.getBotInviteList(bot.getAuthorization(), pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject addBotInvite(String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.addBotInvite(bot.getAuthorization(), dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeBotInvite(String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.BotApi.removeBotInvite(bot.getAuthorization(), dodoSourceId);
                }
            }

            public class ChannelApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

            }

            public class ChannelArticleApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

            }

            public class ChannelMessageApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

            }

            public class ChannelVoiceApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

            }

            public class GiftApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

            }

            public class IntegralApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }

            }

            public class IslandApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }

                @SneakyThrows
                public JSONObject getIslandLevelRankList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandLevelRankList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getIslandList() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandList(bot.getAuthorization());
                }

                @SneakyThrows
                public JSONObject getIslandBanList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandBanList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandMuteList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandMuteList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandInfo(bot.getAuthorization(), islandSourceId);
                }

            }

            public class MemberApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }

                @SneakyThrows
                public JSONObject getIslandLevelRankList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandLevelRankList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getIslandList() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandList(bot.getAuthorization());
                }

                @SneakyThrows
                public JSONObject getIslandBanList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandBanList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandMuteList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandMuteList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonMute(String islandSourceId, String dodoSourceId, int duration, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration, reason);
                }

                @SneakyThrows
                public JSONObject getMemberInvitationInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInvitationInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberChannelBan(String islandSourceId, String dodoSourceId, String noticeChannelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberChannelBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId);
                }

                @SneakyThrows
                public JSONObject removeMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId, reason);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberMute(String islandSourceId, String dodoSourceId, int duration) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration);
                }

                @SneakyThrows
                public JSONObject editMemberNickName(String islandSourceId, String dodoSourceId, String nickName) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.editMemberNickName(bot.getAuthorization(), islandSourceId, dodoSourceId, nickName);
                }

                @SneakyThrows
                public JSONObject getMemberRoleList(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberRoleList(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonBan(String islandSourceId, String dodoSourceId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonBan(bot.getAuthorization(), islandSourceId, dodoSourceId, reason);
                }

                @SneakyThrows
                public JSONObject removeMemberMute(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

            }

            public class NTFApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }

                @SneakyThrows
                public JSONObject getIslandLevelRankList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandLevelRankList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getIslandList() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandList(bot.getAuthorization());
                }

                @SneakyThrows
                public JSONObject getIslandBanList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandBanList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandMuteList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandMuteList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonMute(String islandSourceId, String dodoSourceId, int duration, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration, reason);
                }

                @SneakyThrows
                public JSONObject getMemberInvitationInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInvitationInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberChannelBan(String islandSourceId, String dodoSourceId, String noticeChannelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberChannelBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId);
                }

                @SneakyThrows
                public JSONObject removeMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId, reason);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberMute(String islandSourceId, String dodoSourceId, int duration) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration);
                }

                @SneakyThrows
                public JSONObject editMemberNickName(String islandSourceId, String dodoSourceId, String nickName) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.editMemberNickName(bot.getAuthorization(), islandSourceId, dodoSourceId, nickName);
                }

                @SneakyThrows
                public JSONObject getMemberRoleList(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberRoleList(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonBan(String islandSourceId, String dodoSourceId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonBan(bot.getAuthorization(), islandSourceId, dodoSourceId, reason);
                }

                @SneakyThrows
                public JSONObject removeMemberMute(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform, String issuer, String series) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform, issuer, series);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform);
                }

            }

            public class PersonalApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }

                @SneakyThrows
                public JSONObject getIslandLevelRankList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandLevelRankList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getIslandList() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandList(bot.getAuthorization());
                }

                @SneakyThrows
                public JSONObject getIslandBanList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandBanList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandMuteList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandMuteList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonMute(String islandSourceId, String dodoSourceId, int duration, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration, reason);
                }

                @SneakyThrows
                public JSONObject getMemberInvitationInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInvitationInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberChannelBan(String islandSourceId, String dodoSourceId, String noticeChannelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberChannelBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId);
                }

                @SneakyThrows
                public JSONObject removeMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId, reason);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberMute(String islandSourceId, String dodoSourceId, int duration) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration);
                }

                @SneakyThrows
                public JSONObject editMemberNickName(String islandSourceId, String dodoSourceId, String nickName) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.editMemberNickName(bot.getAuthorization(), islandSourceId, dodoSourceId, nickName);
                }

                @SneakyThrows
                public JSONObject getMemberRoleList(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberRoleList(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonBan(String islandSourceId, String dodoSourceId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonBan(bot.getAuthorization(), islandSourceId, dodoSourceId, reason);
                }

                @SneakyThrows
                public JSONObject removeMemberMute(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform, String issuer, String series) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform, issuer, series);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform);
                }

                @SneakyThrows
                public JSONObject sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height);
                }

                @SneakyThrows
                public JSONObject sendPersonalMessage(String islandSourceId, String dodoSourceId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendPersonalMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, message);
                }

                @SneakyThrows
                public JSONObject sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u);
                }

            }

            public class ResourceApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }

                @SneakyThrows
                public JSONObject getIslandLevelRankList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandLevelRankList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getIslandList() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandList(bot.getAuthorization());
                }

                @SneakyThrows
                public JSONObject getIslandBanList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandBanList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandMuteList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandMuteList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonMute(String islandSourceId, String dodoSourceId, int duration, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration, reason);
                }

                @SneakyThrows
                public JSONObject getMemberInvitationInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInvitationInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberChannelBan(String islandSourceId, String dodoSourceId, String noticeChannelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberChannelBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId);
                }

                @SneakyThrows
                public JSONObject removeMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId, reason);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberMute(String islandSourceId, String dodoSourceId, int duration) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration);
                }

                @SneakyThrows
                public JSONObject editMemberNickName(String islandSourceId, String dodoSourceId, String nickName) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.editMemberNickName(bot.getAuthorization(), islandSourceId, dodoSourceId, nickName);
                }

                @SneakyThrows
                public JSONObject getMemberRoleList(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberRoleList(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonBan(String islandSourceId, String dodoSourceId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonBan(bot.getAuthorization(), islandSourceId, dodoSourceId, reason);
                }

                @SneakyThrows
                public JSONObject removeMemberMute(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform, String issuer, String series) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform, issuer, series);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform);
                }

                @SneakyThrows
                public JSONObject sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height);
                }

                @SneakyThrows
                public JSONObject sendPersonalMessage(String islandSourceId, String dodoSourceId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendPersonalMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, message);
                }

                @SneakyThrows
                public JSONObject sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u);
                }

                @SneakyThrows
                public JSONObject uploadResource(String path) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ResourceApi.uploadResource(bot.getAuthorization(), path);
                }

            }

            public class RoleApi {
                @SneakyThrows
                public JSONObject getChannelInfo(String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelInfo(bot.getAuthorization(), channelId);
                }

                @SneakyThrows
                public JSONObject addChannel(String islandSourceId, String channelName, int channelType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.addChannel(bot.getAuthorization(), islandSourceId, channelName, channelType);
                }

                @SneakyThrows
                public JSONObject getChannelList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.getChannelList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject editChannel(String islandSourceId, String channelName, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.editChannel(bot.getAuthorization(), islandSourceId, channelName, channelId);
                }

                @SneakyThrows
                public JSONObject deleteChannel(String islandSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelApi.deleteChannel(bot.getAuthorization(), islandSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject addChannelArticle(String channelId, String title, String content, String imageUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.addChannelArticle(bot.getAuthorization(), channelId, title, content, imageUrl);
                }

                @SneakyThrows
                public JSONObject removeChannelArticle(int type, String id, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelArticleApi.removeChannelArticle(bot.getAuthorization(), type, id, channelId);
                }

                @SneakyThrows
                public JSONObject sendTextMessage(String channelId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendTextMessage(bot.getAuthorization(), channelId, content);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessageWithReason(String messageId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessageWithReason(bot.getAuthorization(), messageId, reason);
                }

                @SneakyThrows
                public JSONObject sendChannelFileMessage(String channelId, String fileUrl, String name, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelFileMessage(bot.getAuthorization(), channelId, fileUrl, name, size);
                }

                @SneakyThrows
                public JSONObject addChannelMessageReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.addChannelMessageReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelShareMessage(String channelId, String jumpUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelShareMessage(bot.getAuthorization(), channelId, jumpUrl);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, File file) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, file);
                }

                @SneakyThrows
                public JSONObject sendChannelPictureMessage(String channelId, String imageUrl, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelPictureMessage(bot.getAuthorization(), channelId, imageUrl, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject withdrawChannelMessage(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.withdrawChannelMessage(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject editChannelCardMessage(String messageId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelCardMessage(bot.getAuthorization(), messageId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionList(String messageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionList(bot.getAuthorization(), messageId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageReaction(String messageId, String id, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageReaction(bot.getAuthorization(), messageId, id, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject removeChannelMessageBotReaction(String messageId, String id) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.removeChannelMessageBotReaction(bot.getAuthorization(), messageId, id);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendChannelVideoMessage(String channelId, String videoUrl) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendChannelVideoMessage(bot.getAuthorization(), channelId, videoUrl);
                }

                @SneakyThrows
                public JSONObject setChannelMessageTop(String messageId, int operateType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.setChannelMessageTop(bot.getAuthorization(), messageId, operateType);
                }

                @SneakyThrows
                public JSONObject sendAtTextMessage(String channelId, String dodoId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendAtTextMessage(bot.getAuthorization(), channelId, dodoId, message);
                }

                @SneakyThrows
                public JSONObject editChannelMessage(String messageId, String content) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.editChannelMessage(bot.getAuthorization(), messageId, content);
                }

                @SneakyThrows
                public JSONObject referencedMessage(String channelId, String content, String referencedMessageId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.referencedMessage(bot.getAuthorization(), channelId, content, referencedMessageId);
                }

                @SneakyThrows
                public JSONObject sendCardMessage(String channelId, Card messageBody) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.sendCardMessage(bot.getAuthorization(), channelId, messageBody);
                }

                @SneakyThrows
                public JSONObject getChannelMessageReactionMemberList(String messageId, int type, String id, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi.getChannelMessageReactionMemberList(bot.getAuthorization(), messageId, type, id, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject editChannelVoiceMember(int operateType, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.editChannelVoiceMember(bot.getAuthorization(), operateType, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.getChannelVoiceMemberStatus(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject moveChannelVoiceMember(String islandSourceId, String dodoSourceId, String channelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ChannelVoiceApi.moveChannelVoiceMember(bot.getAuthorization(), islandSourceId, dodoSourceId, channelId);
                }

                @SneakyThrows
                public JSONObject getGiftGrossValueList(String targetId, int targetType, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftGrossValueList(bot.getAuthorization(), targetId, targetType, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftShareRatioInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftShareRatioInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getGiftList(String targetId, int targetType) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftList(bot.getAuthorization(), targetId, targetType);
                }

                @SneakyThrows
                public JSONObject getGiftMemberList(String targetId, int targetType, String giftId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftMemberList(bot.getAuthorization(), targetId, targetType, giftId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getGiftAccount(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.GiftApi.getGiftAccount(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIntegralInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.getIntegralInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject setIntegralEdit(String islandSourceId, String dodoSourceId, int operateType, long integral) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IntegralApi.setIntegralEdit(bot.getAuthorization(), islandSourceId, dodoSourceId, operateType, integral);
                }

                @SneakyThrows
                public JSONObject getIslandLevelRankList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandLevelRankList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getIslandList() {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandList(bot.getAuthorization());
                }

                @SneakyThrows
                public JSONObject getIslandBanList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandBanList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandMuteList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandMuteList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject getIslandInfo(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.IslandApi.getIslandInfo(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonMute(String islandSourceId, String dodoSourceId, int duration, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration, reason);
                }

                @SneakyThrows
                public JSONObject getMemberInvitationInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInvitationInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberChannelBan(String islandSourceId, String dodoSourceId, String noticeChannelId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberChannelBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId);
                }

                @SneakyThrows
                public JSONObject removeMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberInfo(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberInfo(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId, noticeChannelId, reason);
                }

                @SneakyThrows
                public JSONObject addMemberBan(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberBan(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberMute(String islandSourceId, String dodoSourceId, int duration) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId, duration);
                }

                @SneakyThrows
                public JSONObject editMemberNickName(String islandSourceId, String dodoSourceId, String nickName) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.editMemberNickName(bot.getAuthorization(), islandSourceId, dodoSourceId, nickName);
                }

                @SneakyThrows
                public JSONObject getMemberRoleList(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberRoleList(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject addMemberReasonBan(String islandSourceId, String dodoSourceId, String reason) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.addMemberReasonBan(bot.getAuthorization(), islandSourceId, dodoSourceId, reason);
                }

                @SneakyThrows
                public JSONObject removeMemberMute(String islandSourceId, String dodoSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.removeMemberMute(bot.getAuthorization(), islandSourceId, dodoSourceId);
                }

                @SneakyThrows
                public JSONObject getMemberList(String islandSourceId, int pageSize, long maxId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi.getMemberList(bot.getAuthorization(), islandSourceId, pageSize, maxId);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform, String issuer, String series) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform, issuer, series);
                }

                @SneakyThrows
                public JSONObject getMemberNftStatus(String islandSourceId, String dodoSourceId, String platform) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.NTFApi.getMemberNftStatus(bot.getAuthorization(), islandSourceId, dodoSourceId, platform);
                }

                @SneakyThrows
                public JSONObject sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height, Boolean isOriginal) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height, isOriginal);
                }

                @SneakyThrows
                public JSONObject sendDodoPictureMessage(String islandSourceId, String dodoSourceId, String u, int width, int height) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoPictureMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, width, height);
                }

                @SneakyThrows
                public JSONObject sendPersonalMessage(String islandSourceId, String dodoSourceId, String message) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendPersonalMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, message);
                }

                @SneakyThrows
                public JSONObject sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u, String coverUrl, long duration, long size) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u, coverUrl, duration, size);
                }

                @SneakyThrows
                public JSONObject sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi.sendDodoVideoMessage(bot.getAuthorization(), islandSourceId, dodoSourceId, u);
                }

                @SneakyThrows
                public JSONObject uploadResource(String path) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.ResourceApi.uploadResource(bot.getAuthorization(), path);
                }

                @SneakyThrows
                public JSONObject editRole(String islandSourceId, String roleId, String roleName, String roleColor, int position, String permission) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.editRole(bot.getAuthorization(), islandSourceId, roleId, roleName, roleColor, position, permission);
                }

                @SneakyThrows
                public JSONObject addRole(String islandSourceId, String roleName, String roleColor, int position, String permission) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.addRole(bot.getAuthorization(), islandSourceId, roleName, roleColor, position, permission);
                }

                @SneakyThrows
                public JSONObject removeRoleMember(String islandSourceId, String dodoSourceId, String roleId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.removeRoleMember(bot.getAuthorization(), islandSourceId, dodoSourceId, roleId);
                }

                @SneakyThrows
                public JSONObject addRoleMember(String islandSourceId, String dodoSourceId, String roleId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.addRoleMember(bot.getAuthorization(), islandSourceId, dodoSourceId, roleId);
                }

                @SneakyThrows
                public JSONObject getRoleList(String islandSourceId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.getRoleList(bot.getAuthorization(), islandSourceId);
                }

                @SneakyThrows
                public JSONObject deleteRole(String islandSourceId, String roleId) {
                    return io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi.deleteRole(bot.getAuthorization(), islandSourceId, roleId);
                }

            }
        }
    }
}
