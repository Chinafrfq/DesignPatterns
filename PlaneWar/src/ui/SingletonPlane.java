package ui;

/**
 * 采用饿汉单例创建Plane对象
 * @author ghp
 * @date 2022/9/17
 */
public class SingletonPlane {
    private static Plane plane = new Plane();
    private SingletonPlane(){}
    public static Plane getPlane() {
        return plane;
    }
}
