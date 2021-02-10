package com.ando.financial.flipnet.repository.impl;

import com.ando.financial.flipnet.etc.SessionHelper;
import com.ando.financial.flipnet.mapper.FlipnetSerialMapper;
import com.ando.financial.flipnet.repository.FlipnetSerialRepository;
import com.ando.financial.flipnet.model.FlipnetSerial;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class FlipnetSerialRepositoryImpl implements FlipnetSerialRepository {

    private void commitAndClose(SqlSession sqlSession) {
        sqlSession.commit();
        sqlSession.close();
    }

    public long insert(FlipnetSerial flipnetSerial){
        try {
            SqlSession sqlSession = SessionHelper.getSqlSession();
            FlipnetSerialMapper flipnetSerialMapper = sqlSession.getMapper(FlipnetSerialMapper.class);
            flipnetSerialMapper.insert(flipnetSerial);
            commitAndClose(sqlSession);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return flipnetSerial.getId();
    }

    @Override
    public void withdrawUpdate(Map<String, String> param) {
        try {
            SqlSession sqlSession = SessionHelper.getSqlSession();
            FlipnetSerialMapper flipnetSerialMapper = sqlSession.getMapper(FlipnetSerialMapper.class);
            flipnetSerialMapper.withdrawUpdate(param);
            commitAndClose(sqlSession);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void withdrawQueryUpdate(Map<String, String> param) {
        try {
            SqlSession sqlSession = SessionHelper.getSqlSession();
            FlipnetSerialMapper flipnetSerialMapper = sqlSession.getMapper(FlipnetSerialMapper.class);
            flipnetSerialMapper.withdrawQueryUpdate(param);
            commitAndClose(sqlSession);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<FlipnetSerial> getWithdrawData(int id, String merchantId) {
        List<FlipnetSerial> result = null;
        try {
            SqlSession sqlSession = SessionHelper.getSqlSession();
            FlipnetSerialMapper flipnetSerialMapper = sqlSession.getMapper(FlipnetSerialMapper.class);
            result = flipnetSerialMapper.getWithdrawData(id, merchantId);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<FlipnetSerial> getPendingWithdrawData() {
        List<FlipnetSerial> result = null;
        try {
            SqlSession sqlSession = SessionHelper.getSqlSession();
            FlipnetSerialMapper flipnetSerialMapper = sqlSession.getMapper(FlipnetSerialMapper.class);
            Date end = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(end);
            calendar.add(Calendar.HOUR, -1);
            Date start = calendar.getTime();
            result = flipnetSerialMapper.getPendingWithdraw(start, end);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }



}
