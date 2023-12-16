package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.Bot;
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
import okhttp3.*;
import okio.ByteString;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 事件触发
 */
public class WebSocketEventTrigger extends EventTrigger {
    public WebSocketEventTrigger p;
    private String wssLo = "";

    private static final int MAX_NUM = 5;
    private static final int MILLIS = 5000;

    private int connectNum = 0;

    public WebSocket mWebSocket = null;
    public OkHttpClient okHttpClient = new OkHttpClient();
    public static OkHttpClient wss = new OkHttpClient.Builder()
            .pingInterval(15, TimeUnit.SECONDS) //保活心跳
            .build();

    public WebSocketEventTrigger(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void start() {
        v2();
    }

    public void reconnect() {
        if (connectNum <= MAX_NUM) {
            try {
                Thread.sleep(MILLIS);
                v2();
                connectNum++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("reconnect over " + MAX_NUM + ",please check url or network");
        }
    }

    public void close() {
        if (mWebSocket != null) {
            isConnect = false;
            mWebSocket.close(1000, "");
        }
    }

    public void v2() {
        if (isConnect) {
            return;
        }
        isConnect = true;
        Request request = new Request.Builder().url("https://botopen.imdodo.com/api/v2/websocket/connection").addHeader("Content-Type", "application/json").addHeader("Authorization", bot.getAuthorization())
                .post(RequestBody.create("{}", MediaType.parse("application/json")))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse( Call call, Response response) throws IOException {
                wssLo = new JSONObject(Objects.requireNonNull(response.body()).string()).getJSONObject("data").getString("endpoint");
                response.close();
                try {
                    Request request = new Request.Builder()
                            .url(wssLo)
                            .build();
                    mWebSocket = wss.newWebSocket(request, new WsListenerC(p));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public class WsListenerC extends WebSocketListener {
        private final EventManager eventManager = bot.getEventManager();
        WebSocketEventTrigger p;

        public WsListenerC(WebSocketEventTrigger et) {
            p = et;
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            isConnect = response.code() == 101;
        }

        @Override
        public void onMessage( WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            JSONObject jsontext = new JSONObject(bytes.utf8());
            switch (jsontext.getJSONObject("data").getString("eventType")) {
                case "1001" -> eventManager.fireEvent(new PersonalMessageEvent(jsontext));
                case "2001" -> eventManager.fireEvent(new MessageEvent(jsontext));
                case "3001" -> eventManager.fireEvent(new MessageReactionEvent(jsontext));
                case "3002" -> eventManager.fireEvent(new CardMessageButtonClickEvent(jsontext));
                case "3003" -> eventManager.fireEvent(new CardMessageFormSubmitEvent(jsontext));
                case "3004" -> eventManager.fireEvent(new CardMessageListSubmitEvent(jsontext));
                case "4001" -> eventManager.fireEvent(new MemberJoinEvent(jsontext));
                case "4002" -> eventManager.fireEvent(new MemberLeaveEvent(jsontext));
                case "5001" -> eventManager.fireEvent(new ChannelVoiceMemberJoinEvent(jsontext));
                case "5002" -> eventManager.fireEvent(new ChannelVoiceMemberLeaveEvent(jsontext));
                case "6001" -> eventManager.fireEvent(new ChannelArticlePublishEvent(jsontext));
                case "6002" -> eventManager.fireEvent(new ChannelArticleCommentEvent(jsontext));
                case "7001" -> eventManager.fireEvent(new GiftSendEvent(jsontext));
                case "8001" -> eventManager.fireEvent(new IntegralChangeEvent(jsontext));
                case "9001" -> eventManager.fireEvent(new GoodsPurchaseEvent(jsontext));
                default -> System.getLogger(Logger.GLOBAL_LOGGER_NAME).log(System.Logger.Level.WARNING, "未知的事件！");
            }
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            if (response != null) {
                System.getLogger(Logger.GLOBAL_LOGGER_NAME).log(System.Logger.Level.WARNING, "WebSocket 连接失败：{}", response.message());
            }
            isConnect = false;
            if (StringUtil.isEmpty(t.getMessage())) {
                reconnect();
            }
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            mWebSocket.close(1000, "正常关闭");
            mWebSocket = null;
            isConnect = false;
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }
    }
}
