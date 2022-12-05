package com.frame.select;

import com.frame.select.component.ScrollSelectPanel;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SelectPanel extends JPanel {
    public ScrollSelectPanel scrollSelectPanel;

    public SelectPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(ColorUtil.setTransparent(0));
        this.setPreferredSize(new Dimension(400, -1));
        scrollSelectPanel = new ScrollSelectPanel();

        this.add(scrollSelectPanel, BorderLayout.CENTER);
    }
}
