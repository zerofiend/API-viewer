package com.frame;

import com.frame.base.ResizeFrame;
import com.frame.menu.MenuPanel;
import com.frame.select.SelectPanel;
import com.frame.title.TitlePanel;
import com.frame.top.TopPanel;
import com.frame.view.ViewPanel;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame {
    ResizeFrame resizeFrame;    //  基础界面
    private Dimension resizeFrameSize;

    public void init() {
        resizeFrame = new ResizeFrame(300, 200);
        //  设置标题
        resizeFrame.setTitle("API查看器");
        //  设置布局方式
        resizeFrame.setLayout(new BorderLayout());
        //  设置初始窗口大小
        resizeFrame.setSize(1440, 810);
        //  设置居中
        resizeFrame.setLocation();
        // 去除标题栏
        resizeFrame.setUndecorated(true);
        //  自定义标题栏
        TitlePanel titlePanel = new TitlePanel(resizeFrame);
        //  设置菜单栏
        MenuPanel menuPanel = new MenuPanel(resizeFrame);
        //  顶部操作栏
        TopPanel topPanel = new TopPanel();
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(menuPanel, BorderLayout.SOUTH);
        //  设置左侧选择栏
        SelectPanel selectPanel = new SelectPanel();
        //  设置右侧内容显示栏
        ViewPanel viewPanel = new ViewPanel();
        //  设置背景图片
        JPanel jPanel = (JPanel) resizeFrame.getContentPane();
        jPanel.setOpaque(false);    //  将面板设置成透明
        jPanel.setBorder(new LineBorder(ColorUtil.BLUE_DEEP_3, 2));
        ImageIcon img = new ImageIcon("src/main/resources/images/background.jpeg");
        JLabel bg = new JLabel(img) {   // 匿名类部类重写paint方法，将图像封装至label中
            public void paint(Graphics g) {
                Image img2 = img.getImage();
                g.drawImage(img2, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        resizeFrame.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE)); //  将label设置为最底层
        bg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); //  将label的大小设置为图片的大小
        jPanel.setLayout(new BorderLayout()); //  使用绝对布局
        jPanel.addComponentListener(new ComponentAdapter() {
            // 检测整个窗口大小是否改变
            @Override
            public void componentResized(ComponentEvent e) {
                resizeFrameSize = resizeFrame.getSize();
                bg.setSize(resizeFrameSize);
                selectPanel.setPreferredSize(new Dimension((int) (resizeFrameSize.getWidth() * 0.3), -1));
                viewPanel.setPreferredSize(new Dimension((int) (resizeFrameSize.getWidth() * 0.7), -1));
                bg.repaint();   //  重新绘制图片
            }
        });
        // 添加所有窗口
//        jPanel.add(titlePanel, BorderLayout.NORTH);
//        jPanel.add(menuPanel, BorderLayout.NORTH);
        jPanel.add(topPanel, BorderLayout.NORTH);
        jPanel.add(selectPanel, BorderLayout.WEST);
        jPanel.add(viewPanel, BorderLayout.EAST);
        //  运行显示窗口
        resizeFrame.showFrame();
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
}
