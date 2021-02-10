package com.ando.financial.flipnet.repository.impl;

import com.ando.financial.flipnet.etc.SessionHelper;
import com.ando.financial.flipnet.mapper.AppConfigMapper;
import com.ando.financial.flipnet.model.AppConfig;
import com.ando.financial.flipnet.repository.AppConfigRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AppConfigRepositoryImpl implements AppConfigRepository {

    @Override
    public AppConfig getConfig(String configType, String instId, String abilityCode) {
        AppConfig result = null;
        try {
            SqlSession sqlSession = SessionHelper.getSqlSession();
            AppConfigMapper appConfigMapper = sqlSession.getMapper(AppConfigMapper.class);
            result = appConfigMapper.getConfig(configType, instId, abilityCode);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}

