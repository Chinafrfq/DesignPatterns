package ui;

import javax.swing.*;

/**
 * 框架类：游戏界面的整体框架，生成弹窗
 * @author ghp
 * @date 2022/9/10
 */
public class Frame extends JFrame {
    public Frame(){
        //弹窗的标题
        setTitle("设计模式飞机大战");
        //弹窗的大小（要和图片一致，这样才能完美展示图片）
        setSize(512,768);
        //窗口在屏幕中居中展示
        setLocationRelativeTo(null);
        //窗口大小不可以被用户修改
        setResizable(false);
        //当用户叉掉弹窗，程序自动关闭（如果没有这行代码，叉掉弹窗程序依然会在后台运行，显然这样是不合理的，同时很费系统资源）
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
