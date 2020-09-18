package com.wenba.common.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：tongrongbing
 * @date：created in 2020/9/1 2:58 下午
 * @description：
 */
public class AviatorTest {

    public static void main(String[] args) {
        String expression = "((string.contains(region,\"天津\") || string.contains(region,\"江苏\")) && (string.contains(region,\"四川\") || string.contains(region,\"北京\"))) || ((channel_name == \"333\"))";
        // 编译表达式
        //(string.contains(first_channel,"dsp") && (second_channel == "gt" ||  second_channel == "nw") ) || (!string.contains(region,"北京"))
        /*Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("first_channel", "nww");  // 一级渠道
        //env.put("second_channel","gt"); // 二级渠道
        //env.put("region","北京"); // 所属地域
        Boolean executeRes = (Boolean) compiledExp.execute(env);
        System.out.println(executeRes);*/
        //String expression = "(string.contains(second_channel,\"ds,trb\") && (first_channel == \"gt\" ||  first_channel == \"nw\")) || (region == \"北京\")";
        //String expression = "(!string.contains(first_channel,\"nw\") && !string.contains(first_channel,\"官网\") && !string.contains(first_channel,\"dsp\") && !string.contains(first_channel,\"app\"))";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("region","江苏");
        env.put("channel_name", "官网_2_32_4");
        //env.put("grade", "6");                              //((grade == 1 || grade == 6) && string.contains(second_channel,"app")) || ((region == 北京 || region == 上海))
        //env.put("region", "北京");
      //  env.put("channel_name", "nw_家长端_社群_直播群_学习报告申请活动");


        Boolean executeRes = (Boolean) compiledExp.execute(env);
        System.out.println(executeRes);

        Integer i = -1;
        System.out.println(i);

    }
}


class ValidateRule extends AbstractFunction {
    private boolean matches(String ip, String subnet) {
       /* IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
        return ipAddressMatcher.matches(ip);*/
        return true;
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String ipBlack = FunctionUtils.getStringValue(arg1, env);
        String ipStr = FunctionUtils.getStringValue(arg2, env);
        Boolean result = null;
        for (String ipList: ipBlack.split(",")) {
            if (ipList.contains("*")) {
                ipList = ipList.substring(0, ipList.lastIndexOf(".")) + ".0/24";
            }
            result = matches(ipStr, ipList);
            if (result) {
                break;
            }
        }
        if (result) {
            return AviatorBoolean.TRUE;
        } else {
            return AviatorBoolean.FALSE;
        }
    }

    public String getName() {
        return "ipContains";
    }
}
