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

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

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
                case "1001" -> eventManager.fireEvent(new PersonalMessageEvent(json));
                case "2001" -> eventManager.fireEvent(new MessageEvent(json));
                case "3001" -> eventManager.fireEvent(new MessageReactionEvent(json));
                case "3002" -> eventManager.fireEvent(new CardMessageButtonClickEvent(json));
                case "3003" -> eventManager.fireEvent(new CardMessageFormSubmitEvent(json));
                case "3004" -> eventManager.fireEvent(new CardMessageListSubmitEvent(json));
                case "4001" -> eventManager.fireEvent(new MemberJoinEvent(json));
                case "4002" -> eventManager.fireEvent(new MemberLeaveEvent(json));
                case "5001" -> eventManager.fireEvent(new ChannelVoiceMemberJoinEvent(json));
                case "5002" -> eventManager.fireEvent(new ChannelVoiceMemberLeaveEvent(json));
                case "6001" -> eventManager.fireEvent(new ChannelArticlePublishEvent(json));
                case "6002" -> eventManager.fireEvent(new ChannelArticleCommentEvent(json));
                case "7001" -> eventManager.fireEvent(new GiftSendEvent(json));
                case "8001" -> eventManager.fireEvent(new IntegralChangeEvent(json));
                case "9001" -> eventManager.fireEvent(new GoodsPurchaseEvent(json));
                default -> System.getLogger(Logger.GLOBAL_LOGGER_NAME).log(System.Logger.Level.WARNING, "未知的事件！");
            }
        }
    }
}
