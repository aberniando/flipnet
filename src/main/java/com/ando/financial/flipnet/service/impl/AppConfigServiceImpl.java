package com.ando.financial.flipnet.service.impl;

import com.ando.financial.flipnet.model.AppConfig;
import com.ando.financial.flipnet.repository.AppConfigRepository;
import com.ando.financial.flipnet.service.AppConfigService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    AppConfigRepository appConfigRepository;


    @Override
    public JsonNode getConfig(String configType, String instId, String abilityCode) {
        AppConfig appConfig = appConfigRepository.getConfig(configType, instId, abilityCode);
        JsonNode result = null;
        try {
            String jsonString = appConfig.getValue();
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.readTree(jsonString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public List<String> getAvailableChannel(String abilityCode) {
        AppConfig appConfig = appConfigRepository.getConfig("router", "DEFAULT", abilityCode);
        List<String> result = new ArrayList<>();
        try {
            String jsonString = appConfig.getValue();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonConfig = mapper.readTree(jsonString);
            JsonNode availableChannel = jsonConfig.get("availableChannel");
            for (JsonNode node : availableChannel){
                result.add(node.textValue());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(result);
        return result;
    }

    public List<String> getRouteRulePriority(String abilityCode) {
        AppConfig appConfig = appConfigRepository.getConfig("router", "DEFAULT", abilityCode);
        List<String> result = new ArrayList<>();
        try {
            String jsonString = appConfig.getValue();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonConfig = mapper.readTree(jsonString);
            JsonNode availableChannel = jsonConfig.get("routeRulePriority");
            for (JsonNode node : availableChannel) {
                result.add(node.textValue());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(result);
        return result;
    }

    public Map<String, String> getChannelStatus(String abilityCode) {
        AppConfig appConfig = appConfigRepository.getConfig("router", "DEFAULT", abilityCode);
        Map<String, String> result = new HashMap<>();
        try {
            String jsonString = appConfig.getValue();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonConfig = mapper.readTree(jsonString);
            JsonNode channelStatus = jsonConfig.get("channelStatus");
            Iterator<String> channelStatuses = channelStatus.fieldNames();
            while (channelStatuses.hasNext()) {
                String key = channelStatuses.next();
                result.put(key, channelStatus.get(key).textValue());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(result);
        return result;
    }

    public Map<String, Map<String, String>> getChannelAttributeDef(String abilityCode) {
        AppConfig appConfig = appConfigRepository.getConfig("router", "DEFAULT", abilityCode);
        Map<String, Map<String, String>> result = new HashMap<>();
        Map<String, String> result2 = null;
        try {
            String jsonString = appConfig.getValue();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonConfig = mapper.readTree(jsonString);
            JsonNode channelAttributeDef = jsonConfig.get("channelAttributeDef");
            Iterator<String> channelList = channelAttributeDef.fieldNames();
            while (channelList.hasNext()) {
                String channelName = channelList.next();
                JsonNode channelAttribute = channelAttributeDef.get(channelName);
                Iterator<String> attributeList = channelAttribute.fieldNames();
                result2 = new HashMap<>();
                while (attributeList.hasNext()) {
                    String attribute = attributeList.next();
                    result2.put(attribute, channelAttribute.get(attribute).textValue());
                    result.put(channelName, result2);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(result);
        return result;
    }

}
