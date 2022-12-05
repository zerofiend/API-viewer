package com.frame.top;

import com.util.ColorUtil;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel() {
        this.setLayout(new BorderLayout());
//        this.setPreferredSize(new Dimension(-1, 77));
        this.setBackground(ColorUtil.setTransparent(0));

    }
}
