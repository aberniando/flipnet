package com.ando.financial.flipnet.repository;

import com.ando.financial.flipnet.model.FlipnetSerial;

import java.util.List;
import java.util.Map;

public interface FlipnetSerialRepository {

    long insert(FlipnetSerial flipnetSerial);

    void withdrawUpdate(Map<String, String> param);
    void withdrawQueryUpdate(Map<String, String> param);

    List<FlipnetSerial> getWithdrawData(int id, String merchantId);
    List<FlipnetSerial> getPendingWithdrawData();


}
