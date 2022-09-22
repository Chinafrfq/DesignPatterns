package com.hhxy.singleton;

/**
 * 枚举类
 * @author ghp
 * @date 2022/9/17
 */
public enum Singleton6 {
    INSTANCE;

    public void say(){
        System.out.println("我是枚举类创建的对象");
    }
}
