package ui;

/**
 * 启动类：用于启动程序
 * @author ghp
 * @date 2022/9/10
 */
public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        Panel panel = new Panel(frame);
        //创建线程
        panel.enemyPlanebegin();
        panel.fireBegin();
        //将面板对象添加到框架对象中，这样才能在弹窗中展示出画笔绘制的内容
        frame.add(panel);
        //让控件（弹窗）能够显示出来
        frame.setVisible(true);
    }
}
