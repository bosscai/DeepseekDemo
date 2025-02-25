package org.example.demo.deepseek;

import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.example.demo.deepseek.Configuration.API_URL;

public class OllamaAPIClient {

    private static final OkHttpClient client = new OkHttpClient();


    private OllamaAPIClient() {}


    private static class Holder{
        private static final OllamaAPIClient INSTANCE = new OllamaAPIClient();
    }

    public static OllamaAPIClient getInstance(){
        return Holder.INSTANCE;
    }



    // 发送 GET 请求
    public void get(String url, Map<String, String> params, final RequestCallback callback) {
        String fullUrl = buildUrlWithParams(url, params);
        Request request = new Request.Builder()
                .url(fullUrl)
                .build();
        sendRequest(request, callback);
    }

    // 发送 POST 请求
    public void post(String url, Map<String, String> params, final RequestCallback callback) {
        String json = JSONObject.toJSONString(params);
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        sendRequest(request, callback);
    }

    // 构建带有参数的 URL
    private String buildUrlWithParams(String url, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (!url.contains("?")) {
            sb.append("?");
        } else {
            if (!url.endsWith("?")) {
                sb.append("&");
            }
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 发送请求
    private void sendRequest(Request request, final RequestCallback callback) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    callback.onError(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback != null) {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        callback.onSuccess(result);
                    } else {
                        callback.onError("Request failed with code: " + response.code());
                    }
                }
            }
        });
    }

    // 请求回调接口
    public interface RequestCallback {
        void onSuccess(String result);
        void onError(String error);
    }

    public static void main(String[] args) {
        Configuration.Builder builder = new Configuration.Builder();
        builder.setLLM(LLM.DEEPSEEK_R1_1_5B);
        builder.setPrompt("你好");
        Configuration build = builder.build();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("model", build.getModel().getName());
        stringStringHashMap.put("prompt", build.getPrompt());
        String jsonString = JSONObject.toJSONString(stringStringHashMap);
        System.out.println(jsonString);

        OllamaAPIClient.getInstance().post(API_URL, stringStringHashMap, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("res" + result);
            }

            @Override
            public void onError(String error) {
                System.out.println("error:" + error);
            }
        });
    }
}
