package com.frame.top.menu;

import com.frame.top.menu.component.MenuBarPane;
import com.util.ColorUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Description TODO MenuPanel 菜单面板
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class MenuPane extends JPanel {
    public MenuBarPane menuBarPanel;   // 菜单栏

    public MenuPane(JFrame jFrame, JPanel selectPanel) {
        // 设置布局
        this.setLayout(new BorderLayout());
        // 设置边框
//        this.setBorder(new EmptyBorder(5, 5, 0, 5));
        // 设置背景透明
        this.setBackground(ColorUtil.setTransparent(0));
        // 添加菜单栏
        menuBarPanel = new MenuBarPane(jFrame, selectPanel);
        this.add(menuBarPanel, BorderLayout.CENTER);
    }
}
