package com.frame.top;

import com.frame.top.menu.MenuPane;
import com.frame.top.title.TitlePane;
import com.util.ColorUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Description TODO TopPanel 顶部面板
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class TopPane extends JPanel {
    TitlePane titlePanel;  // 标题面板
    MenuPane menuPanel;    // 菜单面板

    /**
     * @description: TODO [TopPanel] 顶部面板构造函数
     * @author: ZFiend
     * @date: 2023/2/6 21:13
     * @param: baseFrame
     * @return:
     */
    public TopPane(JFrame baseFrame) {
        // 设置布局模式
        this.setLayout(new BorderLayout());
        // 设置背景颜色
        this.setBackground(ColorUtil.setTransparent(0));
        // 标题栏
        titlePanel = new TitlePane(baseFrame);
        // 菜单栏
        menuPanel = new MenuPane(baseFrame, null);
        // 添加组件
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(menuPanel, BorderLayout.SOUTH);

    }
}
