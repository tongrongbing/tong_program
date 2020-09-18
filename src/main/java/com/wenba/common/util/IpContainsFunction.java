package com.wenba.common.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：tongrongbing
 * @date：created in 2020/9/1 3:22 下午
 * @description：
 */
public class IpContainsFunction {
    public static void main(String[] args) {
        /*String ipBlack = "139.119.23.*,10.119.23.2/31";
        String ipStr = "10.119.23.12";
        Map<String, Object> env = new HashMap<>();
        env.put("ipBlack", ipBlack);
        env.put("ipStr", ipStr);

        AviatorEvaluator.addFunction(new IpContains());

        System.out.println(AviatorEvaluator.execute("ipContains(ipBlack, ipStr)", env));
        env.put("ipStr", "10.119.23.2");
        System.out.println(AviatorEvaluator.execute("ipContains(ipBlack, ipStr)", env));*/

        List<String> list = Arrays.asList("北京","上海","安徽");

        StringBuilder sb = new StringBuilder();
        for(String str : list){
            String typeContains = "\"param1 == param2\"";
            typeContains = typeContains.replace("param1", "first_channel")
                    .replace("param2",str).trim();
            sb.append(typeContains + " || ");
            System.out.println(typeContains);
        }
        System.out.println(sb.toString().substring(0,sb.lastIndexOf("||")));

    }
}
class IpContains extends AbstractFunction {
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
