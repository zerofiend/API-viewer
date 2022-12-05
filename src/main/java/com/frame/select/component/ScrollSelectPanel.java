package com.frame.select.component;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ScrollSelectPanel extends JScrollPane {
    public ScrollSelectPanel() {
        //  设置背景透明
        this.getViewport().setOpaque(false);
        //  设置大小
//        this.setPreferredSize(new Dimension(400, -1));
        //  设置背景
        this.setBackground(ColorUtil.setTransparent(0.1f));
        //  设置边框
        this.setBorder(new LineBorder(ColorUtil.BLUE_DEEP_3, 1));
    }
}
