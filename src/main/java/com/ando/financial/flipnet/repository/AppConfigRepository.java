package com.ando.financial.flipnet.repository;

import com.ando.financial.flipnet.model.AppConfig;

public interface AppConfigRepository {

    AppConfig getConfig(String configType, String instId, String abilityCode);
}
