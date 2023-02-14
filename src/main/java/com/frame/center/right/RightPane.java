package com.frame.center.right;

import com.frame.UI.ScrollBarUI;
import com.frame.center.right.text.ViewTextPane;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * @Description TODO RightPanel 右面板
 * @Author ZFiend
 * @Create 2023.02.05 19:36
 */
public class RightPane extends JScrollPane {
    public ViewTextPane viewTextPanel;

    /**
     * @description: TODO [RightPanel] 右面板构造函数
     * @author: ZFiend
     * @date: 2023/2/5 19:38
     * @param:
     * @return:
     */
    public RightPane() {
        // 设置背景透明
        this.getViewport().setBackground(ColorUtil.BLACK_DEEP_4);
        // 设置边框
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1, 1));
        // 添加文本域
        viewTextPanel = ViewTextPane.getViewTextPane();
        this.getViewport().add(viewTextPanel);
        // 设置自定义滚动条
        this.getVerticalScrollBar().setUI(ScrollBarUI.createUI());
        this.getHorizontalScrollBar().setUI(ScrollBarUI.createUI());
    }
}
