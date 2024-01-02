package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.Bot;
import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
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
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.nio.ByteBuffer;

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

    private int reacquireCount = 0;

    private final int reacquireMaxCount = 50;

    @Override
    public void start() {
        v2();
    }

    public synchronized void close() {
        if (mWebSocket != null) {
            mWebSocket.close(1000, "");
        }
    }

    public void reconnectWebsocket() {
        if (mWebSocket != null) {
            Thread thread = new Thread(this::start);
            thread.start();
        }
    }

    @SneakyThrows
    public synchronized void v2() {
        bot.getApi().V2.eventApi.getWebSocketConnection()
                .ifSuccess(result -> {
                    reacquireCount = 0;
                    wssLo = result.getJSONObjectData().getString("endpoint");
                    try {
                        mWebSocket = new WsListenerC(new URI(wssLo));
                        mWebSocket.setConnectionLostTimeout(0);
                        mWebSocket.connect();
                        long i = waitForTheResponseMills / 500;
                        while (!mWebSocket.isOpen() && i > 0) {
                            Thread.sleep(500);
                            i--;
                        }
                        if (!mWebSocket.isOpen()) {
                            new RuntimeException("未连接上websocket").printStackTrace();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ifFailure(result -> {
                    reacquireCount++;
                    if (reacquireCount > reacquireMaxCount) {
                        DodoOpenJava.LOGGER.error("获取websocket连接错误" + result.getMessage() +
                                ";\n当前重连次数:" + reacquireCount + ",已超过最大重连次数:" + reacquireMaxCount
                                + ",已取消重连");
                        return;
                    }
                    DodoOpenJava.LOGGER.warn("获取websocket连接错误" + result.getMessage() +
                            ";\n已尝试重新获取,当前重新获取次数:" + reacquireCount);
                    v2();
                });
    }

    public static long waitForTheResponseMills = 15 * 1000;

    public void sendHeartbeatPacket() {
        Thread thread = new Thread(() -> {
            while (mWebSocket.isOpen()) {
                mWebSocket.sendPing();
                mWebSocket.send("""
                        {
                          "type":1
                        }
                        """);
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        thread.start();
    }

    class WsListenerC extends WebSocketClient {
        @Override
        public void onWebsocketPong(WebSocket conn, Framedata f) {
            sendFrame(new PingFrame());
        }

        private final EventManager eventManager = bot.getEventManager();

        public WsListenerC(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake data) {
            sendHeartbeatPacket();
        }

        @Override
        public void onMessage(String s) {
        }

        @Override
        public void onMessage(ByteBuffer bf) {
            String message = new String(bf.array());
            if (message.equals("{\"type\":1,\"version\":\"\"}")) {
                sendPing();
                return;
            }
            JSONObject jsontext = new JSONObject(message);
            try {
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
                    default -> DodoOpenJava.LOGGER.warn("未知的事件！");
                }
            } catch (Exception e) {
                DodoOpenJava.LOGGER.warn("Websocket消息接收发生未知错误;消息内容:" + message
                        + ";\n错误内容:" + e.getLocalizedMessage());
                sendPing();
            }
        }

        @Override
        public void onError(Exception ex) {
            ex.printStackTrace();
            reconnectWebsocket();
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            DodoOpenJava.LOGGER.warn("websocket关闭;code:" + code + ":reason:" + reason + ";已自动重连");
            reconnectWebsocket();
        }
    }
}
