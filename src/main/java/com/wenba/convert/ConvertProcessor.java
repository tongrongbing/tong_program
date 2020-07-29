package com.wenba.convert;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-14 17:27
 **/
@FunctionalInterface
public interface ConvertProcessor<T,S> {
    S convert(T t);
}
