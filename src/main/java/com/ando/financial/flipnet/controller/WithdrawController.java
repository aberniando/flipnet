package com.ando.financial.flipnet.controller;

import com.ando.financial.flipnet.model.WithdrawData;
import com.ando.financial.flipnet.service.WithdrawService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class WithdrawController {

    @Autowired
    WithdrawService withdrawService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/withdraw")
    public String withdraw(){
        return "withdraw";
    }

    @RequestMapping("/withdrawQuery")
    public String withdrawQuery(){
        return "withdrawQuery";
    }

    @RequestMapping("/doWithdraw")
    public String withdraw(
            @RequestParam(name = "merchantId", required = false) String merchantId,
            @RequestParam(name = "bankCode", required = false) String bankCode,
            @RequestParam(name = "accountNumber", required = false) String accountNumber,
            @RequestParam(name = "amount", required = false) int amount,
            @RequestParam(name = "remark", required = false) String remark,
            Model model
    ){

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode withdrawRequest = objectMapper.createObjectNode();

        withdrawRequest.put("merchantId", merchantId);
        withdrawRequest.put("bankCode", bankCode);
        withdrawRequest.put("accountNumber", accountNumber);
        withdrawRequest.put("amount", amount);
        withdrawRequest.put("remark", remark);

        ObjectNode withdrawResponse = withdrawService.withdraw(withdrawRequest);
        model.addAttribute("isSuccess", withdrawResponse.get("transactionResult").asText());
        model.addAttribute("withdrawId", withdrawResponse.get("withdrawId").asText());
        return "withdrawResult";
    }

    @RequestMapping("/doWithdrawQuery")
    public String withdrawQuery(@RequestParam(name = "id")String id, Model model, String operator) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode withdrawQueryRequest = objectMapper.createObjectNode();
        withdrawQueryRequest.put("id", id);
        if (operator == null || operator.isEmpty()) {
            operator = "ADMIN";
        }
        withdrawQueryRequest.put("operator", operator);
        withdrawService.withdrawQuery(withdrawQueryRequest);
        return "withdrawQueryResult";
    }

    @RequestMapping("/getWithdrawData")
    public String getWithdrawData(Model model){
        List<WithdrawData> list = withdrawService.getWithdrawData(0, null);
        model.addAttribute("test", "ando");
        model.addAttribute("test2", list);
        return "index";
    }

}
