package com.frame.center.left;

import com.frame.center.left.selection.SelectionPane;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @Description TODO LeftPanel 左面板
 * @Author ZFiend
 * @Create 2023.02.05 19:20
 */
public class LeftPane extends JPanel {
    SelectionPane selectionPanel;


    /**
     * @description: TODO [LeftPanel] 左面板构造函数
     * @author: ZFiend
     * @date: 2023/2/5 19:38
     * @param:
     * @return:
     */
    public LeftPane(String path) {
        // 设置布局
        this.setLayout(new BorderLayout());
        // 设置背景透明
        this.setBackground(null);
        this.setOpaque(false);
        // 设置边框
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1, 1));
        // 设置滚动面板
        selectionPanel = new SelectionPane(path);
        this.add(selectionPanel);
    }
}
