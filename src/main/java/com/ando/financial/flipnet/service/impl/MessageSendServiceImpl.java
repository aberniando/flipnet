package com.ando.financial.flipnet.service.impl;

import com.ando.financial.flipnet.model.request.MessageSendRequest;
import com.ando.financial.flipnet.model.response.MessageSendResponse;
import com.ando.financial.flipnet.msgprocess.MessageProcessFactory;
import com.ando.financial.flipnet.service.AppConfigService;
import com.ando.financial.flipnet.service.MessageSendService;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

@Service
public class MessageSendServiceImpl implements MessageSendService {

    @Autowired
    AppConfigService appConfigService;

    @Override
    public MessageSendResponse send(MessageSendRequest request) {

        JsonNode config = appConfigService.getConfig("communication", request.getInstId(), request.getAbilityCode());
        MessageSendResponse messageSendResponse = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpRequestBase httpRequest = MessageProcessFactory.process(request, config);
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            messageSendResponse = MessageProcessFactory.process(httpResponse, request, config);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return messageSendResponse;
    }
}
