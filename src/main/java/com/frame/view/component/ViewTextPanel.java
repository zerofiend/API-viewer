package com.frame.view.component;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ViewTextPanel extends JTextPane {
    public ViewTextPanel() {
        //设置大小
//        this.setPreferredSize(new Dimension(1025, -1));
        //设置背景
        this.setBackground(ColorUtil.setTransparent(0.1f));
        //设置边框
        this.setBorder(new LineBorder(ColorUtil.BLUE_DEEP_3, 1));
        //设置不可编辑
        this.setEditable(false);
    }
}
