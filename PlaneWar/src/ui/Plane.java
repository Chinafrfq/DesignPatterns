package ui;

import java.awt.image.BufferedImage;

/**
 * 玩家飞机类：给玩家进行操作的飞机
 * @author ghp
 * @date 2022/9/10
 */
public class Plane {
    /**
     * BufferedImage：飞机的图片
     * x: 飞机的横坐标
     * y: 飞机的纵坐标
     */
    BufferedImage image;
    int x;
    int y;

    public Plane() {
        image = GetImg.getImg("hero.png");
        x = 200;
        y = 500;
    }
}
