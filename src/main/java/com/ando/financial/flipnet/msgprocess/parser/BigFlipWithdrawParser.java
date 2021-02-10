package com.ando.financial.flipnet.msgprocess.parser;

import com.ando.financial.flipnet.etc.BusinessStatusCodeEnum;
import com.ando.financial.flipnet.model.request.MessageSendRequest;
import com.ando.financial.flipnet.model.response.MessageSendResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class BigFlipWithdrawParser {

    public static MessageSendResponse parseWithdraw(HttpResponse httpResponse, MessageSendRequest request, JsonNode config) {
        MessageSendResponse messageSendResponse = new MessageSendResponse();
        messageSendResponse.setMessage((new ObjectMapper()).createObjectNode());
        try {
            String rawResponseMessage = EntityUtils.toString(httpResponse.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(rawResponseMessage);
            String sendResultCode = null;
            String transactionResultCode = null;
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                sendResultCode = BusinessStatusCodeEnum.SUCCESS.getCode();
            }
            if (jsonNode.get("status").textValue().equals("PENDING")) {
                transactionResultCode = BusinessStatusCodeEnum.PENDING.getCode();
            }
            messageSendResponse.setTransmissionResult(sendResultCode);
            messageSendResponse.setTransactionResult(transactionResultCode);
            messageSendResponse.setRequestMessage(request.getMessage());
            messageSendResponse.getMessage().put("receipt", jsonNode.get("receipt").textValue());
            messageSendResponse.getMessage().put("rawResponseMessage", jsonNode.toString());
            messageSendResponse.getMessage().put("timeServed", jsonNode.get("time_served").textValue());
            messageSendResponse.getMessage().put("externalSerialNo", String.valueOf(jsonNode.get("id").asInt()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return messageSendResponse;
    }

    public static MessageSendResponse parseWithdrawQuery(HttpResponse httpResponse, MessageSendRequest request, JsonNode config) {
        MessageSendResponse messageSendResponse = new MessageSendResponse();
        messageSendResponse.setMessage((new ObjectMapper()).createObjectNode());
        try {
            String rawResponseMessage = EntityUtils.toString(httpResponse.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(rawResponseMessage);
            String sendResultCode = null;
            String transactionResultCode = null;
            String origTransactionResultCode = null;
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                sendResultCode = BusinessStatusCodeEnum.SUCCESS.getCode();
                transactionResultCode = BusinessStatusCodeEnum.SUCCESS.getCode();
            }
            if (jsonNode.get("status").textValue().equals("SUCCESS")) {
                origTransactionResultCode = BusinessStatusCodeEnum.SUCCESS.getCode();
            } else {
                origTransactionResultCode = BusinessStatusCodeEnum.PENDING.getCode();
            }
            messageSendResponse.setTransmissionResult(sendResultCode);
            messageSendResponse.setTransactionResult(transactionResultCode);
            messageSendResponse.setRequestMessage(request.getMessage());
            messageSendResponse.getMessage().put("receipt", jsonNode.get("receipt").textValue());
            messageSendResponse.getMessage().put("rawResponseMessage", jsonNode.toString());
            messageSendResponse.getMessage().put("timeServed", jsonNode.get("time_served").textValue());
            messageSendResponse.getMessage().put("origTransactionResultCode", origTransactionResultCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return messageSendResponse;
    }
}
