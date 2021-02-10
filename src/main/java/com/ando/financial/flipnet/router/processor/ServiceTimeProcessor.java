package com.ando.financial.flipnet.router.processor;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class ServiceTimeProcessor {

    public static List<String> process(List<String> availableChannel, Map<String, Map<String, String>> processables){
        List<String> filteredResult = new ArrayList<>();
        Date now = new Date();

        for (String channel : availableChannel) {
            Map<String, String> channelAttr = processables.get(channel);
            String attr = channelAttr.get("SERVICE_TIME");
            attr = attr.replaceAll(" ","");
            String timeStart = attr.split("-")[0];
            String timeEnd = attr.split("-")[1];

            Calendar calendarStart = Calendar.getInstance();
            Calendar calendarEnd = Calendar.getInstance();

            calendarStart.setTime(new Date());
            calendarStart.set(Calendar.HOUR, Integer.valueOf(timeStart.split(":")[0]));
            calendarStart.set(Calendar.MINUTE, Integer.valueOf(timeStart.split(":")[1]));
            calendarStart.set(Calendar.SECOND, 0);

            calendarEnd.setTime(new Date());
            calendarEnd.set(Calendar.HOUR, Integer.valueOf(timeEnd.split(":")[0]));
            calendarEnd.set(Calendar.MINUTE, Integer.valueOf(timeEnd.split(":")[1]));
            calendarEnd.set(Calendar.MINUTE, Integer.valueOf(timeEnd.split(":")[1]));
            calendarEnd.set(Calendar.SECOND, 0);

            Date start = calendarStart.getTime();
            Date end = calendarEnd.getTime();

            if (now.after(start) && now.before(end)) {
                filteredResult.add(channel);
            }
        }
        return filteredResult;
    }
}
