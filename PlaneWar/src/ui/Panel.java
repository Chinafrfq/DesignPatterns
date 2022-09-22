package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 面板类：给弹窗中添加内容
 *
 * @author ghp
 * @date 2022/9/10
 */
public class Panel extends JPanel {

    /**
     * bg: 背景图，用于给画笔绘制背景图
     * plane: 玩家的飞机对象，用于给画笔绘制飞机
     * enemyPlanes 敌机对象的集合，用于给画笔绘制敌机
     * fires: 子弹对象的集合，用于给画笔绘制子弹
     * score: 分数
     */
    BufferedImage bg = GetImg.getImg("bg1.jpg");
//    Plane plane = new Plane();
    //使用单例模式
    Plane plane = SingletonPlane.getPlane();

//        EnemyPlane enemyPlane = new EnemyPlane();//只能创建一个敌机
    List<EnemyPlane> enemyPlanes = new ArrayList<>();
    List<Fire> fires = new ArrayList<>();

    int score = 0;

    public Panel() {
    }

    /**
     * 飞机类的有参构造方法
     * 作用：负责添加鼠标监听事件和键盘监听事件，让玩家能够使用鼠标或键盘操作飞机
     *
     * @param frame
     */
    public Panel(Frame frame) {
        /**
         * 给鼠标添加监听事件：允许鼠标操作飞机
         */
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                plane.x = e.getX() - plane.image.getWidth() / 2;
                plane.y = e.getY() - plane.image.getHeight() / 2;
                if (plane.x < 0) {
                    //限制左侧
                    plane.x = 0;
                }
                if (plane.x > 512 - plane.image.getWidth() - 20) {
                    //限制右侧
                    plane.x = 512 - plane.image.getWidth() - 20;
                }
                if (plane.y < 0) {
                    //限制上侧
                    plane.y = 0;
                }
                if (plane.y > 720 - plane.image.getHeight()) {
                    //限制下侧
                    plane.y = 720 - plane.image.getHeight();
                }
                //刷新页面，当鼠标移动后刷新页面
                repaint();
            }
        };
        //监听鼠标
        addMouseListener(mouseAdapter);
        //给鼠标添加移动监听器，鼠标每次移动都执行一次mouseAdapter对象的mouseMoved方法
        addMouseMotionListener(mouseAdapter);

        /**
         * 添加键盘的监听事件：允许键盘操作飞机
         */
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                final int SPEED_PLANE = 15;//控制玩家飞机的移动速度
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                    //如果按下←键或A键，飞机左移10个单位
                    if (plane.x > 0) {
                        plane.x -= SPEED_PLANE;
                    }
                } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                    //如果按下→键或D键，飞机右移10个单位
                    if ((plane.x + plane.image.getWidth() + 23) < 512) {
                        plane.x += SPEED_PLANE;
                    }
                } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                    //如果按下↑键或W键，飞机上移10个单位
                    if (plane.y > 0) {
                        plane.y -= SPEED_PLANE;
                    }
                } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                    //如果按下↓键或S键，飞机下移10个单位
                    if ((plane.y + plane.image.getHeight() + 50) < 768) {
                        plane.y += SPEED_PLANE;
                    }
                }
                //刷星页面
                repaint();
            }
        };

        //给键盘添加器，键盘每按下对应的键就会触发keyAdapter对象的keyPressed方法
        frame.addKeyListener(keyAdapter);
    }


    /**
     * enemyPlaneBegin方法：一旦游戏开始，就创建一个线程，这个线程专门用来创建敌机、移动敌机
     */
    public void enemyPlanebegin() {
        //创建一个线程，该线程不断创建敌机
        new Thread(() -> {
            while (true) {
                //创建一个敌机
                enemyCreate();
                //让敌机移动
                enemyMove();
                //子弹和敌机的碰撞判断
                hit();
                try {
                    //不要让敌机创建的太快了
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //刷星页面
                repaint();
            }
        }).start();
    }

    /**
     * 创建敌机的方法
     */
    //index和GEN_ENEMY常量用来控制飞机的生成速度
    int index = 0;
    final static int GEN_ENEMY = 20;

    private void enemyCreate() {
        index++;
        if (index >= GEN_ENEMY) {
            EnemyPlane enemyPlane = new EnemyPlane();
            enemyPlanes.add(enemyPlane);
            index = 0;
        }
    }

    /**
     * 敌机移动的方法
     */
    private void enemyMove() {
        final int SPEED_ENEMY = 5;//用来控制敌机的速度
        for (EnemyPlane enemyPlane : enemyPlanes) {
            enemyPlane.y += SPEED_ENEMY;
        }
    }

    /**
     * 子弹和敌机的碰撞判断
     */
    private void hit() {
        /*
        //这里使用迭代器有坑！！！这个是由于迭代器的底层原理，不愿与一般修改一遍遍历集合
        for (EnemyPlane enemyPlane : enemyPlanes) {
            for (Fire fire : fires) {
                if ((fire.x + fire.image.getWidth() / 4) > enemyPlane.x && fire.x < (enemyPlane.x + enemyPlane.image.getWidth())
                        && fire.y < (enemyPlane.y + enemyPlane.image.getHeight()) && (fire.y+fire.image.getHeight()/4) > enemyPlane.y ) {
                    enemyPlane.remove();
                    fire.remove();
                    score++;
                }
            }
        }
        */
        /*
        //测试解决迭代器异常
        Iterator<EnemyPlane> enemyPlaneIterator = enemyPlanes.iterator();
        Iterator<Fire> fireIterator = fires.iterator();
        while(enemyPlaneIterator.hasNext()){
            EnemyPlane enemyPlane = enemyPlaneIterator.next();
            while(fireIterator.hasNext()){
                Fire fire = fireIterator.next();
                if ((fire.x + fire.image.getWidth() / 4) > enemyPlane.x && fire.x < (enemyPlane.x + enemyPlane.image.getWidth())
                        && fire.y < (enemyPlane.y + enemyPlane.image.getHeight()) && (fire.y+fire.image.getHeight()/4) > enemyPlane.y ) {
                    enemyPlane.remove();
                    fire.remove();
                    score++;
                }
            }
        }
        */
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            for (int j = 0; j < fires.size(); j++) {
                Fire fire = fires.get(j);
                if ((fire.x + fire.image.getWidth() / 4) > enemyPlane.x && fire.x < (enemyPlane.x + enemyPlane.image.getWidth())
                        && fire.y < (enemyPlane.y + enemyPlane.image.getHeight()) && (fire.y+fire.image.getHeight()/4) > enemyPlane.y ) {
                    //子弹打中敌机，子弹和敌机同时消失
                    enemyPlane.remove();
                    fire.remove();
                    score++;
                }
            }
        }
    }

    /**
     * 子弹的线程方法，用于子弹的创建、移动
     */
    public void fireBegin() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    fireCreate();
                    fireMove();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //刷星页面
                    repaint();
                }
            }
        }.start();
    }

    /**
     * 创建子弹的方法
     */
    //index1和GEN_FIRE用来控制子弹的生成速度
    int index1 = 0;
    final int GEN_FIRE = 20;

    private void fireCreate() {
        index1++;
        if (index1 >= GEN_FIRE) {
            Fire fire = new Fire(plane);
            fires.add(fire);
            index1 = 0;
        }
    }

    /**
     * 子弹移动的方法
     */
    private void fireMove() {
        final int SPEED_FIRE = 5;//控制子弹的速度
        for (Fire fire : fires) {
            fire.y -= SPEED_FIRE;
        }
    }

    /**
     * 重写画笔，画笔用于绘图
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //使用画笔绘制背景图(还需在Main中将Panel对象添加到Frame对象中，这样才能展示出图片)
        g.drawImage(bg, 0, 0, null);
        //使用画笔绘制敌机
//        g.drawImage(enemyPlane.image,enemyPlane.x,enemyPlane.y,null);//只能创建单个敌机
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            g.drawImage(enemyPlane.image, enemyPlane.x, enemyPlane.y, null);
        }
        //使用画笔绘制玩家的飞机（谁先绘制，谁就在下面）
        g.drawImage(plane.image, plane.x, plane.y, null);
        //使用画笔绘制玩家飞机发射的子弹(注意记得缩小子弹的图片)

        /*
        使用迭代器会报ConcurrentModificationException（迭代器修改异常）
        for (Fire fire : fires) {
            g.drawImage(fire.image, fire.x, fire.y, fire.image.getWidth() / 4, fire.image.getHeight() / 4, null);
        }
        */

        for (int i = 0; i < fires.size(); i++) {
            Fire fire = fires.get(i);
            g.drawImage(fire.image, fire.x, fire.y, fire.image.getWidth() / 4, fire.image.getHeight() / 4, null);
        }

        //使用画笔画分数
        g.setColor(Color.WHITE);
        g.drawString("分数 "+score,10,30);
    }
}
