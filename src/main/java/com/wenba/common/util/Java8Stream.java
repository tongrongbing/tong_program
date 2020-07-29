package com.wenba.common.util;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-15 17:08
 **/

public class Java8Stream {
    public static void main(String[] args) {
        List<Integer> collect = Stream.of("apple", "banana", "orange", "waltermaleon", "grape")
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
        System.out.println(reduce);

    }
}
@Data
@AllArgsConstructor
class Student{
    private String name;

    private int age;

}



