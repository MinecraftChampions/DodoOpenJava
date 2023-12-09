package io.github.minecraftchampions.dodoopenjava.event;

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
import io.github.minecraftchampions.dodoopenjava.utils.OpenSecretUtil;
import lombok.Getter;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.w3c.dom.events.EventException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * 事件触发
 */
public class WebHookEventTrigger extends EventTrigger {
    public MockWebServer server = null;

    /**
     * 启动监听
     */
    @Override
    public void start() {
        server = new MockWebServer();
        server.setDispatcher(dispatcher);
        try {
            server.start(port);
            isConnect = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 端口（默认80）
     */
    @Getter
    private int port = 80;

    /**
     * 解密密钥
     */
    @Getter
    private String SecretKey;

    /**
     * api路径(默认为空)
     */
    @Getter
    private String path;

    /**
     * 设置api路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 设置端口(默认80)
     *
     * @param port 端口
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 解密密钥
     */
    public void setSecretKey(String s) {
        SecretKey = s;
    }


    /**
     * 关闭服务器
     */
    @Override
    public void close() {
        if (server == null || isConnect()) {
            return;
        }
        try {
            server.close();
            isConnect = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public boolean isConnect() {
        return server != null && isConnect;
    }

    /**
     * 处理请求
     */
    public final Dispatcher dispatcher = new Dispatcher() {
        @Override
        public @NotNull MockResponse dispatch(RecordedRequest request) {
            MockResponse mockResponse = new MockResponse();
            mockResponse.setHeader("Content-Type", "application/json");
            if (!Objects.equals(request.getMethod(), "POST")) {
                return mockResponse.setBody("""
                        {
                          "status": -9999,
                          "message": "错误的请求"
                        }
                        """);
            }
            String s = request.getBody().readUtf8();
            JSONObject json = new JSONObject(s);
            if (json.keySet().containsAll(Arrays.asList("clientId", "payload"))) {
                String payload = json.getString("payload");
                String event = OpenSecretUtil.WebHookDecrypt(payload, SecretKey);
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(event));
                if (jsonObject.getInt("type") == 2) {
                    return mockResponse.setBody("{\n" +
                            "    \"status\": 0,\n" +
                            "    \"message\": \"\",\n" +
                            "    \"data\": {\n" +
                            "        \"checkCode\": \"" + jsonObject.getJSONObject("data").getString("checkCode") + "\"\n" +
                            "    }\n" +
                            "}");
                }
                DisposeEvent thread = new DisposeEvent(jsonObject);
                thread.start();
                return mockResponse.setBody("""
                        {
                            "status": 0,
                            "message": ""
                        }""");
            }
            return mockResponse.setBody("""
                    {
                      "status": -9999,
                      "message": "错误的请求"
                    }
                    """);
        }
    };

    /**
     * 触发事件
     */
    public class DisposeEvent extends Thread {
        EventManager eventManager = getBot().getEventManager();
        @Override
        public void run() {
            super.run();
        }

        public DisposeEvent(JSONObject json) {
            switch (json.getJSONObject("data").getString("eventType")) {
                case "1001" -> {
                    try {
                        eventManager.fireEvent(new PersonalMessageEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "2001" -> {
                    try {
                        eventManager.fireEvent(new MessageEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3001" -> {
                    try {
                        eventManager.fireEvent(new MessageReactionEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3002" -> {
                    try {
                        eventManager.fireEvent(new CardMessageButtonClickEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3003" -> {
                    try {
                        eventManager.fireEvent(new CardMessageFormSubmitEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3004" -> {
                    try {
                        eventManager.fireEvent(new CardMessageListSubmitEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4001" -> {
                    try {
                        eventManager.fireEvent(new MemberJoinEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4002" -> {
                    try {
                        eventManager.fireEvent(new MemberLeaveEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5001" -> {
                    try {
                        eventManager.fireEvent(new ChannelVoiceMemberJoinEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5002" -> {
                    try {
                        eventManager.fireEvent(new ChannelVoiceMemberLeaveEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6001" -> {
                    try {
                        eventManager.fireEvent(new ChannelArticlePublishEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6002" -> {
                    try {
                        eventManager.fireEvent(new ChannelArticleCommentEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "7001" -> {
                    try {
                        eventManager.fireEvent(new GiftSendEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "8001" -> {
                    try {
                        eventManager.fireEvent(new IntegralChangeEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "9001" -> {
                    try {
                        eventManager.fireEvent(new GoodsPurchaseEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> System.out.println("未知的事件！");
            }
        }
    }
}
