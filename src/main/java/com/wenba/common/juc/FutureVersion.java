package com.wenba.common.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-08 14:45
 **/
public class FutureVersion {
    public static void main(String[] args) throws Exception{
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("。。。。。。。");
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("running.......");
                }
                System.out.println("就算是取消了任务还会继续执行。。。。。。。");
                throw new InterruptedException("当前线程被打断了.........");

                //return "hello";
            }
        });
        Thread t = new Thread(task);
        t.start();
        TimeUnit.SECONDS.sleep(4);
        task.cancel(true);
//        System.out.println(task.get());
        System.out.println("================");

/*
        int i = 9;
        try {
            i = 9/0;
            return;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally......");
        }*/
    }
}
