package com.wenba.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-17 14:33
 **/
public class CollectionUtilsTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","5","3","4","6");
        List<String> db = Arrays.asList("1","2","1","3","3");
        List<String> re = new ArrayList<>();
        re.addAll(list);
        re.removeAll(db);
        HashSet<String> strings = new HashSet<>();
        strings.addAll(re);
        System.out.println(strings);

    }
}
