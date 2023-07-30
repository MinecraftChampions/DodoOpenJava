package io.github.minecraftchampions.dodoopenjava.event.webhook;

import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.*;
import io.github.minecraftchampions.dodoopenjava.event.webhook.util.OpenSecretUtil;
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
public class EventTrigger {
    public static MockWebServer server = null;

    /**
     * 启动监听
     */
    public static void main() {
        server = new MockWebServer();
        server.setDispatcher(dispatcher);
        try {
            server.start(BotManage.port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭服务器
     */
    public static void closeServer() {
        if (server == null) {
            return;
        }
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理请求
     */
    public static final Dispatcher dispatcher = new Dispatcher() {
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
                String event = OpenSecretUtil.WebHookDecrypt(payload, BotManage.SecretKey);
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
    public static class DisposeEvent extends Thread {
        @Override
        public void run() {
            super.run();
        }

        public DisposeEvent(JSONObject json) {
            switch (json.getJSONObject("data").getString("eventType")) {
                case "1001" -> {
                    try {
                        EventManage.fireEvent(new PersonalMessageEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "2001" -> {
                    try {
                        EventManage.fireEvent(new MessageEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3001" -> {
                    try {
                        EventManage.fireEvent(new MessageReactionEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3002" -> {
                    try {
                        EventManage.fireEvent(new CardMessageButtonClickEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3003" -> {
                    try {
                        EventManage.fireEvent(new CardMessageFormSubmitEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "3004" -> {
                    try {
                        EventManage.fireEvent(new CardMessageListSubmitEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4001" -> {
                    try {
                        EventManage.fireEvent(new MemberJoinEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "4002" -> {
                    try {
                        EventManage.fireEvent(new MemberLeaveEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5001" -> {
                    try {
                        EventManage.fireEvent(new ChannelVoiceMemberJoinEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "5002" -> {
                    try {
                        EventManage.fireEvent(new ChannelVoiceMemberLeaveEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6001" -> {
                    try {
                        EventManage.fireEvent(new ChannelArticleEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "6002" -> {
                    try {
                        EventManage.fireEvent(new ChannelArticleCommentEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "7001" -> {
                    try {
                        EventManage.fireEvent(new GiftSendEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "8001" -> {
                    try {
                        EventManage.fireEvent(new IntegralChangeEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "9001" -> {
                    try {
                        EventManage.fireEvent(new GoodsPurchaseEvent(json));
                    } catch (EventException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> System.out.println("未知的事件！");
            }
        }
    }
}
