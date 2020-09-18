package com.wenba.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author：tongrongbing
 * @date：created in 2020/8/31 12:13 下午
 * @description：
 */
public class TimeTest {
    public static void main(String[] args) throws Exception{
        //Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        //System.out.println(sdf.format(date));
        Date parse = sdf.parse("2020-07-29 17:23:52");
        System.out.println(parse);
        System.out.println(parse.getTime());

      /*  Date parse2 = sdf.parse("2020-09-12 00:00:00");
        System.out.println(parse2);
        System.out.println(parse2.getTime());

        Date parse1 = sdf.parse("2020-08-31 08:10:00");
        System.out.println(parse1);
        System.out.println(parse1.getTime());*/

        System.out.println(7 & 8);
    }
}
