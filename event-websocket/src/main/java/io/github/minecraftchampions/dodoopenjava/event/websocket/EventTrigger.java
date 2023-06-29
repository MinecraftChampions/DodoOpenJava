package io.github.minecraftchampions.dodoopenjava.event.websocket;

import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.event.events.v1.*;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.GiftSendEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.GoodsPurchaseEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.IntegralChangeEvent;
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
public class EventTrigger {
    public static EventTrigger p;
    public static String wssLo="";
    public static OkHttpClient okHttpClient = new OkHttpClient();
    public static OkHttpClient wss=new OkHttpClient.Builder()
            .pingInterval(30, TimeUnit.SECONDS) //保活心跳
            .build();
    public static  WebSocket mWebSocket;

    public static String ad;
    public static void main(@NotNull String Authorization) {
        v2(Authorization);
    }

    public static void v1(String Authorization) {
        ad = Authorization;
        Request request = new Request.Builder().url("https://botopen.imdodo.com/api/v1/websocket/connection").addHeader("Content-Type", "application/json").addHeader("Authorization", ad)
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                wssLo= new JSONObject(Objects.requireNonNull(response.body()).string()).getJSONObject("data").getString("endpoint");
                response.close();
                Request request = new Request.Builder()
                        .url(wssLo).build();
                mWebSocket = wss.newWebSocket(request, new WsListenerC1(p));
            }
        });
    }

    public static void v2(String Authorization) {
        ad = Authorization;
        Request request = new Request.Builder().url("https://botopen.imdodo.com/api/v2/websocket/connection").addHeader("Content-Type", "application/json").addHeader("Authorization", ad)
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                wssLo= new JSONObject(Objects.requireNonNull(response.body()).string()).getJSONObject("data").getString("endpoint");
                response.close();
                Request request = new Request.Builder()
                        .url(wssLo).build();
                mWebSocket = wss.newWebSocket(request, new WsListenerC2(p));
            }
        });
    }

    public static class WsListenerC1 extends WebSocketListener {
        EventTrigger p;
        public WsListenerC1(EventTrigger dodo) {
            p=dodo;
        }
        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            JSONObject jsontext = new JSONObject(bytes.utf8());
            switch (jsontext.getJSONObject("data").getString("eventType")) {
                case "1001" -> {
                    try {
                        EventManage.fireEvent(new PersonalMessageEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "2001" -> {
                    try {
                        EventManage.fireEvent(new MessageEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3001" -> {
                    try {
                        EventManage.fireEvent(new MessageReactionEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3002" -> {
                    try {
                        EventManage.fireEvent(new CardMessageButtonClickEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3003" -> {
                    try {
                        EventManage.fireEvent(new CardMessageFormSubmitEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3004" -> {
                    try {
                        EventManage.fireEvent(new CardMessageListSubmitEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4001" -> {
                    try {
                        EventManage.fireEvent(new MemberJoinEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4002" -> {
                    try {
                        EventManage.fireEvent(new MemberLeaveEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5001" -> {
                    try {
                        EventManage.fireEvent(new ChannelVoiceMemberJoinEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5002" -> {
                    try {
                        EventManage.fireEvent(new ChannelVoiceMemberLeaveEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6001" -> {
                    try {
                        EventManage.fireEvent(new ChannelArticleEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6002" -> {
                    try {
                        EventManage.fireEvent(new ChannelArticleCommentEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> System.out.println("未知的事件！");
            }
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            t.printStackTrace();
        }

        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            mWebSocket.close(1000,"正常关闭");
        }
    }

    public static class WsListenerC2 extends WebSocketListener {
        EventTrigger p;
        public WsListenerC2(EventTrigger dodo) {
            p=dodo;
        }
        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            JSONObject jsontext = new JSONObject(bytes.utf8());
            switch (jsontext.getJSONObject("data").getString("eventType")) {
                case "1001" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.PersonalMessageEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "2001" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.MessageEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3001" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.MessageReactionEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3002" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.CardMessageButtonClickEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3003" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.CardMessageFormSubmitEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3004" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.CardMessageListSubmitEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4001" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.MemberJoinEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4002" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.MemberLeaveEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5001" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.ChannelVoiceMemberJoinEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5002" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.ChannelVoiceMemberLeaveEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6001" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.ChannelArticleEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6002" -> {
                    try {
                        EventManage.fireEvent(new io.github.minecraftchampions.dodoopenjava.event.events.v2.ChannelArticleCommentEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "7001" -> {
                    try {
                        EventManage.fireEvent(new GiftSendEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "8001" -> {
                    try {
                        EventManage.fireEvent(new IntegralChangeEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "9001" -> {
                    try {
                        EventManage.fireEvent(new GoodsPurchaseEvent(jsontext));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> System.out.println("未知的事件！");
            }
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            t.printStackTrace();
        }

        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            mWebSocket.close(1000,"正常关闭");
        }
    }
}
