package io.github.minecraftchampions.dodoopenjava.event;

import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.HttpServerProvider;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.utils.OpenSecretUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 事件触发
 *
 * @author qscbm187531
 */
@Slf4j
public class WebHookEventTrigger extends AbstractEventTrigger {
    boolean isConnect;

    public WebHookEventTrigger(@NonNull Bot bot, @NonNull String secretKey,
                               @NonNull String password, @NonNull File file) {
        this.bot = bot;
        setSecretKey(secretKey);
        setPassword(password);
        setFile(file);
    }

    public WebHookEventTrigger(@NonNull Bot bot, int port, @NonNull String path,
                               @NonNull String secretKey, @NonNull String password,
                               @NonNull File file) {
        this.bot = bot;
        setPath(path);
        setPort(port);
        setSecretKey(secretKey);
        setPassword(password);
        setFile(file);
    }

    public WebHookEventTrigger(@NonNull Bot bot, int port, @NonNull String secretKey,
                               @NonNull String password, @NonNull File file) {
        this.bot = bot;
        setPort(port);
        setSecretKey(secretKey);
        setPassword(password);
        setFile(file);
    }

    public WebHookEventTrigger(@NonNull Bot bot, @NonNull String path, @NonNull String secretKey,
                               @NonNull String password, @NonNull File file) {
        this.bot = bot;
        setPath(path);
        setSecretKey(secretKey);
        setPassword(password);
        setFile(file);
    }

    private final HttpServerProvider provider = HttpServerProvider.provider();

    private HttpsServer server;

    /**
     * 启动监听
     */
    @SneakyThrows
    @Override
    public synchronized void start() {
        if (isConnect) {
            return;
        }
        server = provider.createHttpsServer(new InetSocketAddress(port), 1000);
        server.createContext(path, new Handler());
        server.setExecutor(new ThreadPoolExecutor(3, 10, 3,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy()));
        isConnect = true;
        String type = file.getName().substring(file.getName().lastIndexOf('.') + 1).toUpperCase();
        KeyStore keyStore = KeyStore.getInstance(type);
        keyStore.load(new FileInputStream(file), password.toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, password.toCharArray());
        KeyManager[] km = keyManagerFactory.getKeyManagers();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(km, null, null);
        sslContext.getSupportedSSLParameters().setNeedClientAuth(false);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        server.start();
    }

    /**
     * 端口（默认443）
     */
    @Getter
    @Setter
    private int port = 443;

    /**
     * 解密密钥
     */
    @Getter
    @Setter
    @NonNull
    private String secretKey;

    /**
     * 证书密码
     */
    @Getter
    @Setter
    @NonNull
    private String password;

    /**
     * 证书文件(后缀为 .jks或其它 的 文件)
     */
    @Getter
    @Setter
    @NonNull
    private File file;

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
        if (server == null || isConnect) {
            return;
        }
        server.stop(0);
        isConnect = false;
    }

    @Override
    public boolean isConnect() {
        return isConnect;
    }

    /**
     * 处理请求
     */
    public class Handler implements HttpHandler {
        public static final String REQUEST_METHOD = "POST";

        public static final int VERIFY_CALLBACK_TYPE = 2;

        public static final String CALLBACK_TYPE_KEY = "type";


        @Override
        public void handle(HttpExchange httpExchange) {
            try {
                httpExchange.getRequestBody();
                String requestMethod = httpExchange.getRequestMethod();
                Headers responseHeaders = httpExchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json;charset=utf-8");
                if (!REQUEST_METHOD.equals(requestMethod)) {
                    String error = """
                            {
                              "status": -9999,
                              "message": "错误的请求"
                            }
                            """;
                    OutputStream responseBody = httpExchange.getResponseBody();
                    OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
                    writer.write(error);
                    writer.close();
                    responseBody.close();
                } else {
                    InputStream requestBody = httpExchange.getRequestBody();
                    String s = new String(requestBody.readAllBytes());
                    JSONObject json = new JSONObject(s);
                    String payload = json.getString("payload");
                    CompletableFuture<JSONObject> future = CompletableFuture.supplyAsync(() -> {
                        String event = OpenSecretUtils.webHookDecrypt(payload, secretKey);
                        return new JSONObject(Objects.requireNonNull(event));
                    });
                    JSONObject mJson = new JSONObject("""
                            {
                                "status": 0,
                                "message": ""
                            }""");
                    String m = mJson.toString();
                    int length = m.getBytes().length;
                    JSONObject jsonObject = future.get();
                    if (jsonObject.getInt(CALLBACK_TYPE_KEY) == VERIFY_CALLBACK_TYPE) {
                        String textMessage = "{\n" +
                                             "    \"status\": 0,\n" +
                                             "    \"message\": \"\",\n" +
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
                        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, length);
                        OutputStream responseBody = httpExchange.getResponseBody();
                        OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
                        writer.write(m);
                        writer.close();
                        responseBody.close();
                        getBot().getEventManager().parseAndFireEvent(jsonObject);
                    }

                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}