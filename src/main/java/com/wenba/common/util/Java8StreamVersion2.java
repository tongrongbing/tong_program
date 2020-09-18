package com.wenba.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-08-12 17:42
 **/
public class Java8StreamVersion2 {
    public static void main(String[] args) {
        List<Goods> goodsList = Arrays.asList(new Goods("苹果",12d,10),
                new Goods("香蕉",12.0d,2),new Goods("苹果",10d,10));

        double sum = goodsList.stream().mapToDouble(Goods::calculate).sum();
        System.out.println(sum);
        Double reduce = goodsList.stream().map(Goods::calculate).reduce(0d, (a, b) -> a + b);
        System.out.println(reduce);

    }
}

@Data
@AllArgsConstructor
class Goods{
    private String name;
    private double price;
    private int num;

    public double calculate(){
        return this.price * this.num;
    }
}