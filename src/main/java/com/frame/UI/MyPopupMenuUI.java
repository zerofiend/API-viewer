package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.*;

/**
 * @Description TODO MyPopupMenuUI
 * @Author ZFiend
 * @Create 2023.02.17 17:13
 */
public class MyPopupMenuUI extends BasicPopupMenuUI {
    private static final Color defaultBackgroundColor = ColorUtil.BLACK_DEEP_4;
    private static final Color selectedBackgroundColor = ColorUtil.BLACK_DEEP_1;
    private static final Color defaultForegroundColor = ColorUtil.WHITE;
    private static final Color selectForegroundColor = ColorUtil.PURPLE_TYPE_2;

    /**
     * @description: TODO [paint] 绘制弹出菜单的背景色
     * @author: ZFiend
     * @date: 2023/2/17 17:41
     * @param: g
     * @param: c
     * @return: void
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        g.setColor(defaultBackgroundColor);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }

}
