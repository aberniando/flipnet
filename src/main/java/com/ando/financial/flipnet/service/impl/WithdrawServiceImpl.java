package com.ando.financial.flipnet.service.impl;

import com.ando.financial.flipnet.service.MessageSendService;
import com.ando.financial.flipnet.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    MessageSendService messageSendService;


    @Override
    public String withdraw(String request) {
        return null;
    }

    @Override
    public String withdrawQuery(String request) {
        return null;
    }
}
