package com.hhxy.singleton;

/**
 * 饿汉单例
 * @author ghp
 * @date 2022/9/17
 */

/*

//方式一：
public class Singleton3 {
    private static  Singleton3 instance = new Singleton3();
    private Singleton3(){}

    public static Singleton3 getInstance() {
        return instance;
    }
}
*/

//方式二：
public class Singleton3{
    private static Singleton3 instance;

    static {
         instance = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return instance;
    }
}
