package com.ando.financial.flipnet.service;

import com.ando.financial.flipnet.model.WithdrawData;
import com.ando.financial.flipnet.model.response.WithdrawResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public interface WithdrawService {

    ObjectNode withdraw(ObjectNode request);
    ObjectNode withdrawQuery(ObjectNode request);
    List<WithdrawData> getWithdrawData(int id, String merchantId);
    void scheduledWithdrawQuery();

}
