package com.ando.financial.flipnet.service.impl;

import com.ando.financial.flipnet.etc.BusinessStatusCodeEnum;
import com.ando.financial.flipnet.model.WithdrawData;
import com.ando.financial.flipnet.model.request.MessageSendRequest;
import com.ando.financial.flipnet.model.response.MessageSendResponse;
import com.ando.financial.flipnet.model.response.WithdrawResponse;
import com.ando.financial.flipnet.repository.FlipnetSerialRepository;
import com.ando.financial.flipnet.model.FlipnetSerial;
import com.ando.financial.flipnet.service.AppConfigService;
import com.ando.financial.flipnet.service.MessageSendService;
import com.ando.financial.flipnet.service.RouteService;
import com.ando.financial.flipnet.service.WithdrawService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    FlipnetSerialRepository flipnetSerialRepository;

    @Autowired
    AppConfigService appConfigService;

    @Autowired
    RouteService routeService;


    @Override
    public ObjectNode withdraw(ObjectNode request) {
        //Iniate data before send message to external party
        String inst = routeService.route("WITHDRAW");
        if (inst == null || inst.isEmpty()) {
            return null;
        }
        request.put("gmtCreated", String.valueOf(new Date()));
        request.put("gmtModified", String.valueOf(new Date()));
        request.put("abilityCode", "WITHDRAW");
        request.put("instId", inst);
        request.put("externalSerialNo", "");

        FlipnetSerial flipnetSerial = new FlipnetSerial();
        flipnetSerial.setMerchantId(request.get("merchantId").asText());
        flipnetSerial.setGmtCreated(new Date());
        flipnetSerial.setGmtModified(new Date());
        flipnetSerial.setAbilityCode(request.get("abilityCode").asText());
        flipnetSerial.setInstId(request.get("instId").asText());
        flipnetSerial.setExchangeAmount(request.get("amount").asInt());
        flipnetSerial.setOperator("USER");
        flipnetSerial.setExternalSerialNo(request.get("externalSerialNo").asText());
        flipnetSerial.setStatus(BusinessStatusCodeEnum.RECEIVE_SUCCESS.getCode());
        flipnetSerial.setRequestMessage(request.toString());

        long id = flipnetSerialRepository.insert(flipnetSerial);

        MessageSendRequest messageSendRequest = new MessageSendRequest();
        messageSendRequest.setInstId(request.get("instId").asText());
        messageSendRequest.setAbilityCode(request.get("abilityCode").asText());
        messageSendRequest.setMessage(request);

        MessageSendResponse messageSendResponse = messageSendService.send(messageSendRequest);

        String transmissionResult = messageSendResponse.getTransmissionResult();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode result = objectMapper.createObjectNode();

        if (messageSendResponse != null && transmissionResult.equals(BusinessStatusCodeEnum.SUCCESS.getCode())) {
            ObjectNode responseMessage = messageSendResponse.getMessage();
            ObjectNode origRequestMessage = messageSendResponse.getRequestMessage();

            result.put("withdrawId", id);
            result.put("transactionResult", BusinessStatusCodeEnum.SUCCESS.getCode());

            Map<String,String> param = new HashMap<>();
            param.put("status", messageSendResponse.getTransactionResult());
            param.put("responseMessage", responseMessage.toString());
            param.put("requestMessage", origRequestMessage.toString());
            param.put("id",String.valueOf(id));

            if (responseMessage.get("externalSerialNo") != null) {
                param.put("externalSerialNo", responseMessage.get("externalSerialNo").textValue());
            }

            flipnetSerialRepository.withdrawUpdate(param);

        } else {
            result.put("transactionResult", BusinessStatusCodeEnum.FAILED.getCode());
        }

        return result;
    }

    @Override
    public ObjectNode withdrawQuery(ObjectNode request) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode result = objectMapper.createObjectNode();

        int id = request.get("id").asInt();
        List<FlipnetSerial> originalTransactions = flipnetSerialRepository.getWithdrawData(id, null);

        request.put("abilityCode","WITHDRAWQUERY");
        request.put("instId","BIGFLIP");


        FlipnetSerial originalTransaction = null;
        if (originalTransactions == null || originalTransactions.size() == 0) {

        } else {
            originalTransaction = originalTransactions.get(0);
            request.put("origExternalSerialNo", originalTransaction.getExternalSerialNo());
            FlipnetSerial flipnetSerial = new FlipnetSerial();
            flipnetSerial.setMerchantId(originalTransaction.getMerchantId());
            flipnetSerial.setGmtCreated(new Date());
            flipnetSerial.setGmtModified(new Date());
            flipnetSerial.setAbilityCode(request.get("abilityCode").asText());
            flipnetSerial.setInstId(request.get("instId").asText());
            flipnetSerial.setExchangeAmount(originalTransaction.getExchangeAmount());
            flipnetSerial.setOperator(request.get("operator").asText());
            flipnetSerial.setStatus(BusinessStatusCodeEnum.RECEIVE_SUCCESS.getCode());
            flipnetSerial.setRequestMessage(request.toString());
            flipnetSerial.setReferenceId(originalTransaction.getId());

            long generatedId = flipnetSerialRepository.insert(flipnetSerial);

            MessageSendRequest messageSendRequest = new MessageSendRequest();
            messageSendRequest.setInstId(request.get("instId").asText());
            messageSendRequest.setAbilityCode(request.get("abilityCode").asText());
            messageSendRequest.setMessage(request);

            MessageSendResponse messageSendResponse = messageSendService.send(messageSendRequest);

            String transmissionResult = messageSendResponse.getTransmissionResult();



            if (messageSendResponse != null && transmissionResult.equals(BusinessStatusCodeEnum.SUCCESS.getCode())) {
                ObjectNode responseMessage = messageSendResponse.getMessage();
                ObjectNode origRequestMessage = messageSendResponse.getRequestMessage();

                String origTransactionResultCode = responseMessage.get("origTransactionResultCode").asText();
                result.put("withdrawId", id);
                result.put("transactionResult", origTransactionResultCode);

                Map<String,String> param = new HashMap<>();
                param.put("status", messageSendResponse.getTransactionResult());
                param.put("responseMessage", responseMessage.toString());
                param.put("id",String.valueOf(generatedId));

                flipnetSerialRepository.withdrawUpdate(param);

                param.clear();
                param.put("id",String.valueOf(id));
                param.put("status", origTransactionResultCode);
                param.put("receipt", responseMessage.get("receipt").textValue());
                param.put("timeServed", responseMessage.get("timeServed").textValue());
                flipnetSerialRepository.withdrawQueryUpdate(param);

            } else {
                result.put("transactionResult", BusinessStatusCodeEnum.FAILED.getCode());
            }
        }
        return result;
    }

    @Override
    public List<WithdrawData> getWithdrawData(int id, String merchantId) {
        List<FlipnetSerial> flipnetSerials = flipnetSerialRepository.getWithdrawData(id,merchantId);
        List<WithdrawData> result = new ArrayList<>();
        for (FlipnetSerial data : flipnetSerials) {
            WithdrawData withdrawData = new WithdrawData();
            withdrawData.setBankCode(data.getInstId());
            withdrawData.setId(data.getId());
            withdrawData.setMerchantId(data.getMerchantId());
            withdrawData.setLastUpdated(data.getGmtModified());
            withdrawData.setReceipt(data.getReceipt());
            result.add(withdrawData);
        }
        return result;
    }

    @Override
    @Scheduled(fixedRate = 5000)
    public void scheduledWithdrawQuery() {

        List<FlipnetSerial> flipnetSerials = flipnetSerialRepository.getPendingWithdrawData();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode withdrawQueryRequest = objectMapper.createObjectNode();
        withdrawQueryRequest.put("operator", "SCHEDULER");
        if (flipnetSerials != null && flipnetSerials.size() > 0) {
            for (FlipnetSerial flipnetSerial : flipnetSerials) {
                withdrawQueryRequest.put("id", String.valueOf(flipnetSerial.getId()));
                withdrawQuery(withdrawQueryRequest);
            }
        }
    }


}
