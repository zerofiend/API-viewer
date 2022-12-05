package com.frame.title;

import com.frame.title.component.TitleBarPanel;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    public TitleBarPanel titleBarPanel;

    public TitlePanel(JFrame jFrame) {

        this.setLayout(new BorderLayout());
//        this.setBackground(ColorUtil.setTransparent(1));
        titleBarPanel = new TitleBarPanel(jFrame);

        this.add(titleBarPanel, BorderLayout.NORTH);
    }

    public TitleBarPanel getTitleBarPanel() {
        return titleBarPanel;
    }
}


