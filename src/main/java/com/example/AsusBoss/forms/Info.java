package com.example.AsusBoss.forms;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Info {
    private Map<Integer, Long> timeMap = new HashMap<>();

    public void setLastTime(int number) {
        Date date = new Date();

        long timeInMills = date.getTime();
        timeMap.put(number, timeInMills);
    }

    public String getAbsencePeriod (int number) {
        if (timeMap.get(number) != null) {
            Date date = new Date();
            String ret;
            long lastTimeInMills = timeMap.get(number);
            long currentTimeInMills = date.getTime();
            long diffInMills = currentTimeInMills - lastTimeInMills;
            int hours = (int) (diffInMills / (60 * 60 * 1000));

            ret = hours + "ч:" + ((diffInMills - (hours * 3600 * 1000)) / (60 * 1000)) + "м";
            return ret;
        } else
            return "∞";
    }
}
