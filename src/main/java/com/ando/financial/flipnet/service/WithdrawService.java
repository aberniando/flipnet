package com.ando.financial.flipnet.service;

import com.ando.financial.flipnet.model.request.WithdrawRequest;

public interface WithdrawService {

    String withdraw(WithdrawRequest request);
    String withdrawQuery(String request);

}
