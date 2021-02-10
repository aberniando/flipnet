package com.ando.financial.flipnet.router;

import com.ando.financial.flipnet.router.processor.ChannelStatusProcessor;
import com.ando.financial.flipnet.router.processor.ServiceTimeProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ProcessorGateway {

    public static List<String> process(String channelAttribute, List<String> availableChannel, Map<String, Map<String, String>> processable){
        List<String> filteredResult = null;
        if (channelAttribute.equals("CHANNEL_STATUS")) {
            filteredResult = ChannelStatusProcessor.process(availableChannel, processable);
        } else if (channelAttribute.equals("SERVICE_TIME")) {
            filteredResult = ServiceTimeProcessor.process(availableChannel,processable);
        }
        return filteredResult;
    }
}
