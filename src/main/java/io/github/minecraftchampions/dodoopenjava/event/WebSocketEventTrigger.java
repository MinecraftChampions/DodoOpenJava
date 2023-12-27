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
import lombok.SneakyThrows;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * 事件触发
 */
public class WebSocketEventTrigger extends EventTrigger {
    private String wssLo = "";

    @Override
    public boolean isConnect() {
        if (mWebSocket == null) {
            return false;
        } else {
            return mWebSocket.isOpen();
        }
    }

    public WebSocketClient mWebSocket = null;

    public static final long pingInterval = 20 * 1000;


    public WebSocketEventTrigger(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void start() {
        v2();
    }

    public void close() {
        if (mWebSocket != null) {
            mWebSocket.close(1000, "");
        }
    }

    private static void startHeartbeatTask(WebSocketClient client) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (client.isOpen()) {
                    client.sendPing();
                    cancel();
                    timer.purge();
                }
            }
        }, 5000, pingInterval);
    }

    @SneakyThrows
    public void v2() {
        if (isConnect()) {
            return;
        }
        bot.getApi().V2.eventApi.getWebSocketConnection()
                .ifSuccess(result -> wssLo = result.getJSONObjectData().getString("endpoint"))
                .ifFailure(result -> {
                    throw new RuntimeException(result.getMessage());
                });
        mWebSocket = new WsListenerC(new URI(wssLo));
        mWebSocket.connect();
        long i = waitForTheResponseMills / 500;
        while (!mWebSocket.isOpen() && i > 0) {
            Thread.sleep(500);
            i--;
        }
    }

    public static final long waitForTheResponseMills = 10 * 1000;

    class WsListenerC extends WebSocketClient {
        private final EventManager eventManager = bot.getEventManager();

        public WsListenerC(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake data) {
            startHeartbeatTask(mWebSocket);
        }

        @Override
        public void onMessage(String s) {
        }

        @Override
        public void onMessage(ByteBuffer bf) {
            String message = new String(bf.array());
            JSONObject jsontext = new JSONObject(message);
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
        public void onError(Exception ex) {
            ex.printStackTrace();
            super.reconnect();
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            mWebSocket.close(1000, "正常关闭");
            mWebSocket = null;
        }
    }
}
