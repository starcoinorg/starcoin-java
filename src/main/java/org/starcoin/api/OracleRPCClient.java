package org.starcoin.api;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.starcoin.bean.OracleTokenPair;

import java.io.IOException;
import java.util.List;

public class OracleRPCClient {

    private String baseUrl;

    public OracleRPCClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<OracleTokenPair> getOracleTokenPair() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(baseUrl+"/v1/priceFeeds")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            return JSON.parseArray(res,OracleTokenPair.class);
        }
    }
}
