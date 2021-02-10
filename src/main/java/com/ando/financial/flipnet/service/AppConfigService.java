package com.ando.financial.flipnet.service;

import com.ando.financial.flipnet.model.AppConfig;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public interface AppConfigService {

    JsonNode getConfig(String configType, String instId, String abilityCode);
     List<String> getAvailableChannel(String abilityCode);
     List<String> getRouteRulePriority(String abilityCode);
     Map<String, String> getChannelStatus(String abilityCode);
     Map<String, Map<String, String>> getChannelAttributeDef(String abilityCode);



}
