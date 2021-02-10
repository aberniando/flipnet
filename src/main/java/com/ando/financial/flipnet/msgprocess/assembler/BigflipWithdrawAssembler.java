package com.ando.financial.flipnet.msgprocess.assembler;

import com.ando.financial.flipnet.model.request.MessageSendRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BigflipWithdrawAssembler {

    public static HttpRequestBase assembleWithdraw(MessageSendRequest request, JsonNode config) {
        String url = config.get("url").asText();
        HttpPost httpPost = new HttpPost(url);
        String username = config.get("username").asText();
        String password = "";
        String authorization = username + ":" + password;
        authorization = Base64.getEncoder().encodeToString(authorization.getBytes());
        authorization = "Basic " + authorization;

        try {
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpPost.addHeader(HttpHeaders.AUTHORIZATION, authorization);

            List<NameValuePair> params = new ArrayList<>();
            ObjectNode message = request.getMessage();
            params.add(new BasicNameValuePair("bank_code", message.get("bankCode").textValue()));
            params.add(new BasicNameValuePair("amount", String.valueOf(message.get("amount").asInt())));
            params.add(new BasicNameValuePair("remark", message.get("remark").textValue()));
            params.add(new BasicNameValuePair("account_number", message.get("accountNumber").textValue()));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            message.put("rawExternalRequestMessage", EntityUtils.toString(httpPost.getEntity()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return httpPost;
    }

    public static HttpRequestBase assembleWithdrawQuery(MessageSendRequest request, JsonNode config) {
        String url = config.get("url").asText();
        url = url + request.getMessage().get("origExternalSerialNo").textValue();
        HttpGet httpGet = new HttpGet(url);
        String username = config.get("username").asText();
        String password = "";
        String authorization = username + ":" + password;
        authorization = Base64.getEncoder().encodeToString(authorization.getBytes());
        authorization = "Basic " + authorization;

        try {
            httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpGet.addHeader(HttpHeaders.AUTHORIZATION, authorization);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return httpGet;
    }
}
