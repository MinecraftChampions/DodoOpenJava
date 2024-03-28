package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.api.Bot;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

/**
 * 事件触发
 *
 * @author qscbm187531
 * @author holdlijun
 */
@Slf4j
public class WebSocketEventTrigger extends AbstractEventTrigger {
    private String wssLo = "";

    @Override
    public boolean isConnect() {

        return mWebSocket != null && mWebSocket.isOpen();
    }

    public WebSocketClient mWebSocket = null;

    public static final long PING_INTERVAL = 20 * 1000;


    public WebSocketEventTrigger(Bot bot) {
        this.bot = bot;
    }

    private int reacquireCount = 0;

    private final int reacquireMaxCount = 5000;

    @Override
    public void start() {
        v2();
    }

    @Override
    public synchronized void close() {
        if (mWebSocket != null) {
            mWebSocket.close(1000, "");
        }
    }

    public void reconnectWebsocket() {
        if (mWebSocket != null) {
            CompletableFuture.runAsync(this::start);
        }
    }

    @SneakyThrows
    public synchronized void v2() {
        bot.getApi().V2.eventApi.getWebSocketConnection()
                .ifSuccess(result -> {
                    reacquireCount = 0;
                    wssLo = result.getJSONObjectData().getJSONObject("data").getString("endpoint");
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
                            log.error("未连接上websocket");
                        }
                    } catch (Exception e) {
                        log.error("错误", e);
                        reconnectWebsocket();
                    }
                })
                .ifFailure(result -> {
                    reacquireCount++;
                    if (reacquireCount > reacquireMaxCount) {
                        log.error("获取websocket连接错误" + result.getMessage() +
                                  ";\n当前重连次数:" + reacquireCount + ",已超过最大重连次数:" + reacquireMaxCount
                                  + ",已取消重连");
                        return;
                    }
                    log.warn("获取websocket连接错误" + result.getMessage() +
                             ";\n已尝试重新获取,当前重新获取次数:" + reacquireCount);
                    v2();
                });
    }

    public static long waitForTheResponseMills = 15 * 1000;

    public void sendHeartbeatPacket() {
        CompletableFuture.runAsync(() -> {
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

        public static final String HEARTBEAT_STRING = "{\"type\":1,\"version\":\"\"}";

        @Override
        public void onMessage(ByteBuffer bf) {
            String message = new String(bf.array());
            if (HEARTBEAT_STRING.equals(message)) {
                return;
            }
            JSONObject jsontext = new JSONObject(message);
            try {
                eventManager.parseAndFireEvent(jsontext);
            } catch (Exception e) {
                log.warn("Websocket消息接收发生未知错误;消息内容:" + message
                         + ";\n错误内容:" + e.getLocalizedMessage());
                sendPing();
            }
        }

        @Override
        public void onError(Exception ex) {
            log.error("websocket接收事件发生错误", ex);
            reconnectWebsocket();
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            log.warn("websocket关闭;code:" + code + ":reason:" + reason + ";已自动重连");
            reconnectWebsocket();
        }
    }
}