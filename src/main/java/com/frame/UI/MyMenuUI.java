package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;

/**
 * @Description TODO MenuBarUI
 * @Author ZFiend
 * @Create 2023.02.17 16:48
 */
public class MyMenuUI extends BasicMenuUI {
    private static final Color selectedBackground = ColorUtil.BLACK_DEEP_2;
    private static final Color selectedBorderColor = ColorUtil.PURPLE_TYPE_2;
    private int width = 0;
    private int height = 0;

    public MyMenuUI() {
        super.selectionBackground = selectedBackground;
        super.selectionForeground = selectedBorderColor;
    }

    @Override
    public Dimension getMaximumSize(JComponent c) {
        width = c.getWidth();
        height = c.getHeight();
        return super.getMaximumSize(c);
    }
}
