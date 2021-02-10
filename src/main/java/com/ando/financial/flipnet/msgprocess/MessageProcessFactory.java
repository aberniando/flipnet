package com.ando.financial.flipnet.msgprocess;

import com.ando.financial.flipnet.model.request.MessageSendRequest;
import com.ando.financial.flipnet.model.response.MessageSendResponse;
import com.ando.financial.flipnet.msgprocess.assembler.BigflipWithdrawAssembler;
import com.ando.financial.flipnet.msgprocess.parser.BigFlipWithdrawParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

public class MessageProcessFactory {

    public static HttpRequestBase process(MessageSendRequest request, JsonNode config) {

        HttpRequestBase httpRequestBase = null;
        String instId = request.getInstId();
        String abilityCode = request.getAbilityCode();
        if (instId.equals("BIGFLIP")) {
            if (abilityCode.equals("WITHDRAW")) {
                httpRequestBase = BigflipWithdrawAssembler.assembleWithdraw(request, config);
            } else {
                httpRequestBase = BigflipWithdrawAssembler.assembleWithdrawQuery(request, config);
            }
        }

        return httpRequestBase;
    }

    public static MessageSendResponse process(HttpResponse httpResponse, MessageSendRequest request, JsonNode config) {

        MessageSendResponse messageSendResponse = null;
        String instId = request.getInstId();
        String abilityCode = request.getAbilityCode();
        if (instId.equals("BIGFLIP")) {
            if (abilityCode.equals("WITHDRAW")) {
                messageSendResponse = BigFlipWithdrawParser.parseWithdraw(httpResponse, request, config);
            } else {
                messageSendResponse = BigFlipWithdrawParser.parseWithdrawQuery(httpResponse, request, config);
            }
        }

        return messageSendResponse;
    }
}
