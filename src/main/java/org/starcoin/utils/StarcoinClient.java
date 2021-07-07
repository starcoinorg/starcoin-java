package org.starcoin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.UUID;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class StarcoinClient {

  public StarcoinClient(String url) {
    this.baseUrl = url;
  }

  public static final MediaType JSON_MEDIA_TYPE
      = MediaType.parse("application/json; charset=utf-8");

  private String baseUrl;
  private OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .build();

  @SneakyThrows
  public String call(String method, List<String> params) {
    JSONObject jsonBody = new JSONObject();
    jsonBody.put("jsonrpc", "2.0");
    jsonBody.put("method", method);
    jsonBody.put("id", UUID.randomUUID().toString());
    jsonBody.put("params", params);
    RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, jsonBody.toString());
    Request request = new Request.Builder().post(body).url(this.baseUrl).build();
    Response response = okHttpClient.newCall(request).execute();
    System.out.println();
    return response.body().string();
  }
}
