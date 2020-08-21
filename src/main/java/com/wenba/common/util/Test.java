package com.wenba.common.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-08-07 11:05
 **/

public class Test {
    public static void main(String[] args) {
        /*String expression = "string.contains(first_channel,\"nw\") && string.contains(second_channel,\"顾问\") && area != \"北京\"";*/
        String expression = "string.contains(second_channel,\"家长端\")";
        //String expression = "first_channel == 1";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<String, Object>();
        //env.put(RuleDimension.FIRST_CHANNEL.getRuleDimensionName(),"官网_家长端_社群_头条");
        env.put(RuleDimension.SECOND_CHANNEL.getRuleDimensionName(),"z官网_家长端_社群_头条w");
       /* env.put(RuleDimension.CITY.getRuleDimensionName(),"北京");
        env.put(RuleDimension.RULE_GRADE.getRuleDimensionName(),"高一说说");*/
        Boolean executeRes = (Boolean)compiledExp.execute(env);
        if(executeRes){
            System.out.println("ssss");
        }
        System.out.println(executeRes);

    }
}


enum RuleDimension{
    FIRST_CHANNEL{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_FIRST_CHANNEL;
        }
    },
    SECOND_CHANNEL{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_SECOND_CHANNEL;
        }
    },
    CHANNEL_NAME{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_CHANNEL_NAME;
        }
    },
    CITY{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.CITY;
        }
    },
    RULE_GRADE{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_GRADE;
        }
    }
    ;
    public abstract String getRuleDimensionName();
}
interface RuleDimensionContains{
    String RULE_FIRST_CHANNEL = "first_channel";
    String RULE_SECOND_CHANNEL = "second_channel";
    String RULE_CHANNEL_NAME = "channel_name";
    String CITY = "city";
    String RULE_GRADE = "grade";

}