package com.wenba.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.googlecode.aviator.AviatorEvaluator;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import sun.jvm.hotspot.oops.ObjectHeap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-15 17:08
 **/

public class Java8Stream {
    public static void main(String[] args) {
       /* List<Integer> collect = Stream.of("apple", "banana", "orange", "waltermaleon", "grape")
                .map(e -> e.length()).collect(Collectors.toList());
        System.out.println(collect); //[5, 6, 6, 12, 5]
        List<Student> students = Lists.newArrayList(new Student("xiaoming",20),
                new Student("zhao yun",18));
        students.stream().map(Student::getName).forEach(item->{  System.out.println(item);});
        List<Object> collect1 = Stream.of("a,b,c,d", "q,w,e,").flatMap(e -> Stream.of(e.split(","))).collect(Collectors.toList());
        System.out.println(collect1);
        List<Integer> collect2 = Stream.of(1, 2, 3, 1, 2, 5, 6, 7, 8, 0, 0, 1, 2, 3, 1).filter(e -> e % 2 == 0).collect(Collectors.toList());
        System.out.println(collect2);
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8,9,10).reduce(0, ((integer, integer2) -> integer + integer2));
        System.out.println(reduce);*/









        /*
         *
         *规则维度：一级渠道、二级渠道、渠道名称、所属地域、所属年级
         *
         */
        List<ClueRuleItem> ruleItemList = Arrays.asList(
                 new ClueRuleItem(1,"一级渠道","包含","关键",1,1),
                 new ClueRuleItem(2,"二级渠道","包含","gdt",1,1),
                 new ClueRuleItem(3,"所属地域","包含","北京",1,1),
                 new ClueRuleItem(4,"所属年级","是","初一",2,1),
                 new ClueRuleItem(5,"一级渠道","包含","官网",3,1),
                 new ClueRuleItem(6,"二级渠道","包含","APP",4,1), // S++

                 new ClueRuleItem(6,"所属地域","包含","安徽",4,2) // A++
        );

        Map<Integer, List<ClueRuleItem>> resultMap = ruleItemList.stream().collect(Collectors.groupingBy(ClueRuleItem::getFlag));
        String jsonString = JSON.toJSONString(resultMap);
        System.out.println(jsonString);

        Object execute = AviatorEvaluator.execute("(!string.contains(\"abc\",\"ab\")) && string.contains(\"abc\",\"a\")");
        System.out.println(execute);

        execute = AviatorEvaluator.execute("(string.contains(\"abc\",\"ab\") && (\"a\" == \"a\"))");
        System.out.println(execute);

        String firstChannel = "nw";
        String param1 = "nw";
        String area = "北京、上海";
        String paramArea ="北京";
        String expression = "((!string.contains("+"\""+firstChannel+"\""+","+"\""+param1+"\""+")) && string.contains("+"\""+area+"\""+","+"\""+paramArea+"\""+")) || (\"a\" == \"a\")";
        System.out.println(expression);
        execute = AviatorEvaluator.execute(expression);
        System.out.println(execute);



    }


    private String calculateExpression(List<ClueRuleItem> ruleItemList){
        Map<Integer, List<ClueRuleItem>> listMap = ruleItemList.stream().collect(Collectors.groupingBy(ClueRuleItem::getClueLevelId));
        Set<Map.Entry<Integer, List<ClueRuleItem>>> entrySet = listMap.entrySet();
        for(Map.Entry<Integer, List<ClueRuleItem>> entry : entrySet){
            Integer key = entry.getKey();
            List<ClueRuleItem> value = entry.getValue();
        }
        return null;
    }



}
@Data
@AllArgsConstructor
@ToString
class ClueRuleItem {
    private Integer id;
    private String clueDimension; //线索维度
    private String logicType; // 逻辑类型
    private String ruleContent; // 逻辑包含内容
    private Integer flag; // 用来区分是否是同一个规则项
    private Integer clueLevelId; // 线索等级ID

}

@Data
@AllArgsConstructor
class ClueLevel{
    private Integer id;
    private String clueLevelName;
    private String clueLevelDescription;

}

@Data
@AllArgsConstructor
class Student{
    private String name;

    private int age;

}



