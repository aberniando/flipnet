package com.ando.financial.flipnet.mapper;

import com.ando.financial.flipnet.model.AppConfig;

public interface AppConfigMapper {

    AppConfig getConfig(String configType, String instId, String abilityCode);
}
