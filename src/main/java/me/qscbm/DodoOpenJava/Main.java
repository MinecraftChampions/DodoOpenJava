package me.qscbm.DodoOpenJava;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okio.ByteString;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main{
    String url = "https://botopen.imdodo.com/api/v1/channel/message/send";
    static String urlAboutGetWSSURL="https://botopen.imdodo.com/api/v1/websocket/connection";
    static Main p;
    static String wssLo="";
    static OkHttpClient okHttpClient = new OkHttpClient();
    static OkHttpClient wss=new OkHttpClient.Builder()
            .pingInterval(30, TimeUnit.SECONDS) //保活心跳
            .build();
    static WebSocket mWebSocket;
    public static void main(String[] args) {
        Request requestc = new Request.Builder().url(urlAboutGetWSSURL).addHeader("Content-Type", "application/json").addHeader("Authorization", "Bot 47657182.NDc2NTcxODI.77-9L--_ve-_vQ.ibg-kbMvvZ96BlEsQFi_Cf38OY7BwQ1Uxqr_UCyXD50")
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .build();

        okHttpClient.newCall(requestc).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                wssLo=JSONObject.parseObject(response.body().string()).getJSONObject("data").getString("endpoint");
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
        Main p;
        public WsListenerC(Main gameToDoDoChat) {
            p=gameToDoDoChat;
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            String jsontext=bytes.utf8();
            String id=JSONObject.parseObject(jsontext).getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
            System.out.println(id);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            mWebSocket.close(1000,"");
        }
    }
}