package com.frame.top.title.component;

import com.util.ColorUtil;
import com.util.DragEventUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @Description TODO TitleBarPanel 标题栏
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class TitleBarPane extends JPanel {
    private TitleBarLeftPane titleBarLeftPanel = new TitleBarLeftPane("<html><span style=\"font-size:12px;\" " +
            "color=#f0f0f0>API查看器</span></html>");  // 标题

    /**
     * @description: TODO [TitleBarPanel] 标题栏构造函数
     * @author: ZFiend
     * @date: 2023/2/6 21:15
     * @param: frame
     * @return:
     */
    public TitleBarPane(JFrame frame) {
        // 设置布局
        this.setLayout(new BorderLayout());
        //给父窗体初始化拖拽事件
        DragEventUtil.initDragEvent(frame, this);
        //添加右界面
        this.add(new TitleBarRightPane(frame), BorderLayout.EAST);
        //添加左界面
        this.add(titleBarLeftPanel, BorderLayout.WEST);
        //设置高度
        this.setPreferredSize(new Dimension(0, 36));
        //设置背景
        this.setBackground(ColorUtil.BLACK_DEEP_5);
        //设置边框
//        this.setBorder(new EmptyBorder(0, 0, 0, 0));
//        this.setBorder(new LineBorder(ColorUtil.PURPLE_TYPE_3, 1));
        //双击标题栏，进行缩放
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TitleBarRightPane.reSize(frame);
                }
                super.mouseClicked(e);
            }
        });
    }
}

