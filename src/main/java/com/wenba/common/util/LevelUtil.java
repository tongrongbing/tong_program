package com.wenba.common.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 15:00
 **/

public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    /**
     * @author: tongrongbing
     * @description:  添加部门就算当前部门得到level：是通过父level+"."+父ID
     * @time: 2020/7/10 3:45 下午
     * @param parentLevel
     * @param parentId
     * @return java.lang.String
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date1 = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime time = now.with(TemporalAdjusters.lastDayOfMonth());
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date1);
        System.out.println(format);
        System.out.println(time);


    }
}
