package ui;

import java.awt.image.BufferedImage;

/**
 * 子弹类
 * @author ghp
 * @date 2022/9/10
 */
public class Fire {
    BufferedImage image;
    int x;
    int y;

    public Fire() {
    }

    /**
     * 子弹的有参构造方法
     * 需要确保子弹从玩家飞机的飞机头发射
     * @param plane
     */
    public Fire(Plane plane){
        //获取子弹的图片
        image = GetImg.getImg("fire.png");
        //保证子弹在玩家飞机的居中位置(注意：子弹的图片缩小了4倍，所以是/8)
        x = plane.x + plane.image.getWidth()/2 - image.getWidth()/8;
        y = plane.y - plane.image.getHeight()/8;
    }

    /**
     * 移除子弹的方法
     */
    public void remove(){
        x = -99;
        y = -99;
    }
}
