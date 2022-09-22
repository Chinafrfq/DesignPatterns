package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 读图工具类：读取图片
 * @author ghp
 * @date 2022/9/10
 */
public class GetImg {
    public static BufferedImage getImg(String name){
        BufferedImage image = null;
        try {
            image = ImageIO.read(GetImg.class.getResource("/img/"+name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
