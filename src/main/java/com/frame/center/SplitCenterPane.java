package com.frame.center;

import com.frame.UI.MySplitPaneUI;
import com.frame.center.left.LeftPane;
import com.frame.center.right.RightPane;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * @Description TODO SplitCenterPanel 主分隔面板
 * @Author ZFiend
 * @Create 2023.02.06 23:35
 */
public class SplitCenterPane extends JSplitPane {
    LeftPane leftPanel;    // 左面板
    RightPane rightPanel;  // 右面板

    /**
     * @description: TODO [CenterPanel] 主分隔面板构造函数
     * @author: ZFiend
     * @date: 2023/2/5 19:18
     * @param: c1
     * @param: c2
     * @return:
     */
    public SplitCenterPane(String path) {
        // 设置背景透明
        this.setBackground(null);
        this.setOpaque(false);
        // 设置边框
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_2, 1));
        // 设置左边组件
        leftPanel = new LeftPane(path);
        this.setLeftComponent(leftPanel);
        // 设置右边组件
        rightPanel = new RightPane();
        this.setRightComponent(rightPanel);
        // 设置可伸缩
        this.setContinuousLayout(true);
        // 设置分隔条初始位置
        this.setDividerLocation(300);
        // 修改伸缩条ui样式
        this.setUI(MySplitPaneUI.createUI());
    }
}
