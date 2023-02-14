package com.frame.top.title.component;

import com.util.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitleBarRightPane extends JPanel {
    private final String color = "#f0f0f0";


    // jFrame 是需要控制的目标窗口
    public TitleBarRightPane(JFrame jFrame) {

        //右对齐
        FlowLayout rightFlowLayout = new FlowLayout(FlowLayout.RIGHT);
        rightFlowLayout.setHgap(10);
        this.setLayout(rightFlowLayout);

        // 设置背景颜色
        this.setBackground(ColorUtil.setTransparent(0));

        // 最小化按钮
        TitleButton min = new TitleButton("<html><span color=" + color + ">—</span></html>");
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        //最大化
        TitleButton max = new TitleButton("<html><span color=" + color + ">□</span></html>");
        max.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reSize(jFrame);
            }
        });
        //关闭
        TitleButton close = new TitleButton("<html><span color=" + color + ">×</span></html>");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.dispose();
                System.exit(0);
            }
        });
        this.add(min);
        this.add(max);
        this.add(close);
    }

    /**
     * 根据状态进行缩放
     *
     * @param jFrame 目标窗体
     * @return: void
     */
    public static void reSize(JFrame jFrame) {
        int state = jFrame.getExtendedState();

        if (state == JFrame.MAXIMIZED_BOTH) {
            //还原最大化之前的尺寸
            jFrame.setExtendedState(JFrame.NORMAL);
        } else if (state == JFrame.ICONIFIED || state == JFrame.NORMAL) {
            //最大化
            jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }
}


