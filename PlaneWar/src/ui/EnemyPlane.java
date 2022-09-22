package ui;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 敌机类：系统自动生成的飞机
 * @author ghp
 * @date 2022/9/10
 */
public class EnemyPlane {
    /**
     * image：敌机的图片
     * x: 敌机的横坐标
     * y: 敌机的纵坐标
     */
    BufferedImage image;
    int x;
    int y;

    public EnemyPlane() {
        Random random = new Random();
        //随机生成敌机图片的序号：1~15
        int index = random.nextInt(15)+1;
        String number = index < 10 ? ("0"+index) : (index+"");
        //拼接成敌机的图片名
        String name = "ep"+number+".png";
        //获取敌机图片
        image = GetImg.getImg(name);
        y = 0;
        //设置飞机随机产生的位置（需要进行限制，防止随机飞机产生的飞机脱离弹窗）
        x = random.nextInt(512-image.getWidth());
    }

    /**
     * 移除敌机的方法
     */
    public void remove(){
        x = -999;
        y = -999;
    }
}
