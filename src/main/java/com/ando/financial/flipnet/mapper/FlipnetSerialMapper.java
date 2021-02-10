package com.ando.financial.flipnet.mapper;

import com.ando.financial.flipnet.model.FlipnetSerial;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FlipnetSerialMapper {

    long insert(FlipnetSerial flipnetSerial);
    void withdrawUpdate(Map<String, String> param);
    void withdrawQueryUpdate(Map<String, String> param);
    List<FlipnetSerial> getWithdrawData(int id, String merchantId);
    List<FlipnetSerial> getPendingWithdraw(Date start, Date end);
}
