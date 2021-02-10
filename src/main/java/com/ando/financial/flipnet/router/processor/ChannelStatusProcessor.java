package com.ando.financial.flipnet.router.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChannelStatusProcessor {

    public static List<String> process(List<String> availableChannel, Map<String, Map<String,String>> processables) {
        Map<String, String> processable = processables.get("CHANNEL_STATUS");
        List<String> filteredResult = new ArrayList<>();
        for (String channel : availableChannel) {
            String status = processables.get(channel).get("CHANNEL_STATUS");
            if (status.equals("Y")) {
                filteredResult.add(channel);
            }
        }
        return filteredResult;
    }
}
