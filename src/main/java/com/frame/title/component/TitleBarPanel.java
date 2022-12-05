package com.frame.title.component;

import com.util.ColorUtil;
import com.util.DragEventUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitleBarPanel extends JPanel {
    public TitleBarLeftPanel titleBarLeftPanel = new TitleBarLeftPanel("<html><span style=\"font-size:12px;\" " +
            "color=#c6e2ff>API查看器</span></html>");

    /**
     * @param jframe
     * @return:
     */
    public TitleBarPanel(JFrame jframe) {

        this.setLayout(new BorderLayout());
        //给父窗体初始化拖拽事件
        DragEventUtil.initDragEvent(jframe, this);
        //添加右界面
        this.add(new TitleBarRightPanel(jframe), BorderLayout.EAST);
        //添加左界面
        this.add(titleBarLeftPanel, BorderLayout.WEST);
        //设置高度
        this.setPreferredSize(new Dimension(0, 36));
        //设置背景
        this.setBackground(ColorUtil.setTransparent(0.25f));
        //设置边框
        this.setBorder(new EmptyBorder(2, 2, 0, 2));
        this.setBorder(new LineBorder(ColorUtil.BLUE_DEEP_3, 1));
        //双击标题栏，进行缩放
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TitleBarRightPanel.reSize(jframe);
                }
                super.mouseClicked(e);
            }
        });
    }

    public TitleBarLeftPanel getTitleBarLeftPanel() {
        return titleBarLeftPanel;
    }
}

