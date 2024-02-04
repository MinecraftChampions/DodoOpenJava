package io.github.minecraftchampions.dodoopenjava.event;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
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
import io.github.minecraftchampions.dodoopenjava.utils.OpenSecretUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * 事件触发
 */
@Slf4j
public class WebHookEventTrigger extends AbstractEventTrigger {
    boolean isConnect = false;

    public WebHookEventTrigger(Bot bot) {
        this.bot = bot;
    }

    public WebHookEventTrigger(Bot bot,int port,String path) {
        this.bot = bot;
        setPath(path);
        setPort(port);
    }

    public WebHookEventTrigger(Bot bot,int port) {
        this.bot = bot;
        setPort(port);
    }

    private final HttpServerProvider provider = HttpServerProvider.provider();

    private HttpServer server = null;

    /**
     * 启动监听
     */
    @SneakyThrows
    @Override
    public synchronized void start() {
        server = provider.createHttpServer(new InetSocketAddress(port), 1000);
        server.createContext(getPath(), new Handler());
        server.start();
        isConnect = true;
    }

    /**
     * 端口（默认80）
     */
    @Getter
    @Setter
    private int port = 80;

    /**
     * 解密密钥
     */
    @Getter
    @Setter
    @NonNull
    private String secretKey;

    /**
     * api路径(默认为空)
     */
    @Getter
    @Setter
    private String path = "/";

    /**
     * 关闭服务器
     */
    @Override
    public synchronized void close() {
        if (server == null || isConnect()) {
            return;
        }
        server.stop(0);
        isConnect = false;
    }

    @Override
    public boolean isConnect() {
        return server != null && isConnect;
    }

    /**
     * 处理请求
     */
    public class Handler implements HttpHandler {
        @SneakyThrows
        @Override
        public void handle(HttpExchange httpExchange) {
            InputStream ss = httpExchange.getRequestBody();
            String requestMethod = httpExchange.getRequestMethod();
            Headers responseHeaders = httpExchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "application/json;charset=utf-8");
            if (!requestMethod.equalsIgnoreCase("POST")) {
                String error = """
                        {
                          "status": -9999,
                          "textMessage": "错误的请求"
                        }
                        """;
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, error.getBytes(StandardCharsets.UTF_8).length);
                OutputStream responseBody = httpExchange.getResponseBody();
                OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
                writer.write(error);
                writer.close();
                responseBody.close();
            } else {
                InputStream requestBody = httpExchange.getRequestBody();
                int count = 0;
                while (count == 0) {
                    count = requestBody.available();
                }
                byte[] b = new byte[count];
                int readCount = 0;
                while (readCount < count) {
                    readCount += requestBody.read(b, readCount, count - readCount);
                }
                String s = new String(b);
                JSONObject json = new JSONObject(s);
                if (json.keySet().containsAll(Arrays.asList("clientId", "payload"))) {
                    String payload = json.getString("payload");
                    String event = OpenSecretUtil.WebHookDecrypt(payload, SecretKey);
                    JSONObject jsonObject = new JSONObject(Objects.requireNonNull(event));
                    if (jsonObject.getInt("type") == 2) {
                        String textMessage = "{\n" +
                                "    \"status\": 0,\n" +
                                "    \"textMessage\": \"\",\n" +
                                "    \"data\": {\n" +
                                "        \"checkCode\": \"" + jsonObject.getJSONObject("data").getString("checkCode") + "\"\n" +
                                "    }\n" +
                                "}";
                        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, textMessage.getBytes(StandardCharsets.UTF_8).length);
                        OutputStream responseBody = httpExchange.getResponseBody();
                        OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
                        writer.write(textMessage);
                        writer.close();
                        responseBody.close();
                    } else {
                        String textMessage = """
                                {
                                    "status": 0,
                                    "textMessage": ""
                                }""";
                        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, textMessage.getBytes(StandardCharsets.UTF_8).length);
                        OutputStream responseBody = httpExchange.getResponseBody();
                        OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
                        writer.write(textMessage);
                        writer.close();
                        responseBody.close();
                        DisposeEvent thread = new DisposeEvent(jsonObject);
                        thread.start();
                    }
                } else {
                    String error = """
                            {
                              "status": -9999,
                              "textMessage": "处理失败"
                            }
                            """;
                    httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, error.getBytes(StandardCharsets.UTF_8).length);
                    OutputStream responseBody = httpExchange.getResponseBody();
                    OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
                    writer.write(error);
                    writer.close();
                    responseBody.close();
                }
            }
        }
    }

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
                default -> log.warn( "未知的事件！");
            }
        }
    }
}
