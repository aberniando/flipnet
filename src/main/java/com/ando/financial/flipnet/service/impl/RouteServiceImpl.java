package com.ando.financial.flipnet.service.impl;

import com.ando.financial.flipnet.router.ProcessorGateway;
import com.ando.financial.flipnet.service.AppConfigService;
import com.ando.financial.flipnet.service.RouteService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    AppConfigService appConfigService;

    @Override
    public String route(String abilityCode) {
        List<String> availableChannel = appConfigService.getAvailableChannel(abilityCode);
        List<String> routeRulePriority = appConfigService.getRouteRulePriority(abilityCode);
        Map<String, Map<String, String>> channelAttributeDef = appConfigService.getChannelAttributeDef(abilityCode);

        for (String routeRule : routeRulePriority) {
            availableChannel = ProcessorGateway.process(routeRule, availableChannel, channelAttributeDef);
        }

        return availableChannel.get(0);
    }
}
