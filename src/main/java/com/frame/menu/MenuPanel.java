package com.frame.menu;

import com.frame.menu.component.MenuBarPanel;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuBarPanel menuBarPanel;

    public MenuPanel(JFrame jFrame) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5, 5, 0, 5));
        this.setBackground(ColorUtil.setTransparent(0));
        menuBarPanel = new MenuBarPanel(jFrame);

        this.add(menuBarPanel, BorderLayout.CENTER);
    }
}
