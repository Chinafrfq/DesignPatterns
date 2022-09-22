import com.hhxy.singleton.Singleton3;
import com.hhxy.singleton.Singleton6;
import org.junit.Test;

/**
 * @author ghp
 * @date 2022/9/17
 */
public class SingleTest {

    /**
     * 测试饿汉单例模式创建的单例对象
     */
    @Test
    public void singleton3Test(){
        Singleton3 instance1 = Singleton3.getInstance();
        Singleton3 instance2 = Singleton3.getInstance();
        //可以发现两个对象的hash码相同，也就意味着两个对象是同一个
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        //也可以直接使用==号，比较两者的地址，返回true则说明两个对象是同一个
        System.out.println(instance1 == instance2);
    }

    /**
     * 测试枚举类创建的单例对象
     */
    @Test
    public void singleton6Test(){
        Singleton6 instance1 = Singleton6.INSTANCE;
        Singleton6 instance2 = Singleton6.INSTANCE;
        //可以发现两个对象的hash码相同，也就意味着两个对象是同一个
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        //也可以直接使用==号，比较两者的地址，返回true则说明两个对象是同一个
        System.out.println(instance1 == instance2);
    }
}
