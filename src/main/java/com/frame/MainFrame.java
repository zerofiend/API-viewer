package com.frame;

import com.frame.base.BaseFrame;
import com.frame.center.CenterPane;
import com.frame.top.TopPane;
import com.frame.transition.LoadingPane;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @Description TODO MainFrame 项目主界面
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class MainFrame {
    static BaseFrame baseFrame;    //  基础界面
    String icon = "src/main/resources/images/icon_white.png";
//    public String filePath = "src/main/resources/unzip/src";

    /**
     * @description: TODO [MainFrame] 主界面构造函数
     * @author: ZFiend
     * @date: 2023/2/5 19:09
     * @param:
     * @return:
     */
    public MainFrame() {
        //  设置自定义可伸缩窗口
        baseFrame = new BaseFrame(300, 200);
        //  设置窗口图标
        ImageIcon imageIcon = new ImageIcon(icon);
        baseFrame.setIconImage(imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        //  设置标题
        baseFrame.setTitle("API查看器");
        //  设置布局方式
        baseFrame.setLayout(new BorderLayout());
        //  设置初始窗口大小
        baseFrame.setSize(1440, 810);
        //  设置居中
        baseFrame.setLocation();
        // 去除标题栏
        baseFrame.setUndecorated(true);
        // 设置背景
//        setBackgroundImage();
        setBackgroundColor();
    }

    /**
     * @description: TODO [setBackgroundColor] 设置背景颜色
     * @author: ZFiend
     * @date: 2023/2/5 22:02
     * @param:
     * @return: void
     */
    private void setBackgroundColor() {
        //  设置背景颜色
        JPanel jPanel = (JPanel) baseFrame.getContentPane();
        jPanel.setBackground(ColorUtil.BLACK_DEEP_2);
        jPanel.setBorder(new LineBorder(ColorUtil.PURPLE_TYPE_2, 1));
        jPanel.setLayout(new BorderLayout()); //  使用绝对布局
    }

    /**
     * @description: TODO [setBackgroundImage] 设置背景图片
     * @author: ZFiend
     * @date: 2023/2/5 19:09
     * @param:
     * @return: void
     */
    private void setBackgroundImage() {
        //  设置背景图片
        JPanel jPanel = (JPanel) baseFrame.getContentPane();
        jPanel.setOpaque(false);    //  将面板设置成透明
        jPanel.setBorder(new LineBorder(ColorUtil.BLUE_DEEP_3, 2));
        ImageIcon img = new ImageIcon("src/main/resources/images/background.jpeg");
        JLabel bg = new JLabel(img) {   // 匿名类部类重写paint方法，将图像封装至label中
            public void paint(Graphics g) {
                Image img2 = img.getImage();
                g.drawImage(img2, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        baseFrame.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE)); //  将label设置为最底层
        bg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); //  将label的大小设置为图片的大小
        jPanel.setLayout(new BorderLayout()); //  使用绝对布局
        jPanel.addComponentListener(new ComponentAdapter() {
            // 检测整个窗口大小是否改变
            @Override
            public void componentResized(ComponentEvent e) {
                bg.setSize(baseFrame.getSize());
                bg.repaint();   //  重新绘制图片
            }
        });
    }

    /**
     * @description: TODO [init] 主界面初始化
     * @author: ZFiend
     * @date: 2023/2/5 19:10
     * @param:
     * @return: void
     */
    public void init() {
        //  顶部面板
        TopPane topPanel = new TopPane(baseFrame);
        // 主面板
        CenterPane centerPanel = CenterPane.getCenterPane();
        // 添加所有窗口
        baseFrame.add(topPanel, BorderLayout.NORTH);
        baseFrame.add(centerPanel, BorderLayout.CENTER);
        //  运行显示窗口
        baseFrame.showFrame();
    }

    /**
     * frame中的控件自适应frame大小：改变大小位置和字体
     *
     * @param frame       要控制的窗体
     * @param proportionH 当前和原始的比例
     */
    public static void modifyComponentSize(JFrame frame, float proportionW, float proportionH) {

        try {
            Component[] components = frame.getRootPane().getContentPane().getComponents();
            for (Component co : components) {
//				String a = co.getClass().getName();//获取类型名称
//				if(a.equals("javax.swing.JLabel"))
//				{
//				}
                float locX = co.getX() * proportionW;
                float locY = co.getY() * proportionH;
                float width = co.getWidth() * proportionW;
                float height = co.getHeight() * proportionH;
                co.setLocation((int) locX, (int) locY);
                co.setSize((int) width, (int) height);
                int size = (int) (co.getFont().getSize() * proportionH);
                Font font = new Font(co.getFont().getFontName(), co.getFont().getStyle(), size);
                co.setFont(font);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static LoadingPane loadingPane;

    /**
     * @description: TODO [createWait] 创建过渡面板
     * @author: ZFiend
     * @date: 2023/2/12 23:03
     * @param: text
     * @return: void
     */
    public static void createWait(String text) {
        if (loadingPane == null) {
            loadingPane = new LoadingPane();
            loadingPane.setPreferredSize(new Dimension(200, 100));
            baseFrame.setGlassPane(loadingPane);
        }
        loadingPane.setText(text);
    }

    /**
     * @description: TODO [startWait] 启用过渡面板
     * @author: ZFiend
     * @date: 2023/2/12 23:03
     * @param:
     * @return: void
     */
    public static void startWait() {
        if (loadingPane == null) {
            System.out.println("未创建过渡面板");
        } else {
            loadingPane.start();
        }
    }

    /**
     * @description: TODO [stopWait] 停止过渡面板
     * @author: ZFiend
     * @date: 2023/2/12 23:03
     * @param:
     * @return: void
     */
    public static void stopWait() {
        loadingPane.stop();
    }


}
