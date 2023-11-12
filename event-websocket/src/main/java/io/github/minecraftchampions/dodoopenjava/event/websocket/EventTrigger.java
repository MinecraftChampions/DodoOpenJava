package io.github.minecraftchampions.dodoopenjava.event.websocket;

import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle.ChannelArticleCommentEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle.ChannelArticlePublishEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.*;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice.ChannelVoiceMemberJoinEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice.ChannelVoiceMemberLeaveEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.gift.GiftSendEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.integral.IntegralChangeEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.member.MemberJoinEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.member.MemberLeaveEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.personal.PersonalMessageEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.shop.GoodsPurchaseEvent;
import io.github.minecraftchampions.dodoopenjava.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.w3c.dom.events.EventException;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 事件触发
 */
@Slf4j
public class EventTrigger {
    public static EventTrigger p;
    public static String wssLo = "";
    private static boolean isConnect = false;

    private final static int MAX_NUM = 5;
    private final static int MILLIS = 5000;

    private static int connectNum = 0;

    public static WebSocket mWebSocket = null;
    public static OkHttpClient okHttpClient = new OkHttpClient();
    public static OkHttpClient wss = new OkHttpClient.Builder()
            .pingInterval(15, TimeUnit.SECONDS) //保活心跳
            .build();

    public static String ad;

    public static void main(@NotNull String Authorization) {
        v2(Authorization);
    }

    public static void reconnect(String Authorization) {
        log.warn("连接重试");
        if (connectNum <= MAX_NUM) {
            try {
                Thread.sleep(MILLIS);
                v2(Authorization);
                connectNum++;
            } catch (Exception e) {
                log.error("reconnect",e);
            }
        } else {
            log.info( "reconnect over " + MAX_NUM + ",please check url or network");
        }
    }
    /**
     * 是否连接
     */
    public static boolean isConnectFun() {
        return mWebSocket != null && isConnect;
    }

    public static void v2(String Authorization) {
        if (isConnect){
            return;
        }
        ad = Authorization;
        Request request = new Request.Builder().url("https://botopen.imdodo.com/api/v2/websocket/connection").addHeader("Content-Type", "application/json").addHeader("Authorization", ad)
                .post(RequestBody.create("{}", MediaType.parse("application/json")))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                wssLo = new JSONObject(Objects.requireNonNull(response.body()).string()).getJSONObject("data").getString("endpoint");
                response.close();
                try {
                    Request request = new Request.Builder()
                            .url(wssLo)
                            .build();
                    mWebSocket = wss.newWebSocket(request, new WsListenerC(p));
                } catch (Exception e){
                    log.error("链接中断",e);
                }
            }
        });
    }

    public static class WsListenerC extends WebSocketListener {
        EventTrigger p;

        public WsListenerC(EventTrigger dodo) {
            p = dodo;
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            isConnect = response.code() == 101;
            if (!isConnectFun()) {
                reconnect(ad);
            } else {
                log.info("WebSocket 连接成功");
            }

        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket,bytes);
            JSONObject jsontext = new JSONObject(bytes.utf8());
            switch (jsontext.getJSONObject("data").getString("eventType")) {
                case "1001" -> {
                    try {
                        EventManage.fireEvent(new PersonalMessageEvent(jsontext));
                    } catch (EventException e) {
                        log.error("1001",e);
                    }
                }
                case "2001" -> {
                    try {
                        EventManage.fireEvent(new MessageEvent(jsontext));
                    } catch (EventException e) {
                        log.error("2001",e);
                    }
                }
                case "3001" -> {
                    try {
                        EventManage.fireEvent(new MessageReactionEvent(jsontext));
                    } catch (EventException e) {
                        log.error("3001",e);
                    }
                }
                case "3002" -> {
                    try {
                        EventManage.fireEvent(new CardMessageButtonClickEvent(jsontext));
                    } catch (EventException e) {
                        log.error("3002",e);
                    }
                }
                case "3003" -> {
                    try {
                        EventManage.fireEvent(new CardMessageFormSubmitEvent(jsontext));
                    } catch (EventException e) {
                        log.error("3003",e);
                    }
                }
                case "3004" -> {
                    try {
                        EventManage.fireEvent(new CardMessageListSubmitEvent(jsontext));
                    } catch (EventException e) {
                        log.error("3004",e);
                    }
                }
                case "4001" -> {
                    try {
                        EventManage.fireEvent(new MemberJoinEvent(jsontext));
                    } catch (EventException e) {
                        log.error("4001",e);
                    }
                }
                case "4002" -> {
                    try {
                        EventManage.fireEvent(new MemberLeaveEvent(jsontext));
                    } catch (EventException e) {
                        log.error("4002",e);
                    }
                }
                case "5001" -> {
                    try {
                        EventManage.fireEvent(new ChannelVoiceMemberJoinEvent(jsontext));
                    } catch (EventException e) {
                        log.error("5001",e);
                    }
                }
                case "5002" -> {
                    try {
                        EventManage.fireEvent(new ChannelVoiceMemberLeaveEvent(jsontext));
                    } catch (EventException e) {
                        log.error("5002",e);
                    }
                }
                case "6001" -> {
                    try {
                        EventManage.fireEvent(new ChannelArticlePublishEvent(jsontext));
                    } catch (EventException e) {
                        log.error("6001",e);
                    }
                }
                case "6002" -> {
                    try {
                        EventManage.fireEvent(new ChannelArticleCommentEvent(jsontext));
                    } catch (EventException e) {
                        log.error("6002",e);
                    }
                }
                case "7001" -> {
                    try {
                        EventManage.fireEvent(new GiftSendEvent(jsontext));
                    } catch (EventException e) {
                        log.error("7001",e);
                    }
                }
                case "8001" -> {
                    try {
                        EventManage.fireEvent(new IntegralChangeEvent(jsontext));
                    } catch (EventException e) {
                        log.error("8001",e);
                    }
                }
                case "9001" -> {
                    try {
                        EventManage.fireEvent(new GoodsPurchaseEvent(jsontext));
                    } catch (EventException e) {
                        log.error("9001",e);
                    }
                }
                default -> log.warn("未知的事件！");
            }
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            if (response != null) {
                log.error("WebSocket 连接失败：{}",response.message());
            }
            log.error("WebSocket 连接失败异常原因：{}",t.getMessage());
            isConnect = false;
            if (StringUtil.isEmpty(t.getMessage())) {
                log.warn("进行重试");
                reconnect(ad);
            }
        }

        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosed(webSocket, code, reason);
            mWebSocket.close(1000,"正常关闭");
            mWebSocket = null;
            isConnect = false;
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }
    }
}
