package com.tzy.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tzy.apiclientsdk.model.User;
import com.tzy.apiclientsdk.utils.ApiSignUtils;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {

    public  String BASE_URL = "http://localhost:8001/api/name";
    private final String accessKey;
    private final String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
    /**
     * 根据构造函数获取的Key获取基础请求头信息
     * @return headers基础的请求头信息
     */
    private Map<String, String> getHeaders(String body){
        HashMap<String, String> headers = new HashMap<>();
        // 添加 accessKey
        headers.put("accessKey", this.accessKey);
        headers.put("nonce", RandomUtil.randomNumbers(4));
        headers.put("timestamp", String.valueOf(System.currentTimeMillis()));
        headers.put("body", body);
        headers.put("sign", ApiSignUtils.getSign(this.secretKey,body));
        return headers;
    }

    public  String getNameByGet(String name) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return  HttpUtil.get(BASE_URL, paramMap);
    }

    public  String getNameByPost( String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return  HttpUtil.post(BASE_URL+"/string", paramMap);
    }

    public  String getUserNameByPost(String name) {
        User user = new User();
        user.setName(name);
        String requestBody = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(BASE_URL+"/json")
                .body(requestBody)
                .addHeaders(this.getHeaders(requestBody))
                .execute();
        return  response.body();
    }
}
