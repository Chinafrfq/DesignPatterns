package com.hhxy.singleton;

/**
 * 登记式/静态内部类
 * @author ghp
 * @date 2022/9/17
 */
public class Singleton5 {
    private static class SingleHolder{
        private static final Singleton5 INSTANCE = new Singleton5();
    }
    private Singleton5(){}
    public static final Singleton5 getInstance(){
        return SingleHolder.INSTANCE;
    }
}
