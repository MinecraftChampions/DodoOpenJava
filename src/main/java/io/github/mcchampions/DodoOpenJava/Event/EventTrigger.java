package io.github.mcchampions.DodoOpenJava.Event;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.events.*;
import okhttp3.*;
import okio.ByteString;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 事件触发
 */
public class EventTrigger {
    static EventTrigger p;
    static String wssLo="";
    static OkHttpClient okHttpClient = new OkHttpClient();
    static OkHttpClient wss=new OkHttpClient.Builder()
            .pingInterval(30, TimeUnit.SECONDS) //保活心跳
            .build();
    static WebSocket mWebSocket;
    public static void main(String Authorization) {
        Request requestc = new Request.Builder().url("https://botopen.imdodo.com/api/v1/websocket/connection").addHeader("Content-Type", "application/json").addHeader("Authorization", Authorization)
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .build();

        okHttpClient.newCall(requestc).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                wssLo= JSONObject.parseObject(Objects.requireNonNull(response.body()).string()).getJSONObject("data").getString("endpoint");
                //TODO 建立wss链接
                //getLogger().info(wssLo);
                response.close();
                Request request = new Request.Builder()
                        .url(wssLo).build();
                mWebSocket = wss.newWebSocket(request, new WsListenerC(p));//TODO 这里是处理wss发来的数据
            }
        });
    }

    private static class WsListenerC extends WebSocketListener {
        EventTrigger p;
        public WsListenerC(EventTrigger dodo) {
            p=dodo;
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            JSONObject jsontext = JSONObject.parseObject(bytes.utf8());
            EventManage event = new EventManage();
            EventObject e= switch (jsontext.getJSONObject("data").getString("eventType")) {
                case "1001" -> new PersonalMessageEvent(jsontext);
                case "2001" -> new MessageEvent(jsontext);
                case "3001" -> new MessageReactionEvent(jsontext);
                case "3002" -> new CardMessageButtonClickEvent(jsontext);
                case "3003" -> new CardMessageFormSubmitEvent(jsontext);
                case "3004" -> new CardMessageListSubmitEvent(jsontext);
                case "4001" -> new MemberJoinEvent(jsontext);
                case "4002" -> new MemberLeaveEvent(jsontext);
                default -> new EventObject() {};
            };
            event.trigger(e);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            mWebSocket.close(1000,"正常关闭");
        }
    }
}
