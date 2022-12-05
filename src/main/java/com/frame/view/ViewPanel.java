package com.frame.view;

import com.frame.view.component.ViewTextPanel;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewPanel extends JPanel {
    public ViewTextPanel viewTextPanel;

    public ViewPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5, 0, 5, 5));
        this.setPreferredSize(new Dimension(1040, -1));
        this.setBackground(ColorUtil.setTransparent(0));
        viewTextPanel = new ViewTextPanel();

        this.add(viewTextPanel, BorderLayout.CENTER);
    }
}
